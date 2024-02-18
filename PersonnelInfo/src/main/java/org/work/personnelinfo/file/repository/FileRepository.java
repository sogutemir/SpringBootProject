package org.work.personnelinfo.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personnelinfo.file.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
