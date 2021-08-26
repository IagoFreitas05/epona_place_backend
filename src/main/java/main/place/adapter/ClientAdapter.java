package main.place.adapter;

import main.place.dto.ClientDTO;
import main.place.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ClientAdapter {
    public User adapt(ClientDTO clientDTO){
        User user = new User();
        user.setName(clientDTO.getName());
        user.setLastName(clientDTO.getLastName());
        user.setBirthday(clientDTO.getBirthday());
        user.setCelphone(clientDTO.getCelphone());
        user.setCpf(clientDTO.getCpf());
        user.setGender(clientDTO.getGender());
        user.setMail(clientDTO.getMail());
        user.setPassword(clientDTO.getPassword());
        return user;
    }
}
