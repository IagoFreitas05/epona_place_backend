package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.ClientDTO;
import main.place.entity.Address;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class AddressAdapter {
public Address adapt(ClientDTO clientDTO){
    Address address = new Address();
    address.setAddress(clientDTO.getAddress());
    address.setNameAddress(clientDTO.getNameAddress());
    address.setTypeAddress(clientDTO.getTypeAddress());
    address.setCategory(clientDTO.getCategory());
    address.setPostalCode(clientDTO.getPostalCode());
    address.setComplement(clientDTO.getComplement());
    address.setCountry(clientDTO.getCountry());
    address.setObservation(clientDTO.getObservation());
    address.setNumber(clientDTO.getNumber());
    address.setState(clientDTO.getState());
    address.setCity(clientDTO.getCity());
    return address;
    }
}
