package main.place.adapter;

import main.place.dto.ClientDTO;
import main.place.entity.Address;

public class AddressAdapter {
public Address adapt(ClientDTO clientDTO){
    Address address = new Address();
    address.setAddress(clientDTO.getAddress());
    address.setNameAddress(clientDTO.getNameAddress());
    address.setTypeAddress(clientDTO.getTypeAddress());
    address.setCategory(clientDTO.getCategory());
    address.setCode(clientDTO.getCode());
    address.setComplement(clientDTO.getComplement());
    address.setCountry(clientDTO.getCountry());
    address.setObservation(clientDTO.getObservation());
    address.setNumber(clientDTO.getNumber());
    address.setState(clientDTO.getState());
    return address;
    }
}
