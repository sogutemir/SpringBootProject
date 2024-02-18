//package org.work.personnelinfo.personel.mapper;
//
//import org.work.personnelinfo.personel.dto.PersonelDTO;
//import org.work.personnelinfo.personel.model.PersonelEntity;
//
//public class ProductMapperImpl implements PersonelMapper{
//
//    @Override
//    public PersonelDTO modelToDTO(PersonelEntity personelEntity) {
//        if (personelEntity == null) {
//            return null;
//        }
//
//        PersonelDTO personelDTO = new PersonelDTO();
//
//        personelDTO.setId(personelEntity.getId());
//        personelDTO.setName(personelEntity.getName());
//        personelDTO.setSurname(personelEntity.getSurname());
//        personelDTO.setIdentityNumber(personelEntity.getIdentityNumber());
//        personelDTO.setAcademicTitle(personelEntity.getAcademicTitle());
//        personelDTO.setDateOfBirth(personelEntity.getDateOfBirth());
//        personelDTO.setEmail(personelEntity.getEmail());
//        personelDTO.setPhone(personelEntity.getPhone());
//        personelDTO.setEmergencyContact(personelEntity.getEmergencyContact());
//        personelDTO.setEmergencyContactPhone(personelEntity.getEmergencyContactPhone());
//        personelDTO.setResidenceAddress(personelEntity.getResidenceAddress());
//        personelDTO.setEmploymentStartDate(personelEntity.getStartDateOfEmployment());
//        personelDTO.setRegistrationNumber(personelEntity.getRegistrationNo());
//        personelDTO.setPosition(personelEntity.getPosition());
//        personelDTO.setTitle(personelEntity.getTitle());
//        personelDTO.setUnit(personelEntity.getUnit());
//        personelDTO.setTask(personelEntity.getTask());
//        personelDTO.setPersonnelType(personelEntity.getPersonnelType());
//        personelDTO.setWorkStatus(personelEntity.getWorkStatus());
//        personelDTO.setServiceUsage(personelEntity.getServiceUsage());
//        personelDTO.setInternalNumber(personelEntity.getInternalNumber());
//        personelDTO.setRoomNumber(personelEntity.getRoomNumber());
//        // Fotoğrafı veya diğer özel alanları nasıl işleyeceğinize bağlı olarak ek işlemler yapabilirsiniz.
//        // Örneğin, ResourceFileEntity'den Base64 stringine dönüştürme işlemi gibi.
//
//        return personelDTO;
//    }
//
//
//    @Override
//    public PersonelEntity dtoToModel(PersonelDTO personelDTO) {
//        if (personelDTO == null) {
//            return null;
//        }
//
//        PersonelEntity personelEntity = new PersonelEntity();
//        personelEntity.setId(personelDTO.getId());
//        personelEntity.setName(personelDTO.getName());
//        personelEntity.setSurname(personelDTO.getSurname());
//        personelEntity.setIdentityNumber(personelDTO.getIdentityNumber());
//        personelEntity.setAcademicTitle(personelDTO.getAcademicTitle());
//        personelEntity.setDateOfBirth(personelDTO.getDateOfBirth());
//        personelEntity.setEmail(personelDTO.getEmail());
//        personelEntity.setPhone(personelDTO.getPhone());
//        personelEntity.setEmergencyContact(personelDTO.getEmergencyContact());
//        personelEntity.setEmergencyContactPhone(personelDTO.getEmergencyContactPhone());
//        personelEntity.setResidenceAddress(personelDTO.getResidenceAddress());
//        personelEntity.setStartDateOfEmployment(personelDTO.getEmploymentStartDate());
//        personelEntity.setRegistrationNo(personelDTO.getRegistrationNumber());
//        personelEntity.setPosition(personelDTO.getPosition());
//        personelEntity.setTitle(personelDTO.getTitle());
//        personelEntity.setUnit(personelDTO.getUnit());
//        personelEntity.setTask(personelDTO.getTask());
//        personelEntity.setPersonnelType(personelDTO.getPersonnelType());
//        personelEntity.setWorkStatus(personelDTO.getWorkStatus());
//        personelEntity.setServiceUsage(personelDTO.getServiceUsage());
//        personelEntity.setInternalNumber(personelDTO.getInternalNumber());
//        personelEntity.setRoomNumber(personelDTO.getRoomNumber());
//
//        // Eğer personelDTO.getFile() null değilse ve ilgili dosya bilgileri varsa,
//        // bu kısmı da doldurabilirsiniz. Örneğin:
//        // personelEntity.setFile(yourMethodToConvertResourceFileDTOToEntity(personelDTO.getFile()));
//
//        return personelEntity;
//    }
//
//}
