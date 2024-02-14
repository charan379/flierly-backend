package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.AreaDTO;
import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.entity.Area;
import com.ctytech.flierly.address.exception.AreaServiceException;
import com.ctytech.flierly.address.mapper.AreaMapper;
import com.ctytech.flierly.address.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public AreaDTO save(AreaDTO areaDTO) throws AreaServiceException {
        Long pId = Optional.ofNullable(areaDTO.getPostalIdentity()).map(PostalIdentityDTO::getId).orElseThrow(() -> new AreaServiceException("AreaService.PID_ABSENT"));

        if (existsByPostalIdAndName(pId, areaDTO.getName()))
            throw new AreaServiceException("AreaService.ALREADY_EXISTS");

        Area newArea = areaMapper.toEntity(areaDTO);
        return areaMapper.toDTO(areaRepository.save(newArea));
    }

    @Override
    public AreaDTO fetch(Long id) throws AreaServiceException {
        Area area = areaRepository.findById(id).orElseThrow(() -> new AreaServiceException("AreaService.NOT_FOUND"));
        return areaMapper.toDTO(area);
    }

    @Override
    public List<AreaDTO> fetchAllByPI(Long postalIdentityId) {
        return areaRepository.findAllByPostalIdentityId(postalIdentityId).stream().map((area) -> areaMapper.toDTOWithoutPI(area)).toList();
    }

    @Override
    public AreaDTO modify(Long id, AreaDTO update) throws AreaServiceException {
        Area area = areaRepository.findById(id).orElseThrow(() -> new AreaServiceException("AreaService.NOT_FOUND"));

        if (area.getName().equalsIgnoreCase(update.getName())) return areaMapper.toDTO(area);

        if (existsByPostalIdAndName(area.getPostalIdentity().getId(), update.getName()))
            throw new AreaServiceException("AreaService.ALREADY_EXISTS");

        area.setName(update.getName());

        return areaMapper.toDTO(areaRepository.save(area));
    }

    @Override
    public void remove(Long id) throws AreaServiceException {

    }

    @Override
    public Boolean existsByPostalIdAndName(Long pId, String name) {
        return areaRepository.existsByPidAndName(pId, name);
    }
}
