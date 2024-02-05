package com.ctytech.flierly.uom.repository;

import com.ctytech.flierly.uom.entity.Uom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UomRepository extends JpaRepository<Uom, Long> {

}
