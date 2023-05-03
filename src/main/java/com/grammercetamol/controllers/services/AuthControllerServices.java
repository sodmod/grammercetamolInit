package com.grammercetamol.controllers.services;

import com.grammercetamol.implementation.UserServices;
import com.grammercetamol.payload.request.RefreshTokenRequest;
import com.grammercetamol.payload.request.SignInDTO;
import com.grammercetamol.payload.request.SignUpDTO;
import com.grammercetamol.payload.responses.LoginResponse;
import com.grammercetamol.payload.responses.SignUpResponse;
import com.grammercetamol.securities.jwt.JWTService;
import com.grammercetamol.securities.passwordEncoder.PasswordEncrypt;
import com.grammercetamol.securities.refreshToken.RefreshTokenService;
import com.grammercetamol.utilities.Users;
import com.grammercetamol.utilities.repositories.UsersRepositories;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthControllerServices {
    @Autowired
    UsersRepositories usersRepositories;
    @Autowired
    PasswordEncrypt passwordEncrypt;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JWTService jwtService;

    public ResponseEntity<?> signUp(@NonNull SignUpDTO signUpDTO) {

        if (usersRepositories.existsByEmail(signUpDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new SignUpResponse(
                                    "email already exist",
                                    1
                            )
                    );
        }

        Users users = new Users(
                signUpDTO.getFirstname(),
                signUpDTO.getLastname(),
                signUpDTO.getOthername(),
                signUpDTO.getEmail(),
                signUpDTO.getPhoneNumber(),
                passwordEncrypt
                        .bCryptPasswordEncoder()
                        .encode(
                                signUpDTO.getPassword()
                        ),
                signUpDTO.getRole()
        );

        usersRepositories.save(users);


        return ResponseEntity
                .ok()
                .body(
                        new SignUpResponse(
                                "user registered successfully",
                                0
                        )
                );
    }

    public ResponseEntity<?> login(SignInDTO signInDTO) {
        String refreshToken;

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword()));
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
        UserServices userServices = (UserServices) authentication.getPrincipal();

        Set<String> roles = userServices.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

//        Map<String, Object> rowMap = new HashMap<>();
//        rowMap.put("roles", roles);

//        String token = jwtService.generateToken(rowMap, userServices.getUsername());
        String token = jwtService.generateToken(userServices);

        refreshToken = refreshTokenService.findToken(userServices.getId());
        if (refreshToken == null) {
            refreshToken = refreshTokenService.createRefreshToken(userServices.getId());
        }

        return ResponseEntity
                .ok()
                .body(
                        new LoginResponse(
                                "login Successfully",
                                0,
                                userServices.getId(),
                                userServices.getFirstName(),
                                userServices.getLastName(),
                                userServices.getOtherName(),
                                token,
                                roles,
                                refreshToken
                        )
                );
    }

    public ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest) {


        return ResponseEntity
                .ok()
                .body(
                        refreshTokenService
                                .refreshToken(refreshTokenRequest)
                );
    }

}
