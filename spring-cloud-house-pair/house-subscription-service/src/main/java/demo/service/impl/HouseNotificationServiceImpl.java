package demo.service.impl;

import demo.model.HouseData;
import demo.model.HouseNotification;
import demo.model.HouseSubscriptionRepository;
import demo.model.Subscription;
import demo.service.DataProcessService;
import demo.service.HouseNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class HouseNotificationServiceImpl implements HouseNotificationService {

    @Autowired
    private DataProcessService service;

    @Autowired
    private HouseSubscriptionRepository repository;

    @Override
    public List<HouseNotification> prepareNotifications(HouseData house) {
        List<Subscription> subscriptions = this.repository.findTargetSubscriptions(house);
        log.info(String.format("Target [%d] subscriptions ", subscriptions.size()));
        List<HouseNotification> notifications = new ArrayList<>();
        subscriptions.forEach(item -> {
            notifications.add(service.generateNotification(item, house));
        });
        log.info(String.format("Will notify [%d] users", notifications.size()));
        return notifications;
    }
}
