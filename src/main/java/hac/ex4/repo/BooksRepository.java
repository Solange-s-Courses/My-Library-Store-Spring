package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Main repository of the program with all the list.
 */
public interface BooksRepository extends JpaRepository<Book, Long> {

    /**
     * @return all books in the database
     */
    List<Book> findAll();

    /**
     * get 5 top books
     * @return top 5 books by distance from database
     */
    List<Book> findTop5ByOrderByDiscountDesc();

    /**
     * main function to search for a book
     * @param name to search for in the books list
     * @return books list from the database that start with name
     */
    List<Book> findByNameContains(String name);
}
