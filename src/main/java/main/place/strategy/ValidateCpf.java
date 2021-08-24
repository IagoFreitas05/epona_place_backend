package main.place.strategy;

import main.place.entity.Client;
import main.place.entity.EntidadeDominio;
import org.springframework.stereotype.Component;

@Component
public class ValidateCpf implements IStrategy{
    @Override
    public String processar(EntidadeDominio entidadeDominio) {
        if(entidadeDominio instanceof Client){
            Client client = (Client) entidadeDominio;
            String CPF = client.getCpf();
            if (CPF.equals("00000000000") ||
                    CPF.equals("11111111111") ||
                    CPF.equals("22222222222") || CPF.equals("33333333333") ||
                    CPF.equals("44444444444") || CPF.equals("55555555555") ||
                    CPF.equals("66666666666") || CPF.equals("77777777777") ||
                    CPF.equals("88888888888") || CPF.equals("99999999999") ||
                    (CPF.length() != 11))
                return " - CPF Inválido";
        }
        return "";
    }
}
