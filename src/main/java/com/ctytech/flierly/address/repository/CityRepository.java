package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT C FROM City C WHERE C.code= :code AND C.district.id= :districtId")
    Optional<City> findByCodeAndDistrictId(@Param("code") String code, @Param("districtId") Long districtId);

    @Query("SELECT C FROM City C WHERE C.district.id= :districtId")
    List<City> findAllByDistrictId(@Param("districtId") Long districtId);

    @Query("SELECT CASE WHEN count(C) > 0 THEN true ELSE false END FROM City C WHERE C.code= :code AND C.district.id= :districtId")
    Boolean existsByCodeAndDistrictId(@Param("code") String code, @Param("districtId") Long districtId);

}
