package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("SELECT D FROM District D WHERE D.code= :code AND D.state.id= :stateId")
    Optional<District> findByCodeAndStateId(@Param("code") String code, @Param("stateId") Long stateId);

    @Query("SELECT D FROM District D WHERE D.state.id= :stateId")
    List<District> findAllByStateId(@Param("stateId") Long stateId);

    @Query("SELECT CASE WHEN count(D) > 0 THEN true ELSE false END FROM District D WHERE D.code= :code AND D.state.id= :stateId")
    Boolean existsByCodeAndStateId(@Param("code") String code, @Param("stateId") Long stateId);

    @Query("SELECT CASE WHEN count(D) > 0 THEN true ELSE false END FROM District D WHERE D.landlineCode= :landlineCode AND D.state.id= :stateId")
    Boolean existsByLandlineCodeAndStateId(@Param("landlineCode") Integer landlineCode, @Param("stateId") Long stateId);

}
