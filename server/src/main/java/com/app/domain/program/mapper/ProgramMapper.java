package com.app.domain.program.mapper;

import com.app.domain.program.dto.ProgramDto;
import com.app.domain.program.entity.Program;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    Program SaveRequestToProgram(ProgramDto.SaveRequest saveRequest);

    default ProgramDto.SaveResponse programToSaveResponse(Program program){
        return ProgramDto.SaveResponse.ofProgram(program);
    }

    List<ProgramDto.SaveResponse> programsToSaveResponses(List<Program> programs);
}
