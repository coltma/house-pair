package demo.service.impl;


import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    // in miles.
    private static final double earthRadius = 3963.2;

    private HouseDataRepository repository;

    @Autowired
    public HouseServiceImpl(HouseDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<HouseData> findByCounty(String county, Pageable pageable) {
        return this.repository.findByCounty(county, pageable);
    }

    @Override
    public List<HouseData> findByLocationWithin(double lat, double lng, double radius) {
        // X for longitude, Y for latitude in Mongodb.
        return this.repository.findByLocationWithin(new Circle(lng, lat, radius / earthRadius));

    }
}
