package com.grammercetamol.securities.refreshToken;

import com.grammercetamol.utilities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUsers(Users users);

    Boolean existsByUsers(Users users);


}
