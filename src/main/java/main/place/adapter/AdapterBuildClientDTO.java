package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.ClientDTO;
import main.place.entity.Address;
import main.place.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class AdapterBuildClientDTO {
    private final ClientAdapter clientAdapter;
    private final AddressAdapter addressAdapter;

    public ClientDTO build(User user, List<Address> address){
        ClientDTO dto = new ClientDTO();
        dto.setIdAddress(address.get(0).getId());
        dto.setCity(address.get(0).getCity());
        dto.setIdUser(user.getId());
        dto.setAddress(address.get(0).getAddress());
        dto.setNameAddress(address.get(0).getNameAddress());
        dto.setTypeAddress(address.get(0).getTypeAddress());
        dto.setCategory(address.get(0).getCategory());
        dto.setPostalCode(address.get(0).getPostalCode());
        dto.setComplement(address.get(0).getComplement());
        dto.setCountry(address.get(0).getCountry());
        dto.setObservation(address.get(0).getObservation());
        dto.setNumber(address.get(0).getNumber());
        dto.setState(address.get(0).getState());
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
