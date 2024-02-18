package org.work.personnelinfo.resourcefile.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceFileDTO  {

    private Long id;
    private String name;
    private String type;
    private String dataBase64;

}
