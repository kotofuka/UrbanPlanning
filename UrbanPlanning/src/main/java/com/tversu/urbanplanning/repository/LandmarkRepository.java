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
import java.util.List;
import java.util.Optional;

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
    @Query(value = "SELECT * FROM landmarks " +
                    "WHERE name = :name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    Optional<Landmark> findLandmarkByPrimaryKey(@Param("name") String name,
                                                @Param("cityName") String cityName,
                                                @Param("streetName") String streetName);

    // find - найти все достопримечательности на улице
    @Query(value = "SELECT * FROM landmarks " +
                    "WHERE street_name = :streetName AND city_name = :cityName",
            nativeQuery = true)
    List<Landmark> findLandmarksByStreet(@Param("streetName") String streetName,
                                         @Param("cityName") String cityName);

    // find - найти все достопримечательности города
    @Query(value = "SELECT * FROM landmarks WHERE city_name = :cityName",
            nativeQuery = true)
    List<Landmark> findLandmarkByCity(@Param("cityName") String cityName);

    // find - найти все
    @Query(value = "SELECT * FROM landmarks", nativeQuery = true)
    List<Landmark> findAllLandmarks();

    // update - обновить описание
    @Modifying
    @Transactional
    @Query(value = "UPDATE landmarks SET description = :newDecription " +
                    "WHERE name = :name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int updateLandmarkDescription(@Param("name") String name,
                                  @Param("cityName") String cityName,
                                  @Param("streetName") String streetName,
                                  @Param("newDescription") String newDescription);

    // update - обновить обе координаты
    @Modifying
    @Transactional
    @Query(value = "UPDATE landmarks SET latitude = :newLatitude, longitude = :newLongitude " +
                    "WHERE name = :name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int updateLandmarkCoordinates(@Param("name") String name,
                                  @Param("cityName") String cityName,
                                  @Param("streetName") String streetName,
                                  @Param("newLatitude") BigDecimal newLatitude,
                                  @Param("newLongitude") BigDecimal newLongitude);

    // update - обновить широту
    @Modifying
    @Transactional
    @Query(value = "UPDATE landmarks SET latitude = :newLatitude " +
            "WHERE name = :name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int updateLandmarkLatitude(@Param("name") String name,
                                  @Param("cityName") String cityName,
                                  @Param("streetName") String streetName,
                                  @Param("newLatitude") BigDecimal newLatitude);

    // update - обновить долготу
    @Modifying
    @Transactional
    @Query(value = "UPDATE landmarks SET longitude = :newLongitude " +
            "WHERE name = :name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int updateLandmarkLongitude(@Param("name") String name,
                                  @Param("cityName") String cityName,
                                  @Param("streetName") String streetName,
                                  @Param("newLongitude") BigDecimal newLongitude);

    // update - обновить все дополнительные данные
    @Modifying
    @Transactional
    @Query(value = "UPDATE landmarks SET description = :newDescription, latitude = :newLatitude, longitude = :newLongitude " +
                    "WHERE name = :name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int updateLandmarkCoordinates(@Param("name") String name,
                                  @Param("cityName") String cityName,
                                  @Param("streetName") String streetName,
                                  @Param("newDescription") String newDescription,
                                  @Param("newLatitude") BigDecimal newLatitude,
                                  @Param("newLongitude") BigDecimal newLongitude);

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
