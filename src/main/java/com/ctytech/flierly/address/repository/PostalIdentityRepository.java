package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.PostalIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostalIdentityRepository extends JpaRepository<PostalIdentity, Long> {


    @Query("SELECT PI FROM PostalIdentity PI WHERE PI.country.id= :countryId AND PI.pinCode= :pincode")
    Optional<PostalIdentity> fetchByCountryIdAndPincode(@Param("countryId") Long countryId, @Param("pincode") Integer pincode);

    @Query("SELECT PI FROM PostalIdentity PI WHERE PI.city.id= :cityId")
    List<PostalIdentity> fetchAllByCityId(@Param("cityId") Long cityId);

    @Query("SELECT CASE WHEN count(PI) > 0 THEN true ELSE false END FROM PostalIdentity PI WHERE PI.country.id= :countryId AND PI.pinCode= :pincode")
    Boolean exitsByCountryIdAndPincode(@Param("countryId") Long countryId, @Param("pincode") Integer pincode);

    @Query("SELECT CASE WHEN count(C) > 0 THEN true ELSE false END FROM City C WHERE C.id= :cityId AND C.district.state.country.id= :countryId")
    Boolean isCityBelongsToCountry(@Param("countryId") Long countryId, @Param("cityId") Long cityId);
}