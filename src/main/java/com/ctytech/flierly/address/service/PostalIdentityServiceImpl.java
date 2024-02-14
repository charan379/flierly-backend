package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CityDTO;
import com.ctytech.flierly.address.dto.CountryDTO;
import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.entity.PostalIdentity;
import com.ctytech.flierly.address.exception.PostalIdentityServiceException;
import com.ctytech.flierly.address.mapper.PostalIdentityMapper;
import com.ctytech.flierly.address.repository.PostalIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "postalIdentityService")
public class PostalIdentityServiceImpl implements PostalIdentityService {

    @Autowired
    private PostalIdentityRepository postalIdentityRepository;

    @Autowired
    private PostalIdentityMapper postalIdentityMapper;

    @Override
    public PostalIdentityDTO save(PostalIdentityDTO postalIdentityDTO) throws PostalIdentityServiceException {

        Long cityId = Optional.ofNullable(postalIdentityDTO.getCity()).map(CityDTO::getId).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentityService.CITY_ID_ABSENT"));
        Long countryId = Optional.ofNullable(postalIdentityDTO.getCountry()).map(CountryDTO::getId).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentityService.COUNTRY_ID_ABSENT"));
        Integer pinCode = postalIdentityDTO.getPinCode();

        if (!isCityBelongsToCountry(countryId, cityId))
            throw new PostalIdentityServiceException("PostalIdentityService.CITY_NOT_IN_COUNTRY");

        if (pinCode != null && existsByCountryIdAndPincode(countryId, pinCode))
            throw new PostalIdentityServiceException("PostalIdentityService.PINCODE_AND_COUNTRY_EXISTS");

        PostalIdentity postalIdentity = postalIdentityRepository.save(postalIdentityMapper.toEntity(postalIdentityDTO));

        return postalIdentityMapper.toDTO(postalIdentity, true);
    }

    @Override
    public PostalIdentityDTO fetch(Long id) throws PostalIdentityServiceException {
        PostalIdentity postalIdentity = postalIdentityRepository.findById(id).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentityService.NOT_FOUND"));
        return postalIdentityMapper.toDTO(postalIdentity);
    }

    @Override
    public PostalIdentityDTO fetchByCountryIdAndPincode(Long countryId, Integer pincode) throws PostalIdentityServiceException {
        PostalIdentity postalIdentity = postalIdentityRepository.fetchByCountryIdAndPincode(countryId, pincode).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentityService.NOT_FOUND"));
        return postalIdentityMapper.toDTO(postalIdentity);
    }

    @Override
    public List<PostalIdentityDTO> fetchAllByCityId(Long cityId) {
        return postalIdentityRepository.fetchAllByCityId(cityId).stream().map(postalIdentity -> postalIdentityMapper.toDTO(postalIdentity, true)).toList();
    }

    @Override
    public PostalIdentityDTO modify(Long id, PostalIdentityDTO update) throws PostalIdentityServiceException {
        PostalIdentity postalIdentity = postalIdentityRepository.findById(id).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentityService.NOT_FOUND"));

        Integer existingPincode = postalIdentity.getPinCode();
        Integer newPincode = update.getPinCode();

        if (existingPincode == null && newPincode != null) {
            if (existsByCountryIdAndPincode(postalIdentity.getCountry().getId(), newPincode))
                throw new PostalIdentityServiceException("PostalIdentity.PINCODE_AND_COUNTRY_EXISTS");
            postalIdentity.setPinCode(newPincode);
        }

        return postalIdentityMapper.toDTO(postalIdentityRepository.save(postalIdentity), true);
    }

    @Override
    public PostalIdentityDTO remove(Long id) throws PostalIdentityServiceException {
        return null;
    }

    @Override
    public Boolean existsByCountryIdAndPincode(Long countryId, Integer pincode) {
        return postalIdentityRepository.exitsByCountryIdAndPincode(countryId, pincode);
    }

    @Override
    public Boolean isCityBelongsToCountry(Long countryId, Long cityId) {
        return postalIdentityRepository.isCityBelongsToCountry(countryId, cityId);
    }
}
