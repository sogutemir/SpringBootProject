package org.work.personnelinfo.personel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.work.personnelinfo.activity.model.ActivityEntity;
import org.work.personnelinfo.education.model.EducationEntity;
import org.work.personnelinfo.file.model.FileEntity;
import org.work.personnelinfo.project.model.ProjectEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personel")
public class PersonelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be left blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname cannot be left blank")
    @Size(min = 2, max = 60, message = "Surname must be between 2 and 60 characters")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Identity Number cannot be left blank")
    @Size(min = 11, max = 11, message = "Identity Number must be 11 characters")
    @Column(name = "identity_number")
// @ValidIdentityNumber
    private String identityNumber;

    @Size(max = 50, message = "Academic Title must be a maximum of 50 characters")
    @Column(name = "academic_title")
    private String academicTitle;

    @Past
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Email cannot be blank")
    @Column(name = "email")
    @Email
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Emergency contact is required")
    @Column(name = "emergency_contact")
    private String emergencyContact;

    @NotBlank(message = "Emergency contact phone is required")
    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @NotBlank(message = "Residence address is required")
    @Column(name = "residence_address")
    private String residenceAddress;

    @PastOrPresent
    @Column(name = "start_date_of_employment")
    private LocalDate startDateOfEmployment;

    @NotBlank(message = "Registration number is required")
    private String registrationNo;

    @NotBlank(message = "Position information is required")
    @Column(name = "position")
    private String position;

    @NotBlank(message = "Title information is required")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Unit information is required")
    @Column(name = "unit")
    private String unit;

    @NotBlank(message = "Task information is required")
    @Column(name = "task")
    private String task;

    @NotBlank(message = "Personnel type information is required")
    @Column(name = "personnel_type")
    private String personnelType;

    @NotBlank(message = "Work status information is required")
    @Column(name = "work_status")
    private String workStatus;

    @Column(name = "service_usage")
    private String serviceUsage;

    @Column(name = "internal_number")
    private String internalNumber;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToMany(mappedBy = "personel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationEntity> egitimler;

    @OneToMany(mappedBy = "personel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectEntity> projeler;

    @OneToMany(mappedBy = "personel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityEntity> etkinlikler;

    @OneToMany(mappedBy = "personel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> dosyalar;


//    @Lob
//    @Column(name = "personel_foto")
//    private byte[] fotograf;



}
