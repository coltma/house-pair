package demo.service;

import demo.model.HouseData;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface HouseDataService {
    Page<HouseData> findByCounty(String county, Pageable pageable);
    void save(HouseData houseData);
}
