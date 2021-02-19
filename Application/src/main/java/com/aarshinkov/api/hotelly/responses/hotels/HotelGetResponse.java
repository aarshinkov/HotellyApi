package com.aarshinkov.api.hotelly.responses.hotels;

import com.aarshinkov.api.hotelly.responses.users.UserGetResponse;
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
public class HotelGetResponse implements Serializable {

    private String hotelId;
    private String name;
    private String description;
    private AddressGetResponse address;
    private Integer stars;
    private String mainImage;
    private UserGetResponse owner;
    private Timestamp createdOn;
    private Timestamp editedOn;
}
