package main.place.strategy;

import main.place.entity.Address;
import main.place.entity.EntidadeDominio;
import org.springframework.stereotype.Component;

@Component
public class ValidarDadosObrigatoriosEndereco implements  IStrategy{
    @Override
    public String processar(EntidadeDominio entidadeDominio) {
        if(entidadeDominio instanceof Address){
            Address address = (Address) entidadeDominio;
            if(
                address.getCode().isEmpty() ||
                address.getAddress().isEmpty() ||
                address.getNameAddress().isEmpty() ||
                address.getNumber() == null || address.getNumber() == 0
            ){
                return " - os campos: Cep, Logradouro, Nome logradouro e Numero são obrigatórios";
            }
        }
        return null;
    }
}
