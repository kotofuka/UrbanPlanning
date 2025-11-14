package com.tversu.urbanplanning.repository;

import com.tversu.urbanplanning.entity.IdClass.ParticipationInCreationId;
import com.tversu.urbanplanning.entity.ParticipationInCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationInCreationRepository extends JpaRepository<ParticipationInCreation, ParticipationInCreationId> {
    // create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO participations_in_creation (landmark_name, city_name, street_name, creator_full_name) " +
                    "VALUES (:landmarkName, :cityName, :streetName, :creatorFullName)",
            nativeQuery = true)
    void insertParticipation(@Param("landmarkName") String landmarkName,
                             @Param("cityName") String cityName,
                             @Param("streetName") String streetName,
                             @Param("creatorFullName") String creatorFullName);

    // find - найти участника и достопримечательность по ключу
    @Query(value = "SELECT * FROM participations_in_creation " +
                    "WHERE landmark_name = :landmarkName AND city_name = :cityName AND " +
                        "street_name = :streetName AND creator_full_name = :creatorFullName",
            nativeQuery = true)
    Optional<ParticipationInCreation> findParticipationByPrimaryKey(@Param("landmarkName") String landmarkName,
                                                                    @Param("cityName") String cityName,
                                                                    @Param("streetName") String streetName,
                                                                    @Param("creatorFullName") String creatorFullName);

    // find - найти всех создателей достопримечательности
    @Query(value = "SELECT * FROM participations_in_creation " +
            "WHERE landmark_name = :landmarkName AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    List<ParticipationInCreation> findParticipationsByLandmark(@Param("landmarkName") String landmarkName,
                                                               @Param("cityName") String cityName,
                                                               @Param("streetName") String streetName);

    // find - найти все достопримечательности создателя
    @Query(value = "SELECT * FROM participations_in_creation " +
            "WHERE creator_full_name = :creatorFullName",
            nativeQuery = true)
    List<ParticipationInCreation> findParticipationsByCreator(@Param("creatorFullName") String creatorFullName);

    // find - достать все записи таблицы
    @Query(value = "SELECT * FROM participations_in_creation", nativeQuery = true)
    List<ParticipationInCreation> findAllParticipations();

    // delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM participations_in_creation " +
                    "WHERE landmark_name = :landmarkName AND city_name = :cityName AND " +
                        "street_name = :streetName AND creator_full_name = :creatorFullName",
            nativeQuery = true)
    int deleteParticipation(@Param("landmarkName") String landmarkName,
                            @Param("cityName") String cityName,
                            @Param("streetName") String streetName,
                            @Param("creatorFullName") String creatorFullName);

    // delete - удалить все участия для достопримечательности
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM participations_in_creation " +
                    "WHERE landmark_name = :landmarkName AND city_name = :cityName AND street_name = :streetName",
            nativeQuery = true)
    int deleteParticipationByLandmark(@Param("landmarkName") String landmarkName,
                                      @Param("cityName") String cityName,
                                      @Param("streetName") String streetName);
}
