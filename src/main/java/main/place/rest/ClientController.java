package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.adapter.AddressAdapter;
import main.place.adapter.ClientAdapter;
import main.place.dto.ClientDTO;
import main.place.entity.*;
import main.place.facade.Facade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor

@RequestMapping("place/client")

public class ClientController {
    private final Facade facade;
    private final ClientAdapter clientAdapter;
    private final AddressAdapter addressAdapter;

    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody ClientDTO clientDTO){
        Address address = addressAdapter.adapt(clientDTO);
        Client  client = clientAdapter.adapt(clientDTO);

        EntidadeDominio entityClient = facade.salvar(client);
        if(entityClient instanceof ReturnMessage){
            return ((ReturnMessage) entityClient).getReturnMessage();
        }
        Client clientSaved = (Client) entityClient;

        address.setIdUser(clientSaved.getId());
        EntidadeDominio entityAddress = facade.salvar(address);
        if(entityAddress instanceof ReturnMessage){
            return ((ReturnMessage) entityAddress).getReturnMessage();
        }
        return "Cliente cadastrado com sucesso";
    }

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Client client){
        return facade.consultar(id, client);
    }
}
