package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.exception.PostalIdentityServiceException;

public interface PostalIdentityService {

    PostalIdentityDTO save(PostalIdentityDTO postalIdentityDTO) throws PostalIdentityServiceException;

    PostalIdentityDTO fetch(Long id) throws PostalIdentityServiceException;

    PostalIdentityDTO modify(Long id, PostalIdentityDTO update) throws PostalIdentityServiceException;

    PostalIdentityDTO remove(Long id) throws PostalIdentityServiceException;

    Boolean existsByCityIdAndPincode(Long cityId, Integer pincode) throws PostalIdentityServiceException;


}
