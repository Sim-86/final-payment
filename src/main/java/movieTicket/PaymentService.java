package movieTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    // 예약에 대한 결재 정보 추가
    public Long paymentInsert(Payment payment) {
        return paymentRepository.save(payment).getPaymentId();

    }
}
