package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CountryDTO;
import com.ctytech.flierly.address.dto.StateDTO;
import com.ctytech.flierly.address.entity.State;
import com.ctytech.flierly.address.exception.StateServiceException;
import com.ctytech.flierly.address.mapper.StateMapper;
import com.ctytech.flierly.address.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "stateService")
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateMapper stateMapper;

    @Override
    public StateDTO save(StateDTO stateDTO) throws StateServiceException {
        Long countryId = Optional.ofNullable(stateDTO.getCountry()).map(CountryDTO::getId).orElseThrow(() -> new StateServiceException("StateService.COUNTRY_ID_ABSENT"));

        Integer gstCode = stateDTO.getGstCode();

        if (exitsByCodeAndCountryId(stateDTO.getCode(), countryId))
            throw new StateServiceException("StateService.CODE_ALREADY_EXISTS");

        if (gstCode != null && existsByGstCodeAndCountryId(gstCode, countryId))
            throw new StateServiceException("StateService.GST_CODE_ALREADY_EXISTS");

        State state = stateMapper.toEntity(stateDTO);

        if (stateDTO.getIsUnionTerritory() != null) state.setIsUnionTerritory(stateDTO.getIsUnionTerritory());
        else state.setIsUnionTerritory(false);

        return stateMapper.toDTO(stateRepository.save(state));
    }

    @Override
    public StateDTO fetch(Long id) throws StateServiceException {
        State state = stateRepository.findById(id).orElseThrow(() -> new StateServiceException("StateService.NOT_FOUND"));
        return stateMapper.toDTO(state);
    }

    @Override
    public StateDTO fetch(String code, Long countryId) throws StateServiceException {
        State state = stateRepository.findByCodeAndCountryId(code, countryId).orElseThrow(() -> new StateServiceException("StateService.NOT_FOUND"));
        return stateMapper.toDTO(state);
    }

    @Override
    public List<StateDTO> fetchAllByCountryId(Long countryId) throws StateServiceException {
        return stateRepository.findAllByCountryId(countryId).stream().map(state -> stateMapper.toDTO(state)).toList();
    }

    @Override
    public StateDTO modify(Long id, StateDTO update) throws StateServiceException {
        State state = stateRepository.findById(id).orElseThrow(() -> new StateServiceException("StateService.NOT_FOUND"));

        Integer newGstCode = update.getGstCode();
        Integer existingGstCode = state.getGstCode();

        if (!state.getName().equals(update.getName())) state.setName(update.getName());

        if (update.getIsUnionTerritory() != null) state.setIsUnionTerritory(update.getIsUnionTerritory());

        if (newGstCode != null && existingGstCode == null) {
            if (existsByGstCodeAndCountryId(newGstCode, state.getCountry().getId()))
                throw new StateServiceException("StateService.GST_CODE_ALREADY_EXISTS");

            state.setGstCode(update.getGstCode());
        }

        return stateMapper.toDTO(stateRepository.save(state));
    }

    @Override
    public void remove(Long id) throws StateServiceException {

    }

    @Override
    public Boolean existsByGstCodeAndCountryId(Integer gstCode, Long countryId) throws StateServiceException {
        return stateRepository.existsByGstCodeAndCountryId(gstCode, countryId);
    }

    @Override
    public Boolean exitsByCodeAndCountryId(String code, Long countryId) throws StateServiceException {
        return stateRepository.existsByCodeAndCountryId(code, countryId);
    }
}
