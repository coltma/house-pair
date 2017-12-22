package demo.rest;

import demo.model.Subscription;
import demo.service.impl.HouseSubscriptionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class HouseSubscriptionRestController {

    private HouseSubscriptionServiceImpl service;

    @Autowired
    public HouseSubscriptionRestController(HouseSubscriptionServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void subscription(@RequestBody Subscription subscription) {
        this.service.subscribe(subscription);
    }

}
