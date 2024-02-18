package org.work.personnelinfo.personel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.work.personnelinfo.personel.service.PersonelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.work.personnelinfo.personel.dto.PersonelDTO;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personel")
public class PersonelController {

    private final PersonelService personelService;

    @PostMapping("/add")
    public ResponseEntity<?> addPersonel(@RequestParam(value = "file", required = false) MultipartFile file,
                                         @ModelAttribute PersonelDTO personelDTO) {
        try {
            PersonelDTO createdPersonel = personelService.addPersonel(personelDTO, file);
            return new ResponseEntity<>(createdPersonel, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error loading the file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding staff: " + e.getMessage());
        }
    }


}
