package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.HotelEntity;
import com.aarshinkov.api.hotelly.requests.hotels.HotelCreateRequest;

import java.util.List;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface HotelService {

    List<HotelEntity> getHotels();

    HotelEntity getHotelByHotelId(String hotelId);

    HotelEntity createHotel(HotelCreateRequest hcr);
}
