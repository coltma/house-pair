package demo.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface HouseDataRepository extends PagingAndSortingRepository<HouseData, String> {
}
