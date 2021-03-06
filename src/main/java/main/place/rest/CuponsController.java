package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.*;
import main.place.facade.Facade;
import main.place.repository.CuponsRepository;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/cupons")
public class CuponsController {
    private final Facade facade;
    private final CuponsRepository cuponsRepository;
    private final UserService userService;


    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Cupon cupons){
        cupons.setIdManager(userService.getLoggedUser().getId());
        cupons.setCountUsing(0);
        cupons.setIsValid("true");
        EntidadeDominio entity = facade.salvar(cupons);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
        return "sucesso";
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Cupon cupons){
        cupons.setIdManager(userService.getLoggedUser().getId());
        String res = facade.alterar(cupons);
        return res;
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Cupon cupon ){ facade.deletar(id,  cupon);}

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Cupon cupon){
        return facade.consultar(id, cupon);
    }

    @GetMapping()
    @CrossOrigin
    public List<EntidadeDominio> mostrarTodos(Cupon cupon){
        return facade.mostrarTodos(cupon);
    }

    @GetMapping("findByIdManager/{id}")
    @CrossOrigin
    public List<Cupon> mostrarPorIdUser(@PathVariable Integer id, Cupon cupon){return cuponsRepository.findByIdManager(id); }

    @GetMapping("findByName/{name}")
    @CrossOrigin
    public Cupon findByCuponName(@PathVariable String name){return cuponsRepository.findByName(name);}

    @GetMapping("useCupom/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void useCupom(@PathVariable Integer id, Cupon cupon){
      Optional<EntidadeDominio> entidadeDominio;
      entidadeDominio = facade.consultar(id, cupon);
      Cupon cuponSaved = (Cupon) entidadeDominio.get();
      cuponSaved.setCountUsing(cuponSaved.getCountUsing() + 1);
      if(cuponSaved.getCountUsing().equals(cuponSaved.getQuantity())){
          cuponSaved.setIsValid("false");
      }
      facade.salvar(cuponSaved);
    }

    @GetMapping("findByIdUser/{id}")
    @CrossOrigin
    public List<Cupon> findByIdUser(@PathVariable Integer id){
        return cuponsRepository.findByIdUserOrderByIdDesc(id);
    }
}
