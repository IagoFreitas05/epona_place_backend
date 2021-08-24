package main.place.strategy;

import main.place.entity.Client;
import main.place.entity.EntidadeDominio;
import org.springframework.stereotype.Component;

@Component
public class ValidateClientMandatoryData implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidadeDominio) {
        if(entidadeDominio instanceof Client){
            Client client = (Client) entidadeDominio;
            if(client.getCpf().isEmpty() ||
                    client.getBirthday().isEmpty() ||
                    client.getName().isEmpty() ||
                    client.getLastName().isEmpty() ||
                    client.getCpf().isEmpty()
            ){
                return "- os campos: CPF, Data de Nascimento, Nome, Sobrenome e RG são obrigatórios";
            }
        }
        return null;
    }
}
