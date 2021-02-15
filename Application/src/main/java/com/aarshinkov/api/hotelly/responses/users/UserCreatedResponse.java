package com.aarshinkov.api.hotelly.responses.users;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreatedResponse implements Serializable {

    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private Timestamp createdOn;
}
