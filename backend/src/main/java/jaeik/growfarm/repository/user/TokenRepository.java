package jaeik.growfarm.repository.user;

import jaeik.growfarm.entity.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * 토큰 Repository
 * 토큰 관련 데이터베이스 작업을 수행하는 Repository
 * 수정일 : 2025-05-03
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findById(Long tokenId);

    Token findByJwtRefreshToken(String jwtRefreshToken);
}
