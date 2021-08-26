package main.place.strategy;

import main.place.entity.User;
import main.place.entity.EntidadeDominio;
import main.place.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidateExistence implements IStrategy {
    private final UserRepository userRepository;

    public ValidateExistence(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String processar(EntidadeDominio entidadeDominio) {
        if(entidadeDominio instanceof User){
            User user = (User) entidadeDominio;
            if(user.getId() == null){
                User search = userRepository.findByCpf(user.getCpf());
                if(search != null){
                    return "- já existe um usuário com este CPF cadastrado";
                }
            }
        }
        return null;
    }
}
