package com.aarshinkov.api.hotelly.requests.users;

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
public class UserCreateRequest implements Serializable {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
