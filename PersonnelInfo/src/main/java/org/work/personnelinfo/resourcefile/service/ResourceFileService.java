package org.work.personnelinfo.resourcefile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public String uploadFile(MultipartFile file, BaseEntity entity) throws IOException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] compressedData = ResourceFileUtils.compressBytes(file.getBytes());

        ResourceFileEntity resourceFileData = ResourceFileEntity.builder()
                .name(fileName)
                .type(contentType)
                .data(compressedData)
                .build();

        switch (entity.getClass().getSimpleName()) {
            case "PersonelEntity":
                PersonelEntity personelEntity = (PersonelEntity) entity;
                resourceFileData.setPersonel(personelEntity);
                break;
            case "FileEntity":
                FileEntity fileEntity = (FileEntity) entity;
                resourceFileData.setFile(fileEntity);
                break;
            case "ActivityEntity":
                ActivityEntity activityEntity = (ActivityEntity) entity;
                resourceFileData.setActivity(activityEntity);
                break;
            default:
                throw new IllegalArgumentException("Entity type not supported");
        }

        entity.setResourceFile(resourceFileData);

        FileEntity savedFile = fileRepository.save(resourceFileData).getFile();
        if (savedFile != null) {
            return "Saved file in DB with name: " + fileName;
        } else {
            return "Error: File not saved.";
        }
    }

    @Transactional(readOnly = true)
    public byte[] downloadFile(Long fileId) throws FileNotFoundException {
        Optional<ResourceFileEntity> retrievedFile = fileRepository.findById(fileId);

        if (retrievedFile.isPresent()) {
            return ResourceFileUtils.decompressBytes(retrievedFile.get().getData());
        } else {
            throw new FileNotFoundException("File not found with name: " + retrievedFile.get().getName());
        }
    }
}
