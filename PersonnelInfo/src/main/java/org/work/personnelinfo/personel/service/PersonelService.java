package org.work.personnelinfo.personel.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.work.personnelinfo.personel.dto.PersonelDTO;
import org.work.personnelinfo.personel.mapper.PersonelMapper;
import org.work.personnelinfo.personel.repository.PersonelRepository;
import org.work.personnelinfo.personel.model.PersonelEntity;
import org.work.personnelinfo.resourcefile.service.ResourceFileService;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PersonelService {

    private final PersonelRepository personelRepository;
    private final PersonelMapper personelMapper;
    private final ResourceFileService resourceFileService;



    @Transactional
    public PersonelDTO addPersonel(PersonelDTO personelDTO, MultipartFile file) throws IOException {
        if (personelDTO == null) {
            throw new IllegalArgumentException("PersonelDTO cannot be null");
        }

        PersonelEntity personelEntity = personelMapper.dtoToModel(personelDTO);

        personelEntity = personelRepository.save(personelEntity);

        if (file != null && !file.isEmpty()) {
            resourceFileService.uploadFile(file, personelEntity);
        }

        return personelMapper.modelToDTO(personelEntity);
    }


}
