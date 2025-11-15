package com.tversu.urbanplanning.service;

import com.tversu.urbanplanning.entity.Creator;
import com.tversu.urbanplanning.entity.Landmark;
import com.tversu.urbanplanning.entity.ParticipationInCreation;
import com.tversu.urbanplanning.repository.CreatorRepository;
import com.tversu.urbanplanning.repository.LandmarkRepository;
import com.tversu.urbanplanning.repository.ParticipationInCreationRepository;
import com.tversu.urbanplanning.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParticipationInCreationService {
    private final ParticipationInCreationRepository participationInCreationRepository;
    private final LandmarkRepository landmarkRepository;
    private final CreatorRepository creatorRepository;
    private final ValidatorUtil validator;

    public ParticipationInCreationService(ParticipationInCreationRepository participationInCreationRepository,
                                          LandmarkRepository landmarkRepository,
                                          CreatorRepository creatorRepository,
                                          ValidatorUtil validator) {
        this.participationInCreationRepository = participationInCreationRepository;
        this.landmarkRepository = landmarkRepository;
        this.creatorRepository = creatorRepository;
        this.validator = validator;
    }

    // create
    public void createParticipation(String landmarkName, String cityName, String streetName, String creatorName) {
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(landmarkName, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность '" + landmarkName + "'");

        Optional<Creator> creator = creatorRepository.findCreatorByFullName(creatorName);
        validator.requirePresent(creator, "Создатель '" + creatorName + "'");

        Optional<ParticipationInCreation> existingParticipation =
                participationInCreationRepository.findParticipationByPrimaryKey(landmarkName, cityName, streetName,
                        creatorName);
        validator.requireAbsent(existingParticipation, "Это участие");

        participationInCreationRepository.insertParticipation(landmarkName, cityName, streetName, creatorName);
    }

    // find - найти участника и достопримечательность по ключу
    public Optional<ParticipationInCreation> getParticipationByKey(String landmarkName, String cityName,
                                                                   String streetName, String creatorName) {

        return participationInCreationRepository.findParticipationByPrimaryKey(landmarkName, cityName, streetName,
                creatorName);
    }

    // find - найти всех создателей достопримечательности
    public List<ParticipationInCreation> getParticipationsByLandmark(String landmarkName, String cityName,
                                                                     String streetName) {
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(landmarkName, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность '" + landmarkName + "'");

        return participationInCreationRepository.findParticipationsByLandmark(landmarkName, cityName, streetName);
    }

    // find - найти все достопримечательности создателя
    public List<ParticipationInCreation> getParticipationsByCreator(String creatorName) {
        Optional<Creator> creator = creatorRepository.findCreatorByFullName(creatorName);
        validator.requirePresent(creator, "Создатель '" + creatorName + "'");

        return participationInCreationRepository.findParticipationsByCreator(creatorName);
    }

    // find - достать все записи таблицы
    public List<ParticipationInCreation> getAllParticipations() {
        return participationInCreationRepository.findAllParticipations();
    }

    // delete - удалить одно участие для достопримечательности
    public int deleteParticipation(String landmarkName, String cityName, String streetName, String creatorName) {
        Optional<ParticipationInCreation> participation =
                participationInCreationRepository.findParticipationByPrimaryKey(landmarkName, cityName, streetName,
                        creatorName);
        validator.requirePresent(participation, "Участие не найдено");

        return participationInCreationRepository.deleteParticipation(landmarkName, cityName, streetName, creatorName);
    }

    // delete - удалить все участия для достопримечательности
    public int deleteParticipationsByLandmark(String landmarkName, String cityName, String streetName) {
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(landmarkName, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность '" + landmarkName + "'");

        return participationInCreationRepository.deleteParticipationsByLandmark(landmarkName, cityName, streetName);
    }
}
