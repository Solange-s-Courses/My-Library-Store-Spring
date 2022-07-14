package hac.ex4.controllers;

import hac.ex4.Beans.Cart;
import hac.ex4.listeners.SessionListenerCounter;
import hac.ex4.repo.Book;
import hac.ex4.services.BookService;
import hac.ex4.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.unbescape.html.HtmlEscape;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Book controller
 */
@Controller
public class BooksController {

    /**
     * Book service of the book controller
     */
    @Autowired
    private BookService bookService;
    /**
     * Book  payment service of the book controller
     */

    @Autowired
    private PaymentService paymentService;

    /**
     * for showing currently session number
     */
    @Resource(name = "sessionListenerWithMetrics")
    private ServletListenerRegistrationBean<SessionListenerCounter> metrics;

    /**
     * Book  payment session bean cart
     */
    @Resource(name = "sessionBeanCart")
    private Cart cart;

    /**
     * This function is in charge this will put as on the main page of the program
     *
     * @param model to store books and cart size
     * @return main page
     */
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("books", bookService.get5mostDiscountedBooks());
        model.addAttribute("cartSize", cart.books_sum());
        return "index";
    }

    /**
     * In charge of adding books to the card or throwing an expection if it does not succeded
     *
     * @param id    the indentification fo the id
     * @param model model of the attribute
     * @return redirecting to the main page
     */
    @GetMapping("/addToCart/{id}")

    public String addToCart(@PathVariable Long id, Model model) {
        Book book = bookService.getBook(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        cart.add(book);
        return "redirect:/";
    }

    /**
     * In charge of all the process of buying or will give us a message that if it has n ot succeeded because we have
     * chosen more products than on stock
     *
     * @param model model of attribute
     * @return page of the page has succeded with payment
     */
    @GetMapping("/orderConfirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("cartSize", cart.books_sum());
        try {
            paymentService.pay(cart);
            cart.emptyCart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("message", "Can't buy, prolbem with stack in book name:" + e.getMessage());
            model.addAttribute("total_price", cart.sum_price());
            model.addAttribute("cart", cart.getCart());
            return "cart";
        }
        return "OrderConfirmation";
    }

    /**
     * In charge of deleting products of the list
     *
     * @param id id of the book identification
     * @return go to the cart page redirect
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Book book = bookService.getBook(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid book Id:" + id)
                );
        cart.delete(book);
        return "redirect:/cart";
    }

    /**
     * In charge of emptying the products of the card
     *
     * @return redirecting in the page of the cart
     */
    @GetMapping("/emptyCard")
    public String emptyCard() {
        cart.emptyCart();
        return "redirect:/cart";
    }


    /**
     * In charge of bringing us to the page of the cart
     *
     * @param model model of attribute
     * @return page of cart
     */
    @GetMapping("/cart")
    public String cart(Model model) {


        model.addAttribute("total_price", cart.sum_price());
        model.addAttribute("cart", cart.getCart());
        model.addAttribute("cartSize", cart.books_sum());
        return "cart";
    }

    /**
     * In charge of bringing us to the page of the search
     *
     * @param name  name of the book
     * @param model model of attribute
     * @return page of search of book
     */
    @GetMapping("/search")

    public String search(@RequestParam("searchString") String name, Model model) {
        model.addAttribute("books", bookService.getBooksByName(name));
        model.addAttribute("cartSize", cart.books_sum());
        return "search";
    }

    /**
     * in charge of putting the error page
     *
     * @param request requesting of html
     * @param model   model of attribute
     * @return error page
     */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /**
     * forbidden page
     * erorr page
     *
     * @return error message
     */
    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }
}
