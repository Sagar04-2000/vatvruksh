package com.vatvruksh.service;

import com.vatvruksh.entity.EmailClient;
import com.vatvruksh.entity.EmailClientDTO;
import com.vatvruksh.entity.EmailSendDTO;
import com.vatvruksh.repo.EmailClientRepository;
import com.vatvruksh.utilities.AppConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class EmailClientService {

  //  @Autowired
    private EmailClientRepository emailClientRepository;

    private CodeGenerator codeGenerator;

    private JavaMailSender javaMailSender;


    //we will retrun ResponseEnity<String> and will check exceeption when client saved or mail sent and sent back that resposneentity
    //with exception message along with Http message .. for more refer baledung spring response Entity
    public String saveclientinfo(@RequestBody EmailClientDTO emailClientDTO)
    {

        EmailClient emailClient=new EmailClient();
        String clientId= codeGenerator.getClientId();

        emailClient.setClientId(clientId);
        emailClient.setClientEmail(emailClientDTO.getClientEmail());
        emailClient.setClientName(emailClientDTO.getClientName());
        emailClient.setEmailSubject(emailClientDTO.getEmailSubject());
        emailClient.setEmailMessage(emailClientDTO.getEmailMessage());

        emailClientRepository.save(emailClient);
        log.info("Client Saved");


        //to send an email we will call send Email method and pass the EmailSendDTO to it;
        //here we need to change the subject and message cause in this we need to send an email to client;
        EmailSendDTO emailSendDTO=new EmailSendDTO();
        emailSendDTO.setClientEmail(emailClientDTO.getClientEmail());
        emailSendDTO.setEmailSubject(emailClientDTO.getEmailSubject());
        emailSendDTO.setEmailMessage(emailClientDTO.getEmailMessage());
        emailSendDTO.setClientName(emailClientDTO.getClientName());

        sendEmail(emailSendDTO);


        //to receive email on own email id;
        //here we can use emailsendDto;
        reciveEmail(emailSendDTO);

        return "Saved";

    }

    public String sendEmail(EmailSendDTO emailSendDTO)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        //sender email address
        simpleMailMessage.setFrom(AppConstants.SENDER_EMAIL_ADDRESS);
        //receiver email address
        simpleMailMessage.setTo(emailSendDTO.getClientEmail());

        simpleMailMessage.setSubject(emailSendDTO.getEmailSubject());
        simpleMailMessage.setText(emailSendDTO.getEmailMessage());

        javaMailSender.send(simpleMailMessage);

        log.info("Mail Sent");
        return "Email Sent";

    }

    //when client contact us through webpage the query and  his message will be sent to admin email id using self email id
    //we will change the subject to ClientName + subject +wants to send you message for VD ;
    public String reciveEmail(EmailSendDTO emailSendDTO)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        //sender email address
        simpleMailMessage.setFrom(emailSendDTO.getClientEmail());
        //receiver email address
        simpleMailMessage.setTo(AppConstants.RECEIVER_EMAIL_ADDRESS);

        String clientName=emailSendDTO.getClientName();
        simpleMailMessage.setSubject(clientName+"wants to reach to you regarding "+emailSendDTO.getEmailSubject());
        simpleMailMessage.setText(emailSendDTO.getEmailMessage());

        javaMailSender.send(simpleMailMessage);

        log.info("Receiving Mail Sent");
        return "Email Sent";
    }


    /*public String getApplicationId()
    {
        String appCode= AppConstants.APP_SHORT_CODE;

        SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");

        Date date= Calendar.getInstance().getTime();

        String format=sdf.format(date);

        String appcount="001";

        String appPrefix= appCode+format;


        EmailClient latestClient=emailClientRepository.findLatestApplication(appPrefix+"%");

        if(latestClient==null)
        {
            // when it is the first client
            System.out.println( appPrefix+appcount);
        }else{

            String lastId=latestClient.getClientId().substring(appPrefix.length());
            Integer lastcount=Integer.parseInt(lastId);
            appcount=String.format( lastcount<1000? "%03d" :"%04d",(lastcount+1));
            System.out.println(appcount);

        }
    return null;
    } */
}
