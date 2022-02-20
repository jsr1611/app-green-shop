package uz.webbrain.appgreenshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.webbrain.appgreenshop.dto.LoginDto;
import uz.webbrain.appgreenshop.dto.RegisterDto;
import uz.webbrain.appgreenshop.dto.response.Response;
import uz.webbrain.appgreenshop.entity.Role;
import uz.webbrain.appgreenshop.entity.User;
import uz.webbrain.appgreenshop.repository.RoleRepository;
import uz.webbrain.appgreenshop.repository.UserRepository;
import uz.webbrain.appgreenshop.security.JwtProvider;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MyUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        return user;
    }


    public Response register(RegisterDto dto) {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
        for(Long roleId : dto.getRoleIdSet()){
            Optional<Role> byId = roleRepository.findById(roleId);
            byId.ifPresent(roles::add);
        }
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return new Response(true, "Successfully registered",savedUser.getEmail());

    }



    public Response login(LoginDto dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        User principal = (User) authenticate.getPrincipal();
        String generatedToken = jwtProvider.generateToken(principal.getEmail(), principal.getRoles());

        return new Response(true, "Token", generatedToken);
    }
}
