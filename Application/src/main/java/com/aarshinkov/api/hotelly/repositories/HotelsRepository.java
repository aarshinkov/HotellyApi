package com.aarshinkov.api.hotelly.repositories;

import com.aarshinkov.api.hotelly.entities.HotelEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Repository
public interface HotelsRepository extends JpaRepository<HotelEntity, String> {

    List<HotelEntity> findAllByOrderByCreatedOnDesc();

    HotelEntity findByHotelId(String hotelId);
}
