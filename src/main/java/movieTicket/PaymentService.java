package movieTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Long paymentInsert(Payment payment) {
        return paymentRepository.save(payment).getPaymentId();
    }


}
