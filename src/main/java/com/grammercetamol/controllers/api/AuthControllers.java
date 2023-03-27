package com.grammercetamol.controllers.api;

import com.grammercetamol.controllers.services.AuthControllerServices;
import com.grammercetamol.payload.request.RefreshTokenRequest;
import com.grammercetamol.payload.request.SignIn;
import com.grammercetamol.payload.request.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthControllers {

    @Autowired
    AuthControllerServices authControllerServices;

    @PostMapping(value = "/signIn")
    ResponseEntity<?> signup(@Validated @RequestBody SignUp signUp) {
        return authControllerServices.signUp(signUp);
    }

    @PostMapping(value = "/login")
    ResponseEntity<?> login(@Validated @RequestBody SignIn signIn) {
        return authControllerServices.login(signIn);
    }

    @PostMapping(value = "refreshToken")
    ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return authControllerServices.refreshToken(refreshTokenRequest);
    }
}
