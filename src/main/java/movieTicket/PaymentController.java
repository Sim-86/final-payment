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

  @PostMapping("/payments/test")
  public Long test(@RequestBody Payment payment) {
   return paymentService.paymentInsert(payment);
  }
 }
