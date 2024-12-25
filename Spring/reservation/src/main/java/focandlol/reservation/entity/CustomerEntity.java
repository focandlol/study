package focandlol.reservation.entity;

import focandlol.reservation.type.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{


}
