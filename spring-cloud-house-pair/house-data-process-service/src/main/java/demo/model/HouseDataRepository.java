package demo.model;


import demo.model.HouseData;
import org.springframework.data.geo.Circle;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface HouseDataRepository extends PagingAndSortingRepository<HouseData, Long>, HouseDataRepositoryCustom {
    List<HouseData> findByLocationWithin(Circle circle);
    HouseData findById(String id);
}
