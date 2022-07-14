package hac.ex4.Beans;

import hac.ex4.repo.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * We use this to save our beans and do all the actions of deleating saving and editing
 */
@Getter
@Setter
@Component
public class Cart implements Serializable {
    private ArrayList<Book> cart;

    /**
     * We use this to create an array
     */
    public Cart() {
        this.cart = new ArrayList<>();
    }

    /**
     * We use this to empty our cart
     */
    public void emptyCart() {
        cart.clear();
    }

    /**
     * Function in charge of adding books and it will also raise by one the quantity
     */
    public void add(Book b) {

        for (Book c : cart) {
            if (c.equals(b)) {
                c.setQuantity(c.getQuantity() + 1);
                return;
            }
        }
        cart.add(b.withQuantity(1));
    }

    /**
     * Function to delete the book that we have selected
     *
     * @param b The specific book
     */
    public void delete(Book b) {
        cart.remove(b);
    }

    /**
     * Function to know how many books are in total
     */
    public int books_sum() {
        int cartSum = 0;
        for (Book cartItem : cart) cartSum += cartItem.getQuantity();
        return cartSum;
    }

    /**
     * Function to  get the total price of all the books after the discout or with out it.
     */
    public double sum_price() {
        double priceSum = 0;
        for (Book cartItem : cart)
            priceSum += cartItem.getQuantity() * (cartItem.getPrice() - (cartItem.getPrice() * (cartItem.getDiscount() / 100)));
        return priceSum;
    }
}
