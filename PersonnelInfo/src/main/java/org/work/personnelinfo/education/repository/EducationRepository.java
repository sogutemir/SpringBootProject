package org.work.personnelinfo.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personnelinfo.education.model.EducationEntity;

public interface EducationRepository extends JpaRepository<EducationEntity, Long> {
}
