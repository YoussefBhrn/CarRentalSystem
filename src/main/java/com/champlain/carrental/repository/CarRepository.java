package com.champlain.carrental.repository;
import com.champlain.carrental.domain.*;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface CarRepository extends JpaRepository<Car,Long> {List<Car> findByStatus(CarStatus status); List<Car> findByCategory(CarCategory category); boolean existsByLicensePlateAndIdNot(String plate,Long id);}