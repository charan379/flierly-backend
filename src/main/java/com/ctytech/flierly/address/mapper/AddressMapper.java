package com.ctytech.flierly.address.mapper;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private StateMapper stateMapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private PostalIdentityMapper postalIdentityMapper;
    @Autowired
    private AreaMapper areaMapper;

    public AddressDTO toDTO(Address address) {
        if (address == null) return null;
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setIsActive(address.getIsActive());
        dto.setIsPrimary(address.getIsPrimary());
        dto.setLine1(address.getLine1());
        dto.setLine2(address.getLine2());
        dto.setLine3(address.getLine3());
        dto.setCountry(countryMapper.toDTO(address.getCountry()));
        dto.setState(stateMapper.toDTO(address.getState(), true));
        dto.setDistrict(districtMapper.toDTO(address.getDistrict(), true));
        dto.setCity(cityMapper.toDTO(address.getCity(), true));
        dto.setPostalIdentity(postalIdentityMapper.toDTO(address.getPostalIdentity(), true));
        dto.setArea(areaMapper.toDTO(address.getArea(), true));
        dto.setLandMark(address.getLandMark());
        dto.setLatitude(address.getLatitude());
        dto.setLongitude(address.getLongitude());
        return dto;
    }

    public Address toEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}
