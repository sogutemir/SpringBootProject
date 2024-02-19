package org.work.personnelinfo.activity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.work.personnelinfo.activity.dto.ActivityDTO;
import org.work.personnelinfo.activity.model.ActivityEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActivityMapper {

    ActivityDTO modelToDTO(ActivityEntity activityEntity);

    ActivityEntity dtoToModel(ActivityDTO activityDTO);

    void updateModel(ActivityDTO activityDTO,@MappingTarget ActivityEntity activityEntity);
}
