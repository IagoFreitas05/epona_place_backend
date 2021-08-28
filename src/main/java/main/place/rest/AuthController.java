package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.adapter.AdapterBuildClientDTO;
import main.place.adapter.UserLoginAdapter;
import main.place.dto.ClientDTO;
import main.place.dto.CredentialsDTO;
import main.place.dto.TokenDTO;
import main.place.entity.Address;
import main.place.entity.User;
import main.place.exception.PasswordWrongException;
import main.place.repository.AddressRepository;
import main.place.repository.UserRepository;
import main.place.securityjwt.JwtService;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("place/auth")
public class AuthController {

    private final UserService userService;
    private final UserLoginAdapter userLoginAdapter;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AdapterBuildClientDTO buildClientDTO;
    private final AddressRepository addressRepository;

    @PostMapping
    @CrossOrigin
    public TokenDTO auth(@RequestBody CredentialsDTO credentialsDTO){
        User user = userLoginAdapter.adapt(credentialsDTO);
        try{
            UserDetails authenticatedUser =  userService.doAuth(user);
            String token =  jwtService.generateToken(user);
            return new TokenDTO(user.getMail(), token);
        }catch (UsernameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
        }catch (PasswordWrongException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
        }
    }

    @GetMapping
    @CrossOrigin
    public ClientDTO loggedUser(){
        Authentication data = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByMail(data.getName());
        List<Address> address = addressRepository.findByIdUser(user.getId());

        return buildClientDTO.build(user, address);
    }
}
