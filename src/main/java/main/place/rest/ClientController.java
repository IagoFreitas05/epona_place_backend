package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.adapter.AddressAdapter;
import main.place.adapter.ClientAdapter;
import main.place.dto.ClientDTO;
import main.place.entity.*;
import main.place.facade.Facade;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor

@RequestMapping("place/client")

public class ClientController {
    private final Facade facade;
    private final ClientAdapter clientAdapter;
    private final AddressAdapter addressAdapter;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody ClientDTO clientDTO){
        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        Address address = addressAdapter.adapt(clientDTO);
        User user = clientAdapter.adapt(clientDTO);

        EntidadeDominio entityClient = facade.salvar(user);
        if(entityClient instanceof ReturnMessage){
            return ((ReturnMessage) entityClient).getReturnMessage();
        }
        User userSaved = (User) entityClient;

        address.setIdUser(userSaved.getId());
        EntidadeDominio entityAddress = facade.salvar(address);
        if(entityAddress instanceof ReturnMessage){
            return ((ReturnMessage) entityAddress).getReturnMessage();
        }
        return "Cliente cadastrado com sucesso";
    }

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, User user){
        return facade.consultar(id, user);
    }
}
