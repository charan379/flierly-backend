package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.exception.PostalIdentityServiceException;

import java.util.List;

public interface PostalIdentityService {

    PostalIdentityDTO save(PostalIdentityDTO postalIdentityDTO) throws PostalIdentityServiceException;

    PostalIdentityDTO fetch(Long id) throws PostalIdentityServiceException;

    PostalIdentityDTO fetchByCountryIdAndPincode(Long countryId, Integer pincode) throws PostalIdentityServiceException;

    List<PostalIdentityDTO> fetchAllByCityId(Long cityId);

    PostalIdentityDTO modify(Long id, PostalIdentityDTO update) throws PostalIdentityServiceException;

    PostalIdentityDTO remove(Long id) throws PostalIdentityServiceException;

    Boolean existsByCountryIdAndPincode(Long countryId, Integer pincode);

    Boolean isCityBelongsToCountry(Long cityId, Long countryId);
}
