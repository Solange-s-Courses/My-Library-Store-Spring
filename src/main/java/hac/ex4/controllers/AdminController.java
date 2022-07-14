package hac.ex4.controllers;

import hac.ex4.repo.Book;
import hac.ex4.services.BookService;
import hac.ex4.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * In charge of when the user logs in with all the details of the payment
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * Book service to use in the class
     */
    @Autowired
    private BookService bookService;
    /**
     * payment service to use in the class
     */
    @Autowired
    private PaymentService paymentService;


    /**
     * main page
     * @param model to save books payments and total amount inside the class as needed
     * @return admin page
     */
    @GetMapping("")
    public String admin(Model model) {

        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("payments", paymentService.getPayments());
        model.addAttribute("totalAmount", paymentService.getTotalAmount());
        return "admin";
    }

    /**
     * To fill out the option of adding a book n the database
     *
     * @param book  is our constructor
     * @param model is all the information
     * @return to add a book
     */
    @GetMapping("add-book")
    public String showBookForm(Book book, Model model) {
        return "add-book";
    }

    /**
     * Function to fill out the option of editing a book n the database
     *
     * @param id:   the way we identify each book
     * @param model to add attributes
     * @return to update the book
     */
    @PostMapping("edit")
    public String editUser(@RequestParam("id") long id, Model model) {
        Book book = bookService.getBook(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "update-book";
    }

    /**
     * Posting in the system of adding a book n the database
     *
     * @param book   is our main repo of the constructor
     * @param result what we get if we succeded or has error
     * @param model  to add attributes
     * @return adding the book
     */
    @PostMapping("add-book")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        bookService.saveBook(book);
        return "redirect:";
    }

    /**
     * Function in charge of posting in the database  a book that the amount has been edited
     *
     * @param id     the way we identify each book
     * @param book   is our main repo of the constructor
     * @param result if the process has been successfully
     * @return to update the ID
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result) {

        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }
        bookService.saveBook(book);
        return "redirect:/admin";
    }

    /**
     * Function in charge to fill out the option of  deleting a book n the database
     *
     * @param id wanted id to delete
     * @return redirect to main page
     */
    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {

        Book book = bookService.getBook(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid user Id:" + id)
                );
        bookService.deleteBook(book);
        return "redirect:/admin";
    }
}