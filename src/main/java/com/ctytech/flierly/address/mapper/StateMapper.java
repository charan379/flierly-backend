package com.ctytech.flierly.address.mapper;

import com.ctytech.flierly.address.dto.StateDTO;
import com.ctytech.flierly.address.entity.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {

    @Autowired
    private ModelMapper modelMapper;

    public StateDTO toDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }

    public State toEntity(StateDTO stateDTO) {
        return modelMapper.map(stateDTO, State.class);
    }
}
