package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CityDTO;
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

        Long cityId = Optional.ofNullable(postalIdentityDTO.getCity()).map(CityDTO::getId).orElse(null);

        Integer pinCode = postalIdentityDTO.getPinCode();

//        if (cityId != null && pinCode != null && existsByCityIdAndPincode(cityId, pinCode))
//            throw new PostalIdentityServiceException("PostalIdentity.PINCODE_AND_CITY_EXISTS");

        PostalIdentity postalIdentity = postalIdentityRepository.save(postalIdentityMapper.toEntity(postalIdentityDTO));

        return postalIdentityMapper.toDTO(postalIdentity);
    }

    @Override
    public PostalIdentityDTO fetch(Long id) throws PostalIdentityServiceException {
        PostalIdentity postalIdentity = postalIdentityRepository.findById(id).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentity.NOT_FOUND"));
        return postalIdentityMapper.toDTO(postalIdentity);
    }

    @Override
    public PostalIdentityDTO fetchByCountryIdAndPincode(Long countryId, Integer pincode) throws PostalIdentityServiceException {
        PostalIdentity postalIdentity = postalIdentityRepository.fetchByCountryIdAndPincode(countryId, pincode).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentity.NOT_FOUND"));
        return postalIdentityMapper.toDTO(postalIdentity);
    }

    @Override
    public List<PostalIdentityDTO> fetchAllByCityId(Long cityId) {
        return postalIdentityRepository.fetchAllByCityId(cityId).stream().map(postalIdentity -> postalIdentityMapper.toDTO(postalIdentity)).toList();
    }

    @Override
    public PostalIdentityDTO modify(Long id, PostalIdentityDTO update) throws PostalIdentityServiceException {
        PostalIdentity postalIdentity = postalIdentityRepository.findById(id).orElseThrow(() -> new PostalIdentityServiceException("PostalIdentity.NOT_FOUND"));

        Integer existingPincode = postalIdentity.getPinCode();
        Integer newPincode = update.getPinCode();

        if (existingPincode == null) postalIdentity.setPinCode(newPincode);

        return postalIdentityMapper.toDTO(postalIdentityRepository.save(postalIdentity));
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
