package movieTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class PaymentController {

  @Autowired
  PaymentService paymentService;

  //static final Long makePaymentSuccess = 1L;
  static final Long makePaymentFail = 0L;

  // 결재 정보 생성  예약이 되었을때는 생성만 필요할듯??
  @PostMapping("/payments/paymentInsert")
  public Long paymentInsert(@RequestBody Payment payment) {

   System.out.println("payment.getBookingId :" + payment.getBookingId());
   System.out.println("payment.getPaymentId :" + payment.getPaymentId());

   Long paymentId = paymentService.paymentInsert(payment);

   if (paymentId > 0) {
    return paymentId;     // 성공시 생성된 ID를 실패시 0 리턴
   } else {
    return makePaymentFail;
   }
  }

 }
