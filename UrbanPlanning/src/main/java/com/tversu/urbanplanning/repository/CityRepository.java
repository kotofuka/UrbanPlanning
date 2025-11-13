package com.tversu.urbanplanning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository<City> extends JpaRepository<City, String> {
    // create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cities (name) VALUES (:name)", nativeQuery = true)
    void insertCity(@Param("name") String name);

    // find by name
    @Query(value = "SELECT * FROM cities WHERE name = :name", nativeQuery = true)
    Optional<City> findCityByName(@Param("name") String name);

    // find all
    @Query(value = "SELECT * FROM cities", nativeQuery = true)
    List<City> findAllCities();

    // update
    @Modifying
    @Transactional
    @Query(value = "UPDATE cities SET name = :newName WHERE name = :oldName", nativeQuery = true)
    int updateCityName(@Param("oldName") String oldName, @Param("newName") String newName);

    // delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cities WHERE name = :name", nativeQuery = true)
    int deleteCity(@Param("name") String name);
}
