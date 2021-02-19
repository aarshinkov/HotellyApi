package com.aarshinkov.api.hotelly.requests.hotels;

import java.io.Serializable;
import lombok.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelUpdateRequest implements Serializable {

    private String hotelId;
    private String name;
    private String description;
    private String countryCode;
    private String city;
    private String street;
    private Integer number;
    private Integer stars;
    private String mainImage;
}
