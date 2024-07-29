package com.chill.mallang.domain.user.service;

import com.chill.mallang.domain.user.dto.FindByEmailDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
public interface UserService {

    Optional<FindByEmailDTO> findByEmail(String email);
    ResponseEntity<?> findByEmailFromToken(HttpServletRequest request);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

}
