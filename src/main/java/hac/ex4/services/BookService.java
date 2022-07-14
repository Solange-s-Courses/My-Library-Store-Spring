package hac.ex4.services;

import hac.ex4.repo.Book;
import hac.ex4.repo.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * all the functions for the book that we are going to use
 */
@Service
public class BookService {
    /**
     * for the repository
     */
    @Autowired
    private BooksRepository repository;

    /**
     * to get all the books
     *
     * @return all the books
     */
    public List<Book> getBooks() {
        return repository.findAll();
    }

    /**
     * to find by id an specific book
     *
     * @param id way to identify a book
     * @return the book by id seached
     */
    public Optional<Book> getBook(long id) {
        return repository.findById(id);
    }

    /**
     * to get the top 5 book with the discount
     *
     * @return the repostery of all the 5 top  book
     */
    public List<Book> get5mostDiscountedBooks() {
        return repository.findTop5ByOrderByDiscountDesc();
    }

    /**
     * to get a book by a name
     *
     * @param name name of book
     * @return to find the book by that name
     */
    public List<Book> getBooksByName(String name) {
        return repository.findByNameContains(name);
    }

    /**
     * To save a book
     *
     * @param book main object of our function
     */
    public void saveBook(Book book) {
        repository.save(book);
    }

    /**
     * to delete a book
     *
     * @param book main object
     */
    public void deleteBook(Book book) {
        repository.delete(book);
    }
}
