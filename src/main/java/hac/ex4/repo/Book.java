package hac.ex4.repo;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Objects;

/**
 * Class that represents book
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /**
     * for the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * for the name
     */
    @NotEmpty(message = "This tittle is mandatory")
    private String name;

    /**
     * for the image
     */
    @NotEmpty
    private String image = "https://islandpress.org/sites/default/files/default_book_cover_2015.jpg";
    /**
     * for the  quantity
     */
    @With
    @PositiveOrZero
    @NotNull
    private int quantity;

    /**
     * for the price
     */
    @DecimalMin(value = "0.0", inclusive = false, message = "Please introduce a correct size")
    @NotNull
    private double price = 0;


    /**
     * for the discount
     */
    @Min(value = 0, message = "Please introduce a correct discount")
    @NotNull
    private double discount = 0;

    /**
     * Main constructor of the book in the program
     *
     * @param name     to hold the name of the book
     * @param image    to hold the image of the book
     * @param quantity to hold the quantity of the book
     * @param price    to hold the price
     * @param discount holding the discount
     */
    public Book(String name, String image, int quantity, double price, double discount) {
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    /**
     * @param o getting the object and checking if is equal to the one that we have selected
     * @return if object is equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    /**
     * @return hash code of the function
     */
    @Override
    public int hashCode() {

        return getClass().hashCode();
    }
}
