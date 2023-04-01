package com.grammercetamol.payload.responses;

import lombok.Data;

import java.util.Set;

@Data
public class RefreshTokenResponse {
    private String type = "Bearer";
    private long id;
    private String firstname;
    private String lastname;
    private String othername;
    private String token;
    private Set<String> permissions;
    private String refreshToken;

    public RefreshTokenResponse(long id, String firstname, String lastname, String othername, String token, Set<String> permissions, String refreshToken) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.othername = othername;
        this.token = token;
        this.permissions = permissions;
        this.refreshToken = refreshToken;
    }
}
