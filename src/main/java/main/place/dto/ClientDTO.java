package main.place.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
public class ClientDTO {
    private Integer idUser;
    private Integer idAddress;
    private String name;
    private String lastName;
    private String cpf;
    private String birthday;
    private String celphone;
    private String gender;
    private String mail;
    private String password;
    private String role;
    private String typeAddress;
    private String country;
    private String state;
    private String address;
    private String nameAddress;
    private Integer number;
    private String city;
    private String complement;
    private String postalCode;
    private String category;
    private String observation;
    private String actualPassword;
}
