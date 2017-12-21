package demo.service;

import demo.model.HouseData;

public interface HouseDataProcessService {
    /**
     * duplicate house: location, bedroom, bathroom, house_number, area_size
     * @return null or duplicate house.
     */
    HouseData get();

    HouseData findSameHouse(HouseData house);

    void save(HouseData house);
}
