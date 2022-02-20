package uz.webbrain.appgreenshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.webbrain.appgreenshop.dto.UserCreateDto;
import uz.webbrain.appgreenshop.entity.User;
import uz.webbrain.appgreenshop.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public User save(@RequestBody UserCreateDto dto) {
//        return userService.saveUser(dto);
//    }

//    @GetMapping
//    public List<User> findAll() {
//        return userService.findAllUser();
//    }


    @GetMapping("/{user_id}")
    public User findById(@PathVariable("user_id") Long userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/{user_id}")
    public User edit(@PathVariable("user_id") Long userId,
                        @RequestBody UserCreateDto dto) {
        return userService.updateUser(userId, dto);
    }

    @DeleteMapping("/{user_id}")
    public String remove(@PathVariable("user_id") Long userId) {
        return userService.removeUser(userId);
    }


}
