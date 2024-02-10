package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.Area;
import com.ctytech.flierly.address.exception.AreaServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("SELECT A FROM Area A WHERE A.postalIdentity.id= :pId")
    List<Area> findAllByPostalIdentityId(@Param("pId") Long pId) throws AreaServiceException;
}
