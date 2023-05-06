package com.grammercetamol.securities.refreshToken;

import com.grammercetamol.exceptions.TokenRefreshException;
import com.grammercetamol.payload.request.RefreshTokenRequest;
import com.grammercetamol.payload.responses.RefreshTokenResponse;
import com.grammercetamol.securities.jwt.JWTService;
import com.grammercetamol.utilities.Permissions;
import com.grammercetamol.utilities.Roles;
import com.grammercetamol.utilities.Users;
import com.grammercetamol.utilities.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RefreshTokenService {
    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private JWTService jwtService;
    @Value("${app.refreshToken.expiredTime}")
    private Long refreshTokenExpired;

    public String createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsers(usersRepositories.findById(userId).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(Instant.now().plusMillis(refreshTokenExpired));
        refreshTokenRepo.save(refreshToken);
        return refreshToken.getToken();
    }

    public String findToken(long userId) {
        RefreshToken refreshToken;
        Users users = usersRepositories.findById(userId).get();
        boolean fact = refreshTokenRepo.existsByUsers(users);
        if (!fact) {
            return null;
        } else {
            refreshToken = refreshTokenRepo.findByUsers(users).get();
            return refreshToken.getToken();
        }
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        String refreshToken = refreshTokenRequest.getRefreshToken();

        RefreshToken token = extractToken(refreshToken);
        validate_token(token);

        Set<Permissions> permissionsSet = new HashSet<>(Enum.valueOf(Roles.class, token.getUsers().getRole()).permissions());
        Set<String> permissions = permissionsSet.stream().map(Permissions::getPermissions).collect(Collectors.toSet());

//        Map<String, Object> roles = new HashMap<>();
//        roles.put("roles", permit);

//        String jwt = jwtService.generateToken(roles, token.getUsers().getEmail());

        String jwt = jwtService.generateToken(new HashMap<>(), token.getUsers().getEmail());

        return new RefreshTokenResponse(
                token.getUsers().getId(),
                token.getUsers().getFirstName(),
                token.getUsers().getLastName(),
                token.getUsers().getOtherName(),
                jwt,
                permissions,
                token.getToken()
        );
    }

    private RefreshToken extractToken(String token) {
        RefreshToken refreshToken =
                refreshTokenRepo
                        .findByToken(token)
                        .orElseThrow(
                                () -> new TokenRefreshException(
                                        token,
                                        "token cannot be found in the database"
                                )
                        );
        return refreshToken;
    }

    private boolean validate_token(RefreshToken refreshToken) {
        if (refreshToken.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(refreshToken);
            throw new TokenRefreshException(
                    refreshToken.getToken(),
                    "Refresh Token has expired, Please make a new login"
            );
        } else {
            return true;
        }
    }

}
