package org.work.personnelinfo.personel.dto;

import java.time.LocalDate;
import lombok.*;
import org.work.personnelinfo.base.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonelDTO extends BaseDTO {

    private String name;
    private String surname;
    private String identityNumber;
    private String academicTitle;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String emergencyContact;
    private String emergencyContactPhone;
    private String residenceAddress;
    private LocalDate employmentStartDate;
    private String registrationNumber;
    private String position;
    private String title;
    private String unit;
    private String task;
    private String personnelType;
    private String workStatus;
    private String serviceUsage;
    private String internalNumber;
    private String roomNumber;
    private String photoBase64;


}