package main.place.facade;

import main.place.entity.EntidadeDominio;

import java.util.List;
import java.util.Optional;

public interface IFacade {

    Object salvar(EntidadeDominio entidadeDominio);
    void  deletar(Integer id, EntidadeDominio entidadeDominio);
    Optional<EntidadeDominio> consultar(Integer id, EntidadeDominio entidadeDominio);
    List<EntidadeDominio> mostrarTodos(EntidadeDominio entidadeDominio);
    String alterar(EntidadeDominio entidadeDominio);

}
