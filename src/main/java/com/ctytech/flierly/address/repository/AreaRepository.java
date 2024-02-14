package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query(value = "SELECT * FROM areas A WHERE A.postal_id= :pId", nativeQuery = true)
    List<Area> findAllByPostalIdentityId(@Param("pId") Long pId);

    @Query(value = "SELECT CASE WHEN count(A) > 0 THEN true ELSE false END FROM areas A WHERE A.postal_id= :pId AND A.name ILIKE :name", nativeQuery = true)
    Boolean existsByPidAndName(@Param("pId") Long pId, @Param("name") String name);
}
