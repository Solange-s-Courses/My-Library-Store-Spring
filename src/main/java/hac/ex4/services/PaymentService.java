package hac.ex4.services;

import hac.ex4.Beans.Cart;
import hac.ex4.repo.Book;
import hac.ex4.repo.Payment;
import hac.ex4.repo.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Here we define all the transitions that will helps up in the payment stage to throw exception if we are tying to buy
 * more books that they are in the actual stock.
 */
@Service
public class PaymentService {

    /**
     * to get the service
     */
    @Autowired
    private BookService bookService;
    /**
     * to get the repository
     */
    @Autowired
    private PaymentsRepository repository;

    /**
     * to get the payment  and find all
     *
     * @return all the things found
     */
    public List<Payment> getPayments() {
        return repository.findAll();
    }

    /**
     * all the payments
     *
     * @param payment saving payment
     */
    public void savePayment(Payment payment) {
        repository.save(payment);
    }

    /**
     * to check for payments
     *
     * @param cart things wwe have in the cart
     */
    @Transactional
    public void pay(Cart cart) {
        for (Book book : cart.getCart()) {
            Book b = bookService.getBook(book.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + book.getId()));
            b.setQuantity(b.getQuantity() - book.getQuantity());
            if (b.getQuantity() == 0) bookService.deleteBook(b);
            if (b.getQuantity() < 0)
                throw new IllegalArgumentException("Not Enough Stack in Book name: " + book.getName());
        }
        savePayment(new Payment(cart.sum_price()));
    }

    /**
     * to get the total amount
     *
     * @return the amount
     */
    public double getTotalAmount() {
        double amount = 0;
        List<Payment> payments = repository.findAll();
        for (Payment payment : payments) {
            amount += payment.getAmount();
        }
        return amount;
    }
}
