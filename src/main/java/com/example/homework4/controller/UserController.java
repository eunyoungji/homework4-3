package com.example.homework4.controller;

import com.example.homework4.dto.ApiResponseDto;
import com.example.homework4.dto.AuthRequestDto;
import com.example.homework4.jwt.JwtUtil;
import com.example.homework4.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtutil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signUp(@Valid @RequestBody AuthRequestDto requestDto, BindingResult bingdingResult) {

        List<FieldError> fieldErrors = bingdingResult.getFieldErrors();

        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bingdingResult.getFieldErrors()) {
                log.error(fieldError.getField() + "필드: " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiResponseDto("입력한 정보가 회원가입 조건에 맞지 않는다 게로!", HttpStatus.BAD_REQUEST.value()));
        }

        // Validation 예외 처리 후 서비스로 전달
        // 1. 회원가입
        try {
            userService.signup(requestDto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("중복된 이름이다 게로!", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(201).body(new ApiResponseDto("회원가입 성공! 환영한다 게로!", HttpStatus.CREATED.value()));
    }

    // 2. 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> logIn(@RequestBody AuthRequestDto requestDto, HttpServletResponse response) {

        try {
            userService.login(requestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("로그인 실패! T.T", HttpStatus.BAD_REQUEST.value()));
        }
        //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtutil.createToken(requestDto.getUsername(), requestDto.getRole()));

        return ResponseEntity.status(201).body(new ApiResponseDto("로그인 성공! ^.^ 토큰 정보 : " + response.getHeader("Authorization"), HttpStatus.CREATED.value()));


    }

}
