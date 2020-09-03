
package movieTicket.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="promotion", url="${api.url.promotion}")
public interface PromotionService {

    @RequestMapping(method= RequestMethod.POST, path="/promotions")
    public long applyDiscount(@RequestBody Promotion promotion);

}