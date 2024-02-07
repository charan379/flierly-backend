package com.ctytech.flierly.asset.service;

import com.ctytech.flierly.asset.dto.AssetDTO;
import com.ctytech.flierly.asset.dto.AssetPageRequestDTO;
import com.ctytech.flierly.asset.exception.AssetServiceException;
import org.springframework.data.domain.Page;

public interface AssetService {

    AssetDTO save(AssetDTO assetDTO) throws AssetServiceException;

    AssetDTO fetch(Long id) throws AssetServiceException;

    AssetDTO fetch(String serialNumber) throws AssetServiceException;

    Page<AssetDTO> fetchPage(AssetPageRequestDTO assetPageRequestDTO) throws AssetServiceException;

    AssetDTO modify(Long id, AssetDTO update) throws AssetServiceException;

    void remove(Long id) throws AssetServiceException;

    Boolean serialExists(String serialNumber) throws AssetServiceException;


}
