package com.tversu.urbanplanning.repository;

import com.tversu.urbanplanning.entity.Creator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatorRepository extends CrudRepository<Creator, String> {
    // create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO creators (full_name) VALUES (:fullName)", nativeQuery = true)
    void insertCreator(@Param("fullName") String fullName);

    // find - найти по имени
    @Query(value = "SELECT * FROM creators WHERE full_name = :fullName", nativeQuery = true)
    Optional<Creator> findCreatorByFullName(@Param("fullName") String fullName);

    // find - найти все
    @Query(value = "SELECT * FROM creators", nativeQuery = true)
    List<Creator> findAllCreators();

    // update
    @Modifying
    @Transactional
    @Query(value = "UPDATE creators SET full_name = :newFullName WHERE full_name = :oldFullName",
            nativeQuery = true)
    int updateCreatorFullName(@Param("oldFullName") String oldFullName,
                              @Param("newFullName") String newFullName);

    // delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM creators WHERE full_name = :fullName", nativeQuery = true)
    int deleteCreator(@Param("fullName") String fullName);
}
