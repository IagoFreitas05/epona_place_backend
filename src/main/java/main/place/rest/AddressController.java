package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Address;
import main.place.entity.EntidadeDominio;
import main.place.facade.Facade;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("schoolar/estudante/endereco")

public class AddressController {
    private final Facade facade;

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Address address){
        return facade.consultar(id, address);
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Address address){
        return facade.alterar(address);
    }
}
