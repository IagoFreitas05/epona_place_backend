package main.place.strategy;

import main.place.entity.Endereco;
import main.place.entity.EntidadeDominio;
import org.springframework.stereotype.Component;

@Component
public class ValidarDadosObrigatoriosEndereco implements  IStrategy{
    @Override
    public String processar(EntidadeDominio entidadeDominio) {
        if(entidadeDominio instanceof Endereco){
            Endereco endereco = (Endereco) entidadeDominio;
            if(
                endereco.getCep().isEmpty() ||
                endereco.getLogradouro().isEmpty() ||
                endereco.getNomeLogradouro().isEmpty() ||
                endereco.getNumero() == null || endereco.getNumero() == 0
            ){
                return " - os campos: Cep, Logradouro, Nome logradouro e Numero são obrigatórios";
            }
        }
        return null;
    }
}
