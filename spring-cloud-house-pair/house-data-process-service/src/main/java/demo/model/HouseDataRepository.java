package demo.model;


import demo.model.HouseData;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface HouseDataRepository extends PagingAndSortingRepository<HouseData, Long> {

}
