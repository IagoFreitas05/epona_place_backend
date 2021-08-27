package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.ClientDTO;
import main.place.dto.CredentialsDTO;
import main.place.entity.User;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class UserLoginAdapter {
    public User adapt(CredentialsDTO credentialsDTO){

        User user = new User();
        user.setMail(credentialsDTO.getMail());
        user.setPassword(credentialsDTO.getPassword());
        return user;
    }
}
