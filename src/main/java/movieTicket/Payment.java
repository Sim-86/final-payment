package movieTicket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long paymentId;
    private Long bookingId;
    private Double totalPrice;
    private String paymentStatus;
    private String paymentType;
    private Long seatId;

    @PostPersist
    public void onPostPersist(){
        // to do eventHandler 처리 후 주석 해제
//        PaymentSucceeded paymentSucceeded = new PaymentSucceeded();
//        BeanUtils.copyProperties(this, paymentSucceeded);
//        paymentSucceeded.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        movieTicket.external.Promotion promotion = new movieTicket.external.Promotion();
        // mappings goes here

        // mappings goes here
        promotion.setPaymentId(this.getPaymentId());
        promotion.setPaymentType(this.getPaymentType());
    //    promotion.setPaymentType("card");

        System.out.println("Payment 호출 : paymentId " + promotion.getPaymentId());
        System.out.println("Payment 호출 : paymentType " + promotion.getPaymentType());

        //Promotion.setId(this.set);
        long discountRate = PaymentApplication.applicationContext.getBean(movieTicket.external.PromotionService.class)
                .applyDiscount(promotion);
        System.out.println("discountRate : " + discountRate);

        SelectedDiscount selectedDiscount = new SelectedDiscount();
        BeanUtils.copyProperties(this, selectedDiscount);
        selectedDiscount.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
        PaymentCanceled paymentCanceled = new PaymentCanceled();
        BeanUtils.copyProperties(this, paymentCanceled);
        paymentCanceled.publishAfterCommit();
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

}
