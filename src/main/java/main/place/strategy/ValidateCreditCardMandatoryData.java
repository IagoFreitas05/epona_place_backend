package main.place.strategy;

import main.place.entity.Client;
import main.place.entity.CreditCard;
import main.place.entity.EntidadeDominio;
import org.springframework.stereotype.Component;

@Component
public class ValidateCreditCardMandatoryData implements IStrategy{

    @Override
    public String processar(EntidadeDominio entidadeDominio){
        if(entidadeDominio instanceof CreditCard){
            CreditCard credit = (CreditCard) entidadeDominio;
            if(credit.getCardName().isEmpty() ||
                    credit.getCardNumber().isEmpty() ||
                    credit.getCvv().isEmpty() ||
                    credit.getExpireDate().isEmpty() ||
                    credit.getFlag().isEmpty()
            ){
                return "- os campos: Nome do cartão, número do cartão, CVV, data de expiração e bandeira são obrigatórios";
            }
        }
        return null;
    }
}
