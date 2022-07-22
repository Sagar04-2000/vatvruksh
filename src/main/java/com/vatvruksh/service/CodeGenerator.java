package com.vatvruksh.service;


import com.vatvruksh.entity.EmailClient;
import com.vatvruksh.repo.EmailClientRepository;
import com.vatvruksh.utilities.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@AllArgsConstructor
public class CodeGenerator {

    private EmailClientRepository emailClientRepository;

    public String getClientId()
    {

        String appCode= AppConstants.APP_SHORT_CODE;

        SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
        Date date= Calendar.getInstance().getTime();
        String format=sdf.format(date);

        String appPrefix=appCode+format;

        String appCount="001";

        EmailClient latestEmailClient=emailClientRepository.findLatestApplication(appPrefix+"%");

        if(latestEmailClient!=null)
        {
            String lastClientID= latestEmailClient.getClientId().substring(appPrefix.length());
            Integer lastcount=Integer.parseInt(lastClientID);

            appCount=String.format(lastcount<1000 ? "%03d" : "%04d",(lastcount+1));

        }

        return appPrefix+appCount;

    }
}
