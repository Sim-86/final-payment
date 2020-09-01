package movieTicket;

import movieTicket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverUnbooked_CancelPayment(@Payload Unbooked unbooked){

        if(unbooked.isMe()){

            System.out.println("##### listener CancelPayment : " + unbooked.toJson());

            Long bookingId = unbooked.getBookingId();
            System.out.println("##### unbooked.getBookingId : " + unbooked.getBookingId());

            //Payment payment = new Payment();
            //unbooked.setBookingStatus("canceled");  // bookingStatus 상태 변경은 booking policyHandler에서

            // Correlation id 는 'bookingId'
            paymentRepository.findById(Long.valueOf(unbooked.getBookingId())).ifPresent((payment)->{
                payment.setPaymentStatus("canceled");
                paymentRepository.save(payment);
            });
        }
    }

}
