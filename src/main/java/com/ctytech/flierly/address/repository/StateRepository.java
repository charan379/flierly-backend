package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT CASE WHEN count(S) > 0 THEN true ELSE false END FROM State S WHERE S.gstCode= :gstCode AND S.country.id= :countryId")
    Boolean existsByGstCodeAndCountryId(@Param("gstCode") Integer gstCode, @Param("countryId") Long countryId);

    @Query("SELECT CASE WHEN count(S) > 0 THEN true ELSE false END FROM State S WHERE S.code= :code AND S.country.id= :countryId")
    Boolean existsByCodeAndCountryId(@Param("code") String code, @Param("countryId") Long countryId);

    @Query("SELECT S FROM State S WHERE S.country.id= :countryId")
    List<State> findAllByCountryId(@Param("countryId") Long countryId);

    @Query("SELECT S FROM State S WHERE S.code= :code AND S.country.id= :countryId")
    Optional<State> findByCodeAndCountryId(@Param("code") String code, @Param("countryId") Long countryId);
}
