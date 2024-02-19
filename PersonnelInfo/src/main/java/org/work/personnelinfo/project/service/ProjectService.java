package org.work.personnelinfo.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.work.personnelinfo.experience.model.ExperienceEntity;
import org.work.personnelinfo.personel.model.PersonelEntity;
import org.work.personnelinfo.personel.repository.PersonelRepository;
import org.work.personnelinfo.project.dto.ProjectDTO;
import org.work.personnelinfo.project.mapper.ProjectMapper;
import org.work.personnelinfo.project.model.ProjectEntity;
import org.work.personnelinfo.project.repository.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final PersonelRepository personelRepository;

    @Transactional(readOnly = true)
    public ProjectDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::modelToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<ProjectDTO> getProjectsByPersonelId(Long personelId) {
        return projectRepository.findByPersonelId(personelId)
                .stream()
                .map(projectMapper::modelToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectDTO addProject(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            throw new IllegalArgumentException("ProjectDTO cannot be null");
        }

        ProjectEntity projectEntity = projectMapper.dtoToModel(projectDTO);
        PersonelEntity personelEntity = personelRepository.findById(projectDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + projectDTO.getPersonelId()));

        projectEntity.setPersonel(personelEntity);
        projectEntity = projectRepository.save(projectEntity);

        return projectMapper.modelToDTO(projectEntity);
    }

    @Transactional
    public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
        ProjectEntity existingProjectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

        projectMapper.updateModel(projectDTO, existingProjectEntity);

        return projectMapper.modelToDTO(existingProjectEntity);
    }

    @Transactional
    public void deleteProject(Long projectId){
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Experience not found with id: " + projectId));
        projectRepository.delete(projectEntity);
    }

}
