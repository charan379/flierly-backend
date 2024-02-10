package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.AreaDTO;
import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.entity.Area;
import com.ctytech.flierly.address.entity.PostalIdentity;
import com.ctytech.flierly.address.exception.AreaServiceException;
import com.ctytech.flierly.address.mapper.AreaMapper;
import com.ctytech.flierly.address.mapper.PostalIdentityMapper;
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

    @Autowired
    private PostalIdentityMapper postalIdentityMapper;

    @Override
    public AreaDTO save(AreaDTO areaDTO) throws AreaServiceException {
        Area newArea = areaMapper.toEntity(areaDTO);
        return areaMapper.toDTO(areaRepository.save(newArea));
    }

    @Override
    public AreaDTO fetch(Long id) throws AreaServiceException {
        Area area = areaRepository.findById(id).orElseThrow(() -> new AreaServiceException("AreaService.NOT_FOUND"));
        return areaMapper.toDTO(area);
    }

    @Override
    public List<AreaDTO> fetchAllByPI(Long postalIdentityId) throws AreaServiceException {
        return areaRepository.findAllByPostalIdentityId(postalIdentityId).stream().map((area) -> areaMapper.toDTO(area)).toList();
    }

    @Override
    public AreaDTO modify(Long id, AreaDTO update) throws AreaServiceException {
        Area area = areaRepository.findById(id).orElseThrow(() -> new AreaServiceException("AreaService.NOT_FOUND"));

        Long existingPostalId = Optional.ofNullable(area.getPostalIdentity()).map(PostalIdentity::getId).orElse(null);
        Long newPostalId = Optional.ofNullable(update.getPostalIdentity()).map(PostalIdentityDTO::getId).orElse(null);

        if (existingPostalId == null && newPostalId != null)
            area.setPostalIdentity(postalIdentityMapper.toEntity(update.getPostalIdentity()));

        area.setName(update.getName());

        return areaMapper.toDTO(areaRepository.save(area));
    }

    @Override
    public void remove(Long id) throws AreaServiceException {

    }
}
