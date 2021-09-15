package main.place.services;

import main.place.dto.ClientDTO;
import main.place.entity.Address;
import main.place.entity.User;
import main.place.exception.PasswordWrongException;
import main.place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public UserDetails doAuth(User user){
      UserDetails userLoaded = loadUserByUsername(user.getMail());
      boolean isValidPassword = encoder.matches(user.getPassword(), userLoaded.getPassword());
      if(isValidPassword){
          return userLoaded;
      }
      throw new PasswordWrongException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        main.place.entity.User user =  userRepository.findByMail(username);
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getMail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public User getLoggedUser(){
        Authentication data = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByMail(data.getName());
        return user;
    }
}
