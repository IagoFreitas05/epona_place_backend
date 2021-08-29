package main.place.strategy;

import main.place.entity.User;
import main.place.entity.EntidadeDominio;
import org.springframework.stereotype.Component;

@Component
public class ValidateClientMandatoryData implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidadeDominio) {
        if(entidadeDominio instanceof User){
            User user = (User) entidadeDominio;
            if(user.getCpf().isEmpty() ||
                    user.getBirthday().isEmpty() ||
                    user.getName().isEmpty() ||
                    user.getLastName().isEmpty()
            ){
                return "- os campos: CPF, Data de Nascimento, Nome, Sobrenome e RG são obrigatórios";
            }
        }
        return null;
    }
}
