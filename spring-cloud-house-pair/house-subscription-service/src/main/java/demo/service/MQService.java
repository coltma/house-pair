package demo.service;


import demo.model.HouseNotification;

public interface MQService {
    void produce(HouseNotification notification);
}
