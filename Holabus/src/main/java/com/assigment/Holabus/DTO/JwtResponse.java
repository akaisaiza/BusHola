package com.assigment.Holabus.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private final String token;
    private final String role;
}
