package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.ClientDTO;
import main.place.entity.Address;
import main.place.entity.User;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class AdapterBuildClientDTO {
    private final ClientAdapter clientAdapter;
    private final AddressAdapter addressAdapter;

    public ClientDTO build(User user, Address address){
        ClientDTO dto = new ClientDTO();
        dto.setIdAddress(address.getId());
        dto.setCity(address.getCity());
        dto.setIdUser(user.getId());
        dto.setAddress(address.getAddress());
        dto.setNameAddress(address.getNameAddress());
        dto.setTypeAddress(address.getTypeAddress());
        dto.setCategory(address.getCategory());
        dto.setPostalCode(address.getPostalCode());
        dto.setComplement(address.getComplement());
        dto.setCountry(address.getCountry());
        dto.setObservation(address.getObservation());
        dto.setNumber(address.getNumber());
        dto.setState(address.getState());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setBirthday(user.getBirthday());
        dto.setCelphone(user.getCelphone());
        dto.setCpf(user.getCpf());
        dto.setGender(user.getGender());
        dto.setMail(user.getMail());
        dto.setPassword(user.getPassword());
        dto.setActualPassword(user.getPassword());
        dto.setRole("USER");
        return dto;
    }
}
