package main.place.strategy;

import main.place.entity.Client;
import main.place.entity.EntidadeDominio;
import main.place.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidateExistence implements IStrategy {
    private final ClientRepository clientRepository;

    public ValidateExistence(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public String processar(EntidadeDominio entidadeDominio) {
        if(entidadeDominio instanceof Client){
            Client client = (Client) entidadeDominio;
            if(client.getId() == null){
                Client search = clientRepository.findByCpf(client.getCpf());
                if(search != null){
                    return "- já existe um usuário com este CPF cadastrado";
                }
            }
        }
        return null;
    }
}
