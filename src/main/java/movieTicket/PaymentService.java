package movieTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    // 예약에 대한 결재 정보 추가
    public Long paymentInsert(Payment payment) {
        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentRepository.save(payment).getPaymentId();

    }
}
