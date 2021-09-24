package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Category;
import main.place.entity.EntidadeDominio;
import main.place.entity.ReturnMessage;
import main.place.facade.Facade;
import main.place.repository.CategoryRepository;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/category")
public class CategoryController {
    private final Facade facade;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Category category){
        return facade.consultar(id, category);
    }

    @GetMapping()
    @CrossOrigin
    public List<EntidadeDominio> mostrarTodos(Category category){
        return facade.mostrarTodos(category);
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Category category){
        return facade.alterar(category);
    }

    @PostMapping
    @CrossOrigin
    public String salvar(@RequestBody Category category){
        category.setIdManager(userService.getLoggedUser().getId());
        EntidadeDominio entity = facade.salvar(category);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
        return " ";
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Category category ){ facade.deletar(id,  category);}

    @GetMapping("findByIdManager/{id}")
    @CrossOrigin
    public List<Category> findByIdManager(@PathVariable Integer id){
        return categoryRepository.findByIdManager(id);
    }
}
