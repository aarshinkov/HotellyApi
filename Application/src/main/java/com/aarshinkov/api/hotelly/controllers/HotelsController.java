package com.aarshinkov.api.hotelly.controllers;

import com.aarshinkov.api.hotelly.entities.HotelEntity;
import com.aarshinkov.api.hotelly.exceptions.HollException;
import com.aarshinkov.api.hotelly.requests.hotels.HotelCreateRequest;
import com.aarshinkov.api.hotelly.responses.hotels.HotelGetResponse;
import com.aarshinkov.api.hotelly.responses.users.UserGetResponse;
import com.aarshinkov.api.hotelly.services.HotelService;
import com.aarshinkov.api.hotelly.utils.ResponseCodes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@RestController
@Api(value = "Hotels", tags = "Hotels")
public class HotelsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private HotelService hotelService;

    @ApiOperation(value = "Get hotels")
    @GetMapping(value = "/api/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HotelGetResponse>> getHotels() {

        List<HotelEntity> storedHotels = hotelService.getHotels();

        List<HotelGetResponse> hotels = new ArrayList<>();

        for (HotelEntity hotel : storedHotels) {
            HotelGetResponse hgr = getHotelGetResponseFromEntity(hotel);
            hotels.add(hgr);
        }

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @ApiOperation(value = "Get particular hotel")
    @GetMapping(value = "/api/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelGetResponse> getHotel(@PathVariable("hotelId") String hotelId) {

        HotelEntity storedHotel = hotelService.getHotelByHotelId(hotelId);

        if (storedHotel == null) {
            throw new HollException(ResponseCodes.HOTEL_NOT_FOUND, "Hotel not found", "The hotel does not exist", HttpStatus.NOT_FOUND);
        }

        HotelGetResponse hotel = getHotelGetResponseFromEntity(storedHotel);

        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @ApiOperation(value = "Create hotel")
    @PostMapping(value = "/api/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelEntity> createHotel(@RequestBody HotelCreateRequest hcr) {

        HotelEntity createdHotel = hotelService.createHotel(hcr);

        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete hotel")
    @DeleteMapping(value = "/api/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteHotel(@PathVariable("hotelId") String hotelId) {

        HotelEntity hotel = hotelService.getHotelByHotelId(hotelId);

        if (hotel == null) {
            throw new HollException(ResponseCodes.HOTEL_NOT_FOUND, "Hotel not found", "The hotel does not exist", HttpStatus.NOT_FOUND);
        }

        try {
            hotelService.deleteHotel(hotel);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }
    }

    private HotelGetResponse getHotelGetResponseFromEntity(HotelEntity entity) {

        HotelGetResponse hotel = new HotelGetResponse();
        hotel.setHotelId(entity.getHotelId());
        hotel.setName(entity.getName());
        hotel.setDescription(entity.getDescription());
        hotel.setCountryCode(entity.getCountryCode());
        hotel.setCity(entity.getCity());
        hotel.setStreet(entity.getStreet());
        hotel.setNumber(entity.getNumber());
        hotel.setStars(entity.getStars());
        hotel.setMainImage(entity.getMainImage());

        UserGetResponse owner = new UserGetResponse();
        owner.setUserId(entity.getOwner().getUserId());
        owner.setEmail(entity.getOwner().getEmail());
        owner.setFirstName(entity.getOwner().getFirstName());
        owner.setLastName(entity.getOwner().getLastName());
        owner.setCreatedOn(entity.getOwner().getCreatedOn());
        owner.setEditedOn(entity.getOwner().getEditedOn());

        hotel.setOwner(owner);
        hotel.setCreatedOn(entity.getCreatedOn());
        hotel.setEditedOn(entity.getEditedOn());

        return hotel;
    }
}
