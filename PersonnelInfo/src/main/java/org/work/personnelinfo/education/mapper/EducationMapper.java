package org.work.personnelinfo.education.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.work.personnelinfo.education.dto.EducationDTO;
import org.work.personnelinfo.education.model.EducationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {

    EducationDTO modelToDTO(EducationEntity educationEntity);
    EducationEntity dtoToModel(EducationDTO educationDTO);
    void updateModel(EducationDTO educationDTO, @MappingTarget EducationEntity educationEntity);
}
