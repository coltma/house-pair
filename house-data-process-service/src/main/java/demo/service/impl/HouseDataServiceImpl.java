package demo.service.impl;

import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.service.HouseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class HouseDataServiceImpl implements HouseDataService {

    private HouseDataRepository repository;

    @Autowired
    public HouseDataServiceImpl(HouseDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<HouseData> findByCounty(String county, Pageable pageable) {
        return null;
    }

    @Override
    public void save(HouseData houseData) {
        this.repository.save(houseData);
    }
}
