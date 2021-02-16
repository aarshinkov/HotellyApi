package com.aarshinkov.api.hotelly.responses.hotels;

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
public class AddressGetResponse implements Serializable {

    private String addressId;
    private String countryCode;
    private String city;
    private String street;
    private Integer number;
    private Timestamp createdOn;
}
