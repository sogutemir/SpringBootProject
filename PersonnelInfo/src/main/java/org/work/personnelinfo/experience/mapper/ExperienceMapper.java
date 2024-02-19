package org.work.personnelinfo.experience.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.work.personnelinfo.experience.dto.ExperienceDTO;
import org.work.personnelinfo.experience.model.ExperienceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExperienceMapper {

    ExperienceDTO modelToDTO(ExperienceEntity experienceEntity);
    ExperienceEntity dtoToModel(ExperienceDTO experienceDTO);
    void updateModel(ExperienceDTO experienceDTO,@MappingTarget ExperienceEntity experienceEntity);
}
