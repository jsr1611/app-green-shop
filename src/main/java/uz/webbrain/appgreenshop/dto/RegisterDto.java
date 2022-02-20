package uz.webbrain.appgreenshop.dto;

import lombok.Data;
import uz.webbrain.appgreenshop.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegisterDto {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstname;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastname;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Set<Long> roleIdSet;
}
