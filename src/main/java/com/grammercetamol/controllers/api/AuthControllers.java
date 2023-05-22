package com.grammercetamol.controllers.api;

import com.grammercetamol.controllers.services.AuthControllerServices;
import com.grammercetamol.payload.request.RefreshTokenRequest;
import com.grammercetamol.payload.request.SignInDTO;
import com.grammercetamol.payload.request.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthControllers {
    private final AuthControllerServices authControllerServices;

    @PostMapping(value = "/signIn")
    ResponseEntity<?> signup(@Validated @RequestBody SignUpDTO signUpDTO) {
        return authControllerServices.signUp(signUpDTO);
    }

    @PostMapping(value = "/login")
    ResponseEntity<?> login(@Validated @RequestBody SignInDTO signInDTO) {
        return authControllerServices.login(signInDTO);
    }

    @PostMapping(value = "/refreshToken")
    ResponseEntity<?> refreshToken(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authControllerServices.refreshToken(refreshTokenRequest);
    }
}
