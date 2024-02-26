package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.AreaDTO;
import com.ctytech.flierly.address.exception.AreaServiceException;

import java.util.List;

public interface AreaService {

    AreaDTO save(AreaDTO areaDTO) throws AreaServiceException;

    AreaDTO fetch(Long id) throws AreaServiceException;

    List<AreaDTO> fetchAllByPI(Long postalIdentityId) throws AreaServiceException;

    AreaDTO modify(Long id, AreaDTO update) throws AreaServiceException;

    void remove(Long id) throws AreaServiceException;

    Boolean existsByPostalIdAndName(Long pId, String name);
}
