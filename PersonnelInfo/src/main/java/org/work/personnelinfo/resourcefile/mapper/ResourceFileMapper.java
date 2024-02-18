package org.work.personnelinfo.resourcefile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.work.personnelinfo.resourcefile.dto.ResourceFileDTO;
import org.work.personnelinfo.resourcefile.model.ResourceFileEntity;

@Mapper
public interface ResourceFileMapper {

    ResourceFileMapper INSTANCE = Mappers.getMapper(ResourceFileMapper.class);

    @Mapping(target = "dataBase64", expression = "java(org.apache.commons.codec.binary.Base64.encodeBase64String(resourceFileEntity.getData()))")
    ResourceFileDTO entityToDto(ResourceFileEntity resourceFileEntity);

    @Mapping(target = "data", expression = "java(org.apache.commons.codec.binary.Base64.decodeBase64(resourceFileDTO.getDataBase64()))")
    ResourceFileEntity dtoToEntity(ResourceFileDTO resourceFileDTO);
}
