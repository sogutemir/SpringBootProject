package org.work.personnelinfo.file.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.work.personnelinfo.file.dto.FileDTO;
import org.work.personnelinfo.file.model.FileEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileMapper {

    FileDTO modelToDTO(FileEntity fileEntity);

    FileEntity dtoToModel(FileDTO fileDTO);

    void updateModel(FileDTO fileDTO,@MappingTarget FileEntity fileEntity);

}
