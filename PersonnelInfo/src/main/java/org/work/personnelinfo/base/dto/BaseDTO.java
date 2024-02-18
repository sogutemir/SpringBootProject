package org.work.personnelinfo.base.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.work.personnelinfo.resourcefile.dto.ResourceFileDTO;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class  BaseDTO {

    private Long id;
    private ResourceFileDTO file;

}
