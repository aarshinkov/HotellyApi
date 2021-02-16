package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.AddressEntity;
import com.aarshinkov.api.hotelly.entities.HotelEntity;
import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.repositories.HotelsRepository;
import com.aarshinkov.api.hotelly.repositories.UsersRepository;
import com.aarshinkov.api.hotelly.requests.hotels.HotelCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
public class HotelServiceImpl implements HotelService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private HotelsRepository hotelsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<HotelEntity> getHotels() {
        return hotelsRepository.findAll();
    }

    @Override
    public HotelEntity getHotelByHotelId(String hotelId) {
        return hotelsRepository.findByHotelId(hotelId);
    }

    @Override
    public HotelEntity createHotel(HotelCreateRequest hcr) {

        HotelEntity hotel = new HotelEntity();
        hotel.setHotelId(UUID.randomUUID().toString());
        hotel.setName(hcr.getName());

        AddressEntity address = new AddressEntity();
        address.setAddressId(UUID.randomUUID().toString());
        address.setCountryCode(hcr.getCountryCode());
        address.setCity(hcr.getCity());
        address.setStreet(hcr.getStreet());
        address.setNumber(hcr.getNumber());

        hotel.setAddress(address);

        hotel.setStars(hcr.getStars());
        hotel.setMainImage(hcr.getMainImage());

        UserEntity owner = usersRepository.findByUserId(hcr.getUserId());

        hotel.setOwner(owner);

        return hotelsRepository.save(hotel);
    }
}
