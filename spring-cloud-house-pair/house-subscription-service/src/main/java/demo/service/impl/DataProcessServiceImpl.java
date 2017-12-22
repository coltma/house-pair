package demo.service.impl;

import demo.model.HouseData;
import demo.model.HouseNotification;
import demo.model.Subscription;
import demo.service.DataProcessService;
import org.springframework.stereotype.Service;

@Service
public class DataProcessServiceImpl implements DataProcessService {

    @Override
    public HouseNotification generateNotification(Subscription subscription, HouseData house) {
        // Do not divide by zero.
        return new HouseNotification(
                subscription.getEmailAddress(),
                subscription.getPrice(),
                house.getPrice(),
                this.calculatePercent(subscription.getPrice(), house.getPrice()),
                house.getPostDate(),
                house.getAvailableDate(),
                house.getDetailUrl(),
                this.combineAddress(house)
        );
    }

    @Override
    public String calculatePercent(double prev, double crt) {
        if (Double.doubleToRawLongBits(prev) == 0) {
            return "-%";
        }
        return String.format("%.2f%%", (crt - prev) * 100 / prev);
    }

    @Override
    public String combineAddress(HouseData house) {
        StringBuilder sb = new StringBuilder();
        if (house.getHouseNumber() != -1) {
            sb.append(house.getHouseNumber());
        }

        sb.append(String.format(" (%s) %s, %s, %s, %s, %d",
                house.getRawAddress(),
                house.getCity(),
                house.getCounty(),
                house.getState(),
                house.getCountry(),
                house.getPostalCode())
        );

        return sb.toString();
    }
}



