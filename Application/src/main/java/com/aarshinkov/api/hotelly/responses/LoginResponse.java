package com.aarshinkov.api.hotelly.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse implements Serializable {

    private String userId;
    private String email;
    private String firstName;
    private String lastName;
}
