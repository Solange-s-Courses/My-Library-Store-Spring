package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * We need to create a repository in order to make all the service
 */
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    /**
     * @return get all payments from the database
     */
    List<Payment> findAll();
}
