package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT CASE WHEN count(A) > 0 THEN true ELSE false END FROM Area A WHERE A.id= ?1 AND A.postalIdentity.id= ?2 AND A.postalIdentity.city.id= ?3 AND A.postalIdentity.city.district.id= ?4 AND A.postalIdentity.city.district.state.id= ?5 AND A.postalIdentity.city.district.state.country.id= ?6")
    Boolean validAreaMapping(Long areaId, Long pId, Long cityId, Long districtId, Long stateId, Long countryId);
}
