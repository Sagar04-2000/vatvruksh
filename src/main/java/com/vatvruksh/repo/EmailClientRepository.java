package com.vatvruksh.repo;

import com.vatvruksh.entity.EmailClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
public interface EmailClientRepository extends JpaRepository<EmailClient,String> {


    @Query(value = "select * from email_client where client_id like ? order by client_id desc limit 1",nativeQuery = true)
    EmailClient findLatestApplication(String appPrefix);

}
