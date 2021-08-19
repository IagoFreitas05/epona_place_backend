package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Endereco;
import main.place.entity.EntidadeDominio;
import main.place.facade.Facade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("schoolar/estudante/endereco")

public class EnderecoController {
    private final Facade facade;

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Endereco endereco){
        return facade.consultar(id,endereco);
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Endereco endereco){
        return facade.alterar(endereco);
    }
}
