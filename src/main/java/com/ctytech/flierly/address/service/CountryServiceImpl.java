package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CountryDTO;
import com.ctytech.flierly.address.entity.Country;
import com.ctytech.flierly.address.exception.CountryServiceException;
import com.ctytech.flierly.address.mapper.CountryMapper;
import com.ctytech.flierly.address.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "countryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public CountryDTO save(CountryDTO countryDTO) throws CountryServiceException {

        if (existsByCode(countryDTO.getCode())) throw new CountryServiceException("CountryService.CODE_EXISTS");

        if (existsByDialingCode(countryDTO.getDialingCode()))
            throw new CountryServiceException("CountryService.DIALING_CODE_EXISTS");

        Country country = countryRepository.save(countryMapper.toEntity(countryDTO));

        return countryMapper.toDTO(country);
    }

    @Override
    public CountryDTO fetch(Long id) throws CountryServiceException {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryServiceException("CountryService.NOT_FOUND"));
        return countryMapper.toDTO(country);
    }

    @Override
    public CountryDTO fetch(String code) throws CountryServiceException {
        Country country = countryRepository.findByCode(code).orElseThrow(() -> new CountryServiceException("CountryService.NOT_FOUND"));
        return countryMapper.toDTO(country);
    }

    @Override
    public List<CountryDTO> fetchAll() throws CountryServiceException {
        return countryRepository.findAll().stream().map(country -> countryMapper.toDTO(country)).toList();
    }

    @Override
    public CountryDTO modify(Long id, CountryDTO update) throws CountryServiceException {

        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryServiceException("CountryService.NOT_FOUND"));

        if (country.getName().equals(update.getName())) return countryMapper.toDTO(country);

        country.setName(update.getName());
        countryRepository.save(country);

        return countryMapper.toDTO(country);
    }

    @Override
    public void remove(Long id) throws CountryServiceException {

    }

    @Override
    public Boolean existsByCode(String code) throws CountryServiceException {
        return countryRepository.existsByCode(code);
    }

    @Override
    public Boolean existsByDialingCode(Integer dialingCode) throws CountryServiceException {
        return countryRepository.existsByDialingCode(dialingCode);
    }
}
