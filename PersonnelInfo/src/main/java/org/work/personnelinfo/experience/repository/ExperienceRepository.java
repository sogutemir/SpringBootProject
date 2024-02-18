package org.work.personnelinfo.experience.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personnelinfo.experience.model.ExperienceEntity;

public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Long> {
}
