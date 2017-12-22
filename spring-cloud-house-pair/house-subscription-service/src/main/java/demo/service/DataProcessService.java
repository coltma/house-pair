package demo.service;

import demo.model.HouseData;
import demo.model.HouseNotification;
import demo.model.Subscription;

public interface DataProcessService {
    HouseNotification generateNotification(Subscription subscription, HouseData house);
    String calculatePercent(double prev, double crt);
    String combineAddress(HouseData house);
}
