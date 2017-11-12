package demo.domain;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {

    Payment findByPaymentId(@Param("restaurantId") String paymentId);
}
