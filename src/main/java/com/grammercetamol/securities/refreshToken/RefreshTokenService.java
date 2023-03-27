package com.grammercetamol.securities.refreshToken;

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

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsers(usersRepositories.findById(userId).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(Instant.now().plusMillis(refreshTokenExpired));
//        refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }
}
