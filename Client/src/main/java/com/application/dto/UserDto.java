package com.application.dto;


import com.application.dto.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    @Email
    private String name;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    private List<Role> roles = new ArrayList<>();
}
