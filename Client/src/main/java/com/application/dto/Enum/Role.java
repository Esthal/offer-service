package com.application.dto.Enum;

import com.application.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role
{
    private Long id;
    private String name;
    private List<UserDto> users;
}
