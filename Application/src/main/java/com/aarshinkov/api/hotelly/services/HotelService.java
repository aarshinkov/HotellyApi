package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.HotelEntity;
import com.aarshinkov.api.hotelly.exceptions.HollException;
import com.aarshinkov.api.hotelly.requests.hotels.HotelCreateRequest;
import com.aarshinkov.api.hotelly.requests.hotels.HotelUpdateRequest;

import java.util.List;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface HotelService {

    List<HotelEntity> getHotels();

    HotelEntity getHotelByHotelId(String hotelId);

    HotelEntity createHotel(HotelCreateRequest hcr) throws HollException;

    HotelEntity updateHotel(HotelUpdateRequest hur) throws HollException;

    void deleteHotel(HotelEntity hotel) throws Exception;
}
