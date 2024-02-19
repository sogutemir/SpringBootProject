package org.work.personnelinfo.education.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.work.personnelinfo.education.dto.EducationDTO;
import org.work.personnelinfo.education.mapper.EducationMapper;
import org.work.personnelinfo.education.model.EducationEntity;
import org.work.personnelinfo.education.repository.EducationRepository;
import org.work.personnelinfo.experience.model.ExperienceEntity;
import org.work.personnelinfo.personel.model.PersonelEntity;
import org.work.personnelinfo.personel.repository.PersonelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;
    private final PersonelRepository personelRepository;


    @Transactional(readOnly = true)
    public EducationDTO getEducationById(Long id) {
        return educationRepository.findById(id)
                .map(educationMapper::modelToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Education not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<EducationDTO> getEducationsByPersonelId(Long personelId) {
        return educationRepository.findByPersonelId(personelId)
                .stream()
                .map(educationMapper::modelToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EducationDTO addEducation(EducationDTO educationDTO) {
        if (educationDTO == null) {
            throw new IllegalArgumentException("EducationDTO cannot be null");
        }

        EducationEntity educationEntity = educationMapper.dtoToModel(educationDTO);
        PersonelEntity personelEntity = personelRepository.findById(educationDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + educationDTO.getPersonelId()));

        educationEntity.setPersonel(personelEntity);
        educationEntity = educationRepository.save(educationEntity);

        return educationMapper.modelToDTO(educationEntity);
    }

    @Transactional
    public EducationDTO updateEducation(Long educationId, EducationDTO educationDTO) {
        EducationEntity existingEducationEntity = educationRepository.findById(educationId)
                .orElseThrow(() -> new IllegalArgumentException("Education not found with id: " + educationId));

        educationMapper.updateModel(educationDTO, existingEducationEntity);

        return educationMapper.modelToDTO(existingEducationEntity);
    }

    @Transactional
    public void deleteEducation(Long educationId){
        EducationEntity educationEntity = educationRepository.findById(educationId)
                .orElseThrow(() -> new IllegalArgumentException("Experience not found with id: " + educationId));
        educationRepository.delete(educationEntity);
    }


}
