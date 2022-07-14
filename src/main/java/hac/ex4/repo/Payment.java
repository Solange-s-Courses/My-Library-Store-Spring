package hac.ex4.repo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * Class of payment to create the database
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {

    /**
     * for the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * for the amount
     */
    @NotNull
    @Positive
    private double amount = 1;

    /**
     * for the datetime
     */
    @CreationTimestamp
    private LocalDateTime datetime;

    /**
     * for the payment
     *
     * @param amount to save the amount
     */
    public Payment(double amount) {
        this.amount = amount;
    }


}
