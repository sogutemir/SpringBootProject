package org.work.personnelinfo.resourcefile.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.work.personnelinfo.activity.model.ActivityEntity;
import org.work.personnelinfo.base.model.BaseEntity;
import org.work.personnelinfo.file.model.FileEntity;
import org.work.personnelinfo.personel.model.PersonelEntity;
import org.work.personnelinfo.resourcefile.model.ResourceFileEntity;
import org.work.personnelinfo.resourcefile.repository.ResourceFileRepository;
import org.work.personnelinfo.resourcefile.utility.ResourceFileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceFileService {

    private final ResourceFileRepository fileRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public String uploadFile(MultipartFile file, BaseEntity entity) throws IOException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String contentType = file.getContentType();
        byte[] compressedData = ResourceFileUtils.compressBytes(file.getBytes());

        ResourceFileEntity fileEntity = ResourceFileEntity.builder()
                .name(fileName)
                .type(contentType)
                .data(compressedData)
                .build();

        // Entity ile ilişkili alanları set edin
        associateEntityWithFile(entity, fileEntity);

        // Veritabanına kaydet
        ResourceFileEntity savedFile = fileRepository.save(fileEntity);

        if (savedFile != null) {
            return "Saved file in DB with name: " + fileName;
        } else {
            return "Error: File not saved.";
        }
    }

    @Transactional(readOnly = true)
    public byte[] downloadFile(Long fileId) throws FileNotFoundException {
        Optional<ResourceFileEntity> retrievedFile = fileRepository.findById(fileId);

        if (!retrievedFile.isPresent()) {
            throw new FileNotFoundException("File not found with id: " + fileId);
        }

        return ResourceFileUtils.decompressBytes(retrievedFile.get().getData());
    }

    private void associateEntityWithFile(BaseEntity entity, ResourceFileEntity fileEntity) {
        switch (entity.getClass().getSimpleName()) {
            case "PersonelEntity":
                PersonelEntity personelEntity = (PersonelEntity) entity;
                personelEntity.setResourceFile(fileEntity);
                break;
            case "FileEntity":
                FileEntity fileEntity1 = (FileEntity) entity;
                fileEntity1.setResourceFile(fileEntity);
                break;
            case "ActivityEntity":
                ActivityEntity activityEntity = (ActivityEntity) entity;
                activityEntity.setResourceFile(fileEntity);
                break;
            default:
                throw new IllegalArgumentException("Entity type not supported");
        }
    }
}

