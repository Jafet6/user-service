package com.accenture.userservice.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {
    private String name;
    private String cpf;
    private String login;
    private String password;

}
