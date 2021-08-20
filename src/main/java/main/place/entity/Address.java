package main.place.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address extends  EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_address")
    private String typeAddress;

    private String country;

    private String state;

    private String address;

    @Column(name = "name_address")
    private String nameAddress;

    private Integer number;

    private String complement;

    @Column(name ="postal_code")
    private String postalCode;

    @Column(name = "id_user")
    private Integer idUser;

    private String category;

    private String observation;
}
