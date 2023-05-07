package com.assigment.Holabus.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    private final String fullName;
    private final String email;
    private final String phoneNumber;
    private final int depId;
    private final String password;
}
