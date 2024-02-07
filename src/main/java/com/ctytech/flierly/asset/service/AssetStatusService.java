package com.ctytech.flierly.asset.service;

import com.ctytech.flierly.asset.dto.AssetStatusDTO;
import com.ctytech.flierly.asset.dto.AssetStatusPageRequestDTO;
import com.ctytech.flierly.asset.exception.AssetServiceException;
import org.springframework.data.domain.Page;

public interface AssetStatusService {

    AssetStatusDTO save(AssetStatusDTO assetStatusDTO) throws AssetServiceException;

    AssetStatusDTO fetch(Long id) throws  AssetServiceException;

    AssetStatusDTO fetch(String code) throws AssetServiceException;

    Page<AssetStatusDTO> pageQuery(AssetStatusPageRequestDTO assetStatusPageRequestDTO) throws AssetServiceException;

    AssetStatusDTO modify(AssetStatusDTO assetStatusDTO) throws AssetServiceException;

    void remove(Long id) throws AssetServiceException;

    Boolean codeExists(String code) throws AssetServiceException;
}
