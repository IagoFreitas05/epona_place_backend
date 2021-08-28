package main.place.entity;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="card_number")
    private String cardNumber;

    private String cvv;

    private String flag;

    @Column(name ="expire_date")
    private String expireDate;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "id_user")
    private Integer idUser;
}
