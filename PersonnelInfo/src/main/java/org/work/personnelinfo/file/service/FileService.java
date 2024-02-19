package org.work.personnelinfo.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.work.personnelinfo.file.dto.FileDTO;
import org.work.personnelinfo.file.mapper.FileMapper;
import org.work.personnelinfo.file.model.FileEntity;
import org.work.personnelinfo.file.repository.FileRepository;
import org.work.personnelinfo.resourcefile.service.ResourceFileService;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final ResourceFileService resourceFileService;
    private final FileMapper fileMapper;

    @Transactional(readOnly = true)
    public FileDTO getFileById(Long id){
        return fileRepository.findById(id)
                .map(fileMapper::modelToDTO)
                .orElseThrow(() -> new IllegalArgumentException("File not found with id: " + id));
    }

    @Transactional
    public FileDTO addFile(FileDTO fileDTO, MultipartFile file) throws IOException {
        if(fileDTO == null){
            throw new IllegalArgumentException("FileDTO cannot be null");
        }
        FileEntity fileEntity = fileMapper.dtoToModel(fileDTO);
        fileEntity = fileRepository.save(fileEntity);

        if(file != null && !file.isEmpty()){
            resourceFileService.uploadFile(file, fileEntity);
        }
        return fileMapper.modelToDTO(fileEntity);
    }

    @Transactional
    public FileDTO updateFile(Long fileId, FileDTO fileDTO, MultipartFile file) throws IOException {
        FileEntity existingFileEntity = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File not found with id: " + fileId));

        fileMapper.updateModel(fileDTO, existingFileEntity);

        if(file != null && !file.isEmpty()){
            if(existingFileEntity.getResourceFile() != null){
                resourceFileService.deleteFile(existingFileEntity.getResourceFile().getId());
            }
            resourceFileService.uploadFile(file, existingFileEntity);
        }

        FileEntity updatedFileEntity = fileRepository.save(existingFileEntity);

        return fileMapper.modelToDTO(updatedFileEntity);
    }

    @Transactional
    public void deleteFile(Long fileId) throws FileNotFoundException {
        FileEntity fileEntity = fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id: " + fileId));
        if(fileEntity.getResourceFile() != null){
            resourceFileService.deleteFile(fileEntity.getResourceFile().getId());
        }
        fileRepository.delete(fileEntity);
    }

}
