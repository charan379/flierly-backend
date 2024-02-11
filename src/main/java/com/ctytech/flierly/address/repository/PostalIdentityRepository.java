package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.PostalIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalIdentityRepository extends JpaRepository<PostalIdentity, Long> {

    @Query("SELECT CASE WHEN count(PI) > 0 THEN true ELSE false END FROM PostalIdentity PI WHERE PI.city.id = :cityId AND PI.pinCode= :pinCode")
    boolean existsByCityIdAndPincode(@Param("cityId") Long cityId, @Param("pinCode") Integer pincode);
}
