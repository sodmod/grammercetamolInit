package com.grammercetamol.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LoginResponses extends Responses {

    private long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String type = "Bearer";
    private String token;
    private Set<String> roles;

    public LoginResponses(
            String responseMessage,
            int responseCode,
            long id,
            String firstName,
            String lastName,
            String otherName,
            String token,
            Set<String> roles) {

        super.setMessage(responseMessage);
        super.setMessageCode(responseCode);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.token = token;
        this.roles = roles;
    }
}
