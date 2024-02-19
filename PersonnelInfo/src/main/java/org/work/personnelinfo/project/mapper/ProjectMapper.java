package org.work.personnelinfo.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.work.personnelinfo.project.dto.ProjectDTO;
import org.work.personnelinfo.project.model.ProjectEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectDTO modelToDTO(ProjectEntity projectEntity);
    ProjectEntity dtoToModel(ProjectDTO projectDTO);
    void updateModel(ProjectDTO projectDTO,@MappingTarget ProjectEntity projectEntity);
}
