package com.vatvruksh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailClientDTO {

    private String clientName;
    private String clientEmail;
    private String emailSubject;
    private String emailMessage;
}
