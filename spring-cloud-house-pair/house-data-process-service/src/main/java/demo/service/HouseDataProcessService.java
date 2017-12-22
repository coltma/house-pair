package demo.service;

import demo.model.HouseData;

import java.util.List;

public interface HouseDataProcessService {
    /**
     * duplicate house: location, bedroom, bathroom, house_number, area_size
     * @return null or duplicate house.
     */
    void save(HouseData house);
    List<HouseData> findByLocationWithin(double lat, double lng, double radius);
    HouseData findById(String id);
}
