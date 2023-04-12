package com.grammercetamol.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LoginResponse extends Response {

    private long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String type = "Bearer";
    private String token;
    private Set<String> roles;
    private String refreshToken;
    private boolean loggedIn = true;

    public LoginResponse(
            String responseMessage,
            int responseCode,
            long id,
            String firstName,
            String lastName,
            String otherName,
            String token,
            Set<String> roles,
            String refreshToken) {

        super.setMessage(responseMessage);
        super.setMessageCode(responseCode);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.token = token;
        this.roles = roles;
        this.refreshToken = refreshToken;
    }
}
