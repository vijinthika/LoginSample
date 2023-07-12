package com.login.Login.request.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
   private  String toEmail;
    private String body;
    private String subject;
    private String attachment;
}
