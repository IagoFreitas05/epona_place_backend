package main.place.services;

import main.place.entity.User;
import main.place.exception.PasswordWrongException;
import main.place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public UserDetails doAuth(User user){
      UserDetails userLoaded = loadUserByUsername(user.getMail());
      boolean isValidPassword = encoder.matches(user.getPassword(), userLoaded.getPassword());
      if(isValidPassword){
          return userLoaded;
      }
      throw new PasswordWrongException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        main.place.entity.User user =  userRepository.findByMail(username);
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getMail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
