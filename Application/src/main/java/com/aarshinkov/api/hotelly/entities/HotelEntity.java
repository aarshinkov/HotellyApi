package com.aarshinkov.api.hotelly.entities;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Entity
@Table(name = "hotels")
@DynamicInsert
@DynamicUpdate
public class HotelEntity implements Serializable {

    @Id
    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "main_image")
    private String mainImage;

    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "user_id")
    private UserEntity owner;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "edited_on")
    private Timestamp editedOn;
}
