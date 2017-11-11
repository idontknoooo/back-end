package demo.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "payment")
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {

    Payment findByPaymentId(@Param("PaymentId") String paymentId);
}
