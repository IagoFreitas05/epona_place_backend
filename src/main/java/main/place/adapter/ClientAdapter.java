package main.place.adapter;

import main.place.dto.ClientDTO;
import main.place.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientAdapter {
    public Client adapt(ClientDTO clientDTO){
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setBirthday(clientDTO.getBirthday());
        client.setCelphone(clientDTO.getCelphone());
        client.setCpf(clientDTO.getCpf());
        client.setGender(clientDTO.getGender());
        client.setMail(clientDTO.getMail());
        client.setPassword(clientDTO.getPassword());
        return client;
    }
}
