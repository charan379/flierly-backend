package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.DistrictDTO;
import com.ctytech.flierly.address.dto.StateDTO;
import com.ctytech.flierly.address.entity.District;
import com.ctytech.flierly.address.exception.DistrictServiceException;
import com.ctytech.flierly.address.mapper.DistrictMapper;
import com.ctytech.flierly.address.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "districtService")
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public DistrictDTO save(DistrictDTO districtDTO) throws DistrictServiceException {
        Long stateId = Optional.ofNullable(districtDTO.getState()).map(StateDTO::getId).orElseThrow(() -> new DistrictServiceException("DistrictService.STATE_ID_ABSENT"));

        Integer landlineCode = districtDTO.getLandlineCode();

        if (existsByCodeAndStateId(districtDTO.getCode(), stateId))
            throw new DistrictServiceException("DistrictService.CODE_ALREADY_EXISTS");

        if (landlineCode != null && existsByLandlineCodeAndStateId(landlineCode, stateId))
            throw new DistrictServiceException("DistrictService.LANDLINE_CODE_ALREADY_EXISTS");

        District district = districtMapper.toEntity(districtDTO);

        return districtMapper.toDTO(districtRepository.save(district));
    }

    @Override
    public DistrictDTO fetch(Long id) throws DistrictServiceException {
        District district = districtRepository.findById(id).orElseThrow(() -> new DistrictServiceException("DistrictService.NOT_FOUND"));
        return districtMapper.toDTO(district);
    }

    @Override
    public DistrictDTO fetch(String code, Long stateId) throws DistrictServiceException {
        District district = districtRepository.findByCodeAndStateId(code, stateId).orElseThrow(() -> new DistrictServiceException("DistrictService.NOT_FOUND"));
        return districtMapper.toDTO(district);
    }

    @Override
    public List<DistrictDTO> fetchAllByStateId(Long stateId) throws DistrictServiceException {
        return districtRepository.findAllByStateId(stateId).stream().map(district -> districtMapper.toDTO(district)).toList();
    }

    @Override
    public DistrictDTO modify(Long id, DistrictDTO update) throws DistrictServiceException {
        District district = districtRepository.findById(id).orElseThrow(() -> new DistrictServiceException("DistrictService.NOT_FOUND"));

        district.setName(update.getName());

        Integer newLandlineCode = update.getLandlineCode();

        if (newLandlineCode != null && !newLandlineCode.equals(district.getLandlineCode())) {
            if (existsByLandlineCodeAndStateId(newLandlineCode, district.getState().getId()))
                throw new DistrictServiceException("DistrictService.LANDLINE_CODE_ALREADY_EXISTS");

            district.setLandlineCode(update.getLandlineCode());
        }

        return districtMapper.toDTO(districtRepository.save(district));
    }

    @Override
    public void remove(Long id) throws DistrictServiceException {

    }

    @Override
    public Boolean existsByCodeAndStateId(String code, Long stateId) {
        return districtRepository.existsByCodeAndStateId(code, stateId);
    }

    @Override
    public Boolean existsByLandlineCodeAndStateId(Integer landlineCode, Long stateId) {
        return districtRepository.existsByLandlineCodeAndStateId(landlineCode, stateId);
    }
}
