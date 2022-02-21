package uz.webbrain.appgreenshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.webbrain.appgreenshop.dto.LoginDto;
import uz.webbrain.appgreenshop.dto.RegisterDto;
import uz.webbrain.appgreenshop.dto.response.Response;
import uz.webbrain.appgreenshop.service.impl.MyUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MyUserService userService;

    @PostMapping("/register/user")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDto dto){
        Response response = userService.register(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 500 ).body(response);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto dto){
        Response response = userService.login(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 500).body(response);
    }
}
