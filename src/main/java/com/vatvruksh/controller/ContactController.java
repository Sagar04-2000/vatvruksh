package com.vatvruksh.controller;


import com.vatvruksh.entity.EmailClientDTO;
import com.vatvruksh.service.EmailClientService;
import com.vatvruksh.service.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/contact-us")
@AllArgsConstructor
public class ContactController {

    //@Autowired
    EmailSender emailSender;

    //@Autowired
    EmailClientService emailClientService;

    @GetMapping("/req/{number}")
    public void getreq(@PathVariable String number)
    {
        System.out.println("number :" +number);
    }

    @GetMapping("/req")
    public void sendemail()
    {
        System.out.println("In req");
        String toEmail="sagar2426.wagh@gmail.com";
        String body="Message body";
        String subject=" Email Subject";

        emailSender.sendEmail(toEmail,subject,body);
    }

    @GetMapping("/new")
    public ResponseEntity<String> emailsend(@RequestBody EmailClientDTO emailClientDTO)
    {

        //return ResponseEntity.status(HttpStatus.ACCEPTED).body(emailClientService.saveclientinfo(emailClientDTO) );

        //we wiil  use this when sending back responsentity with http message for more look at emailclient service
        return  new ResponseEntity<>(emailClientService.saveclientinfo(emailClientDTO),HttpStatus.ACCEPTED);
    }



}
