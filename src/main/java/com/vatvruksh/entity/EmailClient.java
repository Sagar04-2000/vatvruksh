package com.vatvruksh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table(name="emailclient")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailClient {

    @Id
    //@Column(name="clientId")
    private String clientId;

   // @Column(name="clientName")
    private String clientName;

   // @Column(name="clientEmail")
    private String clientEmail;

   // @Column(name = "emailSubject")
    private String emailSubject;

    //@Column(name="emailMessage")
    private String emailMessage;


}
