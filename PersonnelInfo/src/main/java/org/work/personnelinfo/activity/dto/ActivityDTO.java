package org.work.personnelinfo.activity.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


import lombok.*;
import org.work.personnelinfo.base.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO extends BaseDTO {

    private String activityName;
    private String description;
    private String link;
    private LocalDate activityDate;

    @NotNull
    private Long personelId;

}
