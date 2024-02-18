package org.work.personnelinfo.personel.mapper;

import org.mapstruct.Mapper;
import org.work.personnelinfo.personel.dto.PersonelDTO;
import org.work.personnelinfo.personel.model.PersonelEntity;


@Mapper
public interface PersonelMapper {

    PersonelDTO modelToDTO(PersonelEntity personelEntity);
    PersonelEntity dtoToModel(PersonelDTO personelDTO);
}
