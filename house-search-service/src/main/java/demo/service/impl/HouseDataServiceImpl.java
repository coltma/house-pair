package demo.service.impl;

import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.service.HouseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class HouseDataServiceImpl implements HouseDataService{

    private HouseDataRepository houseDataRepository;

    @Autowired
    public HouseDataServiceImpl(HouseDataRepository houseDataRepository) {
        this.houseDataRepository = houseDataRepository;
    }

    @Override
    public Page<HouseData> findByCounty(String county, Pageable pageable) {
        return null;
    }

    @Override
    public Page<HouseData> findByCity(String city, Pageable pageable) {
        return null;
    }

    @Override
    public Page<HouseData> findByPostal_code(int postal_code, Pageable pageable) {
        return null;
    }
}
