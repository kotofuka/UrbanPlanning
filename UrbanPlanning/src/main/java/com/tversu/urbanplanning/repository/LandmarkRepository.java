package com.tversu.urbanplanning.repository;

import com.tversu.urbanplanning.entity.IdClass.LandmarkId;
import com.tversu.urbanplanning.entity.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, LandmarkId> {
    // create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO landmarks (name, description, latitude, longitude, city_name, street_name) " +
                    "VALUES (:name, :description, :latitude, :longitude, :cityName, :streetName)",
            nativeQuery = true)
    void insertLandmark(@Param("name") String name,
                        @Param("description") String description,
                        @Param("latitude") BigDecimal latitude,
                        @Param("longitude") BigDecimal longitude,
                        @Param("cityName") String cityName,
                        @Param("streetName") String streetName);

    // find - найти по составному ключу


    // find - найти все достопримечательности на улице


    // find - найти все достопримечательности города


    // find - найти все


    // update - обновить описание


    // update - обновить координаты


    // update - обновить все дополнительные данные


    // delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM landmarks " +
                    "WHERE name = :name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int deleteLandmark(@Param("name") String name,
                       @Param("cityName") String cityName,
                       @Param("streetName") String streetName);
}
