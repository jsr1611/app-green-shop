package uz.webbrain.appgreenshop.service;

import uz.webbrain.appgreenshop.dto.UserCreateDto;
import uz.webbrain.appgreenshop.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    User findUserById(Long userId);

    User updateUser(Long userId, UserCreateDto dto);

//    User saveUser(UserCreateDto dto);

    String removeUser(Long userId);
}
