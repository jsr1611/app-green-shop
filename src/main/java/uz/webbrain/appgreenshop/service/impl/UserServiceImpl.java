package uz.webbrain.appgreenshop.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.webbrain.appgreenshop.dto.UserCreateDto;
import uz.webbrain.appgreenshop.entity.Role;
import uz.webbrain.appgreenshop.entity.User;
import uz.webbrain.appgreenshop.exception.NotFoundException;
import uz.webbrain.appgreenshop.repository.RoleRepository;
import uz.webbrain.appgreenshop.repository.UserRepository;
import uz.webbrain.appgreenshop.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

//    @Override
//    public User saveUser(UserCreateDto dto) {
////        if (userRepository.existsByEmail(dto.getEmail()))                    // email bo'yicha ishlamadi
////            throw new RuntimeException("Such Email already exists");
//
//        Set<Role> roleSet = new HashSet<>();
//        Set<Long> roleIds = dto.getRoleIds();
//        for (Long roleId : roleIds) {
//            Role role = roleRepository.findById(roleId)
//                    .orElseThrow(() -> new NotFoundException("Not Found Role with ID: " + roleId));
//            roleSet.add(role);
//        }
//
//        User user = new User(
//                dto.getFirstname(),
//                dto.getLastname(),
//                dto.getEmail(),
//                dto.getPassword(),
//                roleSet
//        );
//        return userRepository.save(user);
//    }


    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Not Found User with ID: " + userId));
    }


    @Override
    public User updateUser(Long userId, UserCreateDto dto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if (!user.getFirstname().equals(dto.getFirstname())) {
                user.setFirstname(dto.getFirstname());
            }

            if (!user.getLastname().equals(dto.getLastname())) {
                user.setLastname(dto.getLastname());
            }

            if (!user.getEmail().equals(dto.getEmail())) {
                user.setEmail(dto.getEmail());
            }

            if (!user.getPassword().equals(dto.getPassword())) {
                user.setPassword(dto.getPassword());
            }

            Set<Role> newRoleSet = new HashSet<>();
            Set<Long> roleIds1 = dto.getRoleIds();
            for (Long aLong : roleIds1) {
                Optional<Role> byId = roleRepository.findById(aLong);
                if (byId.isPresent()) {
                    Role role = byId.get();
                    newRoleSet.add(role);
                } else {
                    throw new NotFoundException("Not Found Role with ID: " + byId);
                }
            }

            Set<Role> roles = user.getRoles();
            roles.addAll(newRoleSet);
            user.setRoles(roles);

            return userRepository.save(user);
        }
        throw new NotFoundException("Not Found User with ID: " + userId);
    }

    @Override
    public String removeUser(Long userId) {
        userRepository.deleteById(userId);

        return "Successfully Deleted";
    }
}
