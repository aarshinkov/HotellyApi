package com.aarshinkov.api.hotelly.requests;

import lombok.*;

import java.io.Serializable;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest implements Serializable {

    private String email;
    private String password;
}
