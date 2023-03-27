package com.grammercetamol.securities.refreshToken;

import com.grammercetamol.exceptions.TokenRefreshException;
import com.grammercetamol.utilities.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
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


    public RefreshToken extractToken(String token) {
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

    private RefreshToken validate_token(RefreshToken refreshToken) {
        if (refreshToken.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(refreshToken);
            throw new TokenRefreshException(
                    refreshToken.getToken(),
                    "Refresh Token has expired, Please make a new login"
            );
        }
        return refreshToken;
    }
}
