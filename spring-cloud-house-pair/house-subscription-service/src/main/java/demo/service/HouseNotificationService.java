package demo.service;

import demo.model.HouseData;
import demo.model.HouseNotification;
import demo.model.Subscription;

import java.util.List;

public interface HouseNotificationService {
    List<HouseNotification> prepareNotifications(HouseData house);
}
