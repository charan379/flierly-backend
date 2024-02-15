package com.ctytech.flierly.address.servicetests;

import com.ctytech.flierly.address.dto.*;
import com.ctytech.flierly.address.entity.Address;
import com.ctytech.flierly.address.exception.AddressServiceException;
import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.address.repository.AddressRepository;
import com.ctytech.flierly.address.service.AddressServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AddressServiceTests {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressMapper addressMapper;

    private AddressDTO addressDTO;

    @BeforeEach
    void setUp() {
        addressDTO = new AddressDTO();
        addressDTO.setId(1000L);
        addressDTO.setCountry(new CountryDTO());
        addressDTO.getCountry().setId(1000L);
        addressDTO.setState(new StateDTO());
        addressDTO.getState().setId(1000L);
        addressDTO.setDistrict(new DistrictDTO());
        addressDTO.getDistrict().setId(1000L);
        addressDTO.setCity(new CityDTO());
        addressDTO.getCity().setId(1000L);
        addressDTO.setPostalIdentity(new PostalIdentityDTO());
        addressDTO.getPostalIdentity().setId(1000L);
        addressDTO.setArea(new AreaDTO());
        addressDTO.getArea().setId(1000L);
    }

    @Test
    @DisplayName("Invalid Test: Create new address without country")
    void saveWithoutCountry() {
        addressDTO.setCountry(null);
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.save(addressDTO));
        Assertions.assertEquals("AddressService.COUNTRY_ID_ABSENT", exception.getMessage());
    }

    @Test
    @DisplayName("Invalid Test: Create new address without state")
    void saveWithOutState() {
        addressDTO.setState(null);
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.save(addressDTO));
        Assertions.assertEquals("AddressService.STATE_ID_ABSENT", exception.getMessage());
    }

    @Test
    @DisplayName("Invalid Test: Create new address without district")
    void saveWithOutDistrict() {
        addressDTO.setDistrict(null);
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.save(addressDTO));
        Assertions.assertEquals("AddressService.DISTRICT_ID_ABSENT", exception.getMessage());
    }

    @Test
    @DisplayName("Invalid Test: Create new address without city")
    void saveWithOutCity() {
        addressDTO.setCity(null);
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.save(addressDTO));
        Assertions.assertEquals("AddressService.CITY_ID_ABSENT", exception.getMessage());
    }

    @Test
    @DisplayName("Invalid Test: Create new address without Postal Identity")
    void saveWithOutPI() {
        addressDTO.setPostalIdentity(null);
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.save(addressDTO));
        Assertions.assertEquals("AddressService.POSTAL_ID_ABSENT", exception.getMessage());
    }

    @Test
    @DisplayName("Invalid Test: Create new address without area")
    void saveWithOutArea() {
        addressDTO.setArea(null);
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.save(addressDTO));
        Assertions.assertEquals("AddressService.AREA_ID_ABSENT", exception.getMessage());
    }

    @Test
    @DisplayName("Invalid Test: Create new address with invalid area mapping")
    void saveWithInvalidAreaMapping() {
        when(addressRepository.validAreaMapping(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(false);
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.save(addressDTO));
        Assertions.assertEquals("AddressService.INVALID_AREA_MAPPING", exception.getMessage());
    }

    @Test
    @DisplayName("Valid Test: Create new address with valid data")
    void saveValidAddress() throws AddressServiceException {
        when(addressRepository.validAreaMapping(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        Address address = new Address();
        address.setId(1000L);
        when(addressRepository.save(Mockito.any())).thenReturn(address);
        when(addressMapper.toDTO(Mockito.any())).thenReturn(addressDTO);
        AddressDTO createdAddress = addressService.save(addressDTO);
        Assertions.assertEquals(addressDTO.getId(), createdAddress.getId());
    }

    @Test
    @DisplayName("Invalid Test: Get Address By invalid id")
    void getNonExistingAddress() {
        when(addressRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        AddressServiceException exception = Assertions.assertThrows(AddressServiceException.class, () -> addressService.fetch(Mockito.anyLong()));
        Assertions.assertEquals("AddressService.NOT_FOUND", exception.getMessage());
    }

    @Test
    @DisplayName("Valid Test: Get Address by valid id")
    void getExistingAddress() throws AddressServiceException {
        Address address = new Address();
        address.setId(1000L);
        when(addressRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(address));
        addressDTO.setId(1000L);
        when(addressMapper.toDTO(Mockito.any())).thenReturn(addressDTO);
        AddressDTO fetchedAddress = addressService.fetch(1000L);
        Assertions.assertEquals(addressDTO.getId(), fetchedAddress.getId());
    }


}
