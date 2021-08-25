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
    private String card_number;

    private String cvv;

    private String flag;

    @Column(name ="expire_date")
    private String expire_date;

    @Column(name = "card_name")
    private String card_name;

    @Column(name = "idUser")
    private Integer idUser;
}
