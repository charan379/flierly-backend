package com.ctytech.flierly.uom.repository;

import com.ctytech.flierly.uom.entity.UomConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UomConversionsRepository extends JpaRepository<UomConversion, Long> {

}
