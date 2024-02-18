package org.work.personnelinfo.experience.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {

    private Long id;
    private String institutionName;
    private String workType;
    private LocalDate workStartDate;
    private LocalDate workEndDate;
    private String workPosition;
    private String workDescription;

    @NotNull
    private Long personelId;
}
