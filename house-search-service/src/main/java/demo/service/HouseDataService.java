package demo.service;

import demo.model.HouseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseDataService {
    Page<HouseData> findByCounty(String county, Pageable pageable);
    Page<HouseData> findByCity(String city, Pageable pageable);
    //specify
    Page<HouseData> findByPostal_code(int postal_code, Pageable pageable);

}
