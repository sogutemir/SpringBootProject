package org.work.personnelinfo.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personnelinfo.activity.model.ActivityEntity;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
}
