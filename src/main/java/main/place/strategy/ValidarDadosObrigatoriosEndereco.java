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
                address.getPostalCode().isEmpty() ||
                address.getAddress().isEmpty() ||
                address.getNameAddress().isEmpty() ||
                address.getNumber() == null || address.getNumber() == 0 ||
                address.getTypeAddress() .isEmpty() ||
                address.getCategory().isEmpty() ||
                address.getState().isEmpty() ||
                address.getCountry().isEmpty() ||
                address.getCity().isEmpty()
            ){
                return " - os campos: Cep, Logradouro, Nome logradouro e Numero, País, estado, tipo logradouro e nome do endereço são obrigatórios";
            }
        }
        return null;
    }
}
