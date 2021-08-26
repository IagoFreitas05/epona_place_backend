package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.ClientDTO;
import main.place.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Data
@AllArgsConstructor

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
        user.setRole(clientDTO.getRole());
        return user;
    }
}
