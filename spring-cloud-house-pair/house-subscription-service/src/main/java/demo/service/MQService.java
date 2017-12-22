package demo.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import demo.model.HouseNotification;

public interface MQService {
    void produce(HouseNotification notification) throws JsonProcessingException;
}
