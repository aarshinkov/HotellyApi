package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.HotelEntity;
import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.exceptions.HollException;
import com.aarshinkov.api.hotelly.repositories.HotelsRepository;
import com.aarshinkov.api.hotelly.repositories.UsersRepository;
import com.aarshinkov.api.hotelly.requests.hotels.HotelCreateRequest;
import com.aarshinkov.api.hotelly.requests.hotels.HotelUpdateRequest;
import com.aarshinkov.api.hotelly.uploader.Uploader;
import com.aarshinkov.api.hotelly.uploader.domain.FileName;
import com.aarshinkov.api.hotelly.uploader.domain.ImageFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.aarshinkov.api.hotelly.utils.ResponseCodes.HOTEL_NOT_FOUND;

import org.springframework.http.HttpStatus;

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

    @Autowired
    private Uploader uploader;

    @Override
    public List<HotelEntity> getHotels() {
        return hotelsRepository.findAllByOrderByCreatedOnDesc();
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
        hotel.setDescription(hcr.getDescription());
        hotel.setCountryCode(hcr.getCountryCode());
        hotel.setCity(hcr.getCity());
        hotel.setStreet(hcr.getStreet());
        hotel.setNumber(hcr.getNumber());
        hotel.setStars(hcr.getStars());

        FileName image = new FileName();
        try {
            image = uploader.uploadFile(hcr.getMainImage(), "Test", ImageFolder.HOTELS);
        } catch (IOException e) {
            log.error("Error uploading image for hotel", e);
        }

        hotel.setMainImage(image.getFullName());

        UserEntity owner = usersRepository.findByUserId(hcr.getUserId());

        hotel.setOwner(owner);

        return hotelsRepository.save(hotel);
    }

    @Override
    public HotelEntity updateHotel(HotelUpdateRequest hur) throws HollException {

        HotelEntity storedHotel = hotelsRepository.findByHotelId(hur.getHotelId());

        if (storedHotel == null) {
            throw new HollException(HOTEL_NOT_FOUND, "Hotel not found", "The hotel does not exist", HttpStatus.NOT_FOUND);
        }

        return null;
    }

    @Override
    public void deleteHotel(HotelEntity hotel) throws Exception {
        hotelsRepository.delete(hotel);
    }
}
