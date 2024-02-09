package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.PostalIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalIdentityRepository extends JpaRepository<PostalIdentity, Long> {

    @Query("SELECT CASE WHEN count(PI) > 0 THEN true ELSE false END FROM postalIdentity PI WHERE PI.cityId = :cityId AND PI.pinCode= :pinCode")
    Boolean existsByCityIdAndPincode(@Param("cityId") Long cityId, @Param("pincode") Integer pincode);
}
