package demo.service.impl;

import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.service.HouseDataProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseDataProcessServiceImpl implements HouseDataProcessService {

    private HouseDataRepository repository;

    @Autowired
    public HouseDataProcessServiceImpl(HouseDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public HouseData get() {
        return null;
    }

    @Override
    public HouseData findSameHouse(HouseData current) {
        return null;
    }

    @Override
    public void save(HouseData house) {
        this.repository.save(house);
    }
}
