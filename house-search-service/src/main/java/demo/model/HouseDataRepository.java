package demo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseDataRepository extends PagingAndSortingRepository<HouseData, String> {
    // additional custom finder methods go here
    List<HouseData> findByLocationWithin(Circle circle);

    Page<HouseData> findByCounty(@Param(value = "county") String county, Pageable pageable);
}
