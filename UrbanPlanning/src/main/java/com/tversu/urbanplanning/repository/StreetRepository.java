package com.tversu.urbanplanning.repository;

import com.tversu.urbanplanning.entity.IdClass.StreetId;
import com.tversu.urbanplanning.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street, StreetId> {

    // create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO streets (name, city_name) VALUES (:name, :cityName)", nativeQuery = true)
    void insertStreet(@Param("name") String name, @Param("cityName") String cityName);

    // find - найти по составному ключу
    @Query(value = "SELECT * FROM streets WHERE name = :name AND city_name = :cityName", nativeQuery = true)
    Optional<Street> findStreetByNameAndCity(@Param("name") String name, @Param("cityName") String cityName);

    // find all - найти все улицы города
    @Query(value = "SELECT * FROM streets WHERE city_name = :cityName", nativeQuery = true)
    List<Street> findStreetsByCity(@Param("cityName") String cityName);

    // find all - найти все улицы
    @Query(value = "SELECT * FROM streets", nativeQuery = true)
    List<Street> findAllStreets();

    // update
    @Modifying
    @Transactional
    @Query(value = "UPDATE streets SET name = :newName WHERE name = :oldName AND city_name = :cityName",
            nativeQuery = true)
    int updateStreetName(@Param("oldName") String oldName,
                         @Param("newName") String newName,
                         @Param("cityName") String cityName);

    // delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM streets WHERE name = :name AND city_name = :cityName", nativeQuery = true)
    int deleteStreet(@Param("name") String name, @Param("cityName") String cityName);
}
