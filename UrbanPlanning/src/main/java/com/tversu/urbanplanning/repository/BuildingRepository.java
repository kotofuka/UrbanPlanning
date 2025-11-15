package com.tversu.urbanplanning.repository;

import com.tversu.urbanplanning.entity.Building;
import com.tversu.urbanplanning.entity.IdClass.BuildingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, BuildingId> {

    // create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO buildings (house_number, city_name, street_name) " +
                    "VALUES (:houseNumber, :cityName, :streetName)",
            nativeQuery = true)
    void insertBuilding(@Param("houseNumber") String houseNumber,
                        @Param("cityName") String cityName,
                        @Param("streetName") String streetName);

    // find - найти по составному ключу
    @Query(value = "SELECT * FROM buildings " +
                    "WHERE house_number = :houseNumber AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    Optional<Building> findBuildingByPrimaryKey(@Param("houseNumber") String houseNumber,
                                        @Param("cityName") String cityName,
                                        @Param("streetName") String streetName);

    // find all - найти все здания на улице
    @Query(value = "SELECT * FROM buildings " +
                    "WHERE city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    List<Building> findBuildingsByCityAndStreet(@Param("cityName") String cityName,
                                               @Param("streetName") String streetName);

    // find all - найти все
    @Query(value = "SELECT * FROM buildings", nativeQuery = true)
    List<Building> findAllBuildings();


    // update - изменить номер дома
    @Modifying
    @Transactional
    @Query(value = "UPDATE buildings SET house_number = :newHouseNumber " +
                    "WHERE house_number = :oldHouseNumber AND city_name = :cityName AND street_name = :streetName"
            ,nativeQuery = true)
    int updateBuildingHouseNumber(@Param("oldHouseNumber") String oldHouseNumber,
                                  @Param("newHouseNumber") String newHouseNumber,
                                  @Param("cityName") String cityName,
                                  @Param("streetName") String streetName);

    // update - переместить здание на другую улицу (в том же городе)
    @Modifying
    @Transactional
    @Query(value = "UPDATE buildings SET street_name = :newStreetName " +
                    "WHERE house_number = :houseNumber AND city_name = :cityName AND street_name = :oldStreetName",
            nativeQuery = true)
    int updateBuildingStreetOnly(@Param("houseNumber") String houseNumber,
                                 @Param("cityName") String cityName,
                                 @Param("oldStreetName") String oldStreetName,
                                 @Param("newStreetName") String newStreetName);


    // update - изменить улицу и город
    @Modifying
    @Transactional
    @Query(value = "UPDATE buildings SET street_name = :newStreetName, city_name = :newCityName " +
                    "WHERE house_number = :houseNumber AND city_name = :oldCityName AND street_name = :oldStreetName",
            nativeQuery = true)
    int updateBuildingStreetAndCity(@Param("houseNumber") String houseNumber,
                                   @Param("oldCityName") String oldCityName,
                                   @Param("oldStreetName") String oldStreetName,
                                   @Param("newCityName") String newCityName,
                                   @Param("newStreetName") String newStreetName);

    // delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM buildings " +
                    "WHERE house_number = :house_name AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int deleteBuilding(@Param("houseNumber") String houseNumber,
                       @Param("cityName") String cityName,
                       @Param("streetName") String streetName);
}
