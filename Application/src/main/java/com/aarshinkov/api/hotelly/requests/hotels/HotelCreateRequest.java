package com.aarshinkov.api.hotelly.requests.hotels;

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
public class HotelCreateRequest implements Serializable {

    private String name;
    private String countryCode;
    private String city;
    private String street;
    private Integer number;
    private Integer stars;
    private String mainImage;
    private String userId;

}
