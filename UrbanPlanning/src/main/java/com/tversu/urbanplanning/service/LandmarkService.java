package com.tversu.urbanplanning.service;

import com.tversu.urbanplanning.dto.LandmarkDto.AllDataLandmarkRequest;
import com.tversu.urbanplanning.dto.LandmarkDto.UpdateLandmarkCoordinatesOnlyRequest;
import com.tversu.urbanplanning.entity.Landmark;
import com.tversu.urbanplanning.entity.Street;
import com.tversu.urbanplanning.repository.LandmarkRepository;
import com.tversu.urbanplanning.repository.StreetRepository;
import com.tversu.urbanplanning.util.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;
    private final StreetRepository streetRepository;
    private final ValidatorUtil validator;

    public LandmarkService(LandmarkRepository landmarkRepository, StreetRepository streetRepository, ValidatorUtil validator) {
        this.landmarkRepository = landmarkRepository;
        this.streetRepository = streetRepository;
        this.validator = validator;
    }

    // create
    public void createLandmark(AllDataLandmarkRequest request){
        Optional<Street> street = streetRepository.findStreetByNameAndCity(request.streetName(), request.cityName());
        validator.requirePresent(street, "Улица '" + request.streetName() + "'");

        Optional<Landmark> existingLandmark = landmarkRepository.findLandmarkByPrimaryKey(request.name(),
                request.cityName(), request.streetName());

        validator.requireAbsent(existingLandmark, "Достопримечательность '" + request.name() + "'");

        landmarkRepository.insertLandmark(
                request.name(),
                request.description(),
                request.latitude(),
                request.longitude(),
                request.cityName(),
                request.streetName()
        );
    }

    // find - найти по составному ключу
    public Optional<Landmark> getLandmarkByKey(String name, String cityName, String streetName){
        return landmarkRepository.findLandmarkByPrimaryKey(name, cityName, streetName);
    }

    // find - найти все достопримечательности на улице
    public List<Landmark> getLandmarksByStreet(String streetName, String cityName){
        Optional<Street> street =  streetRepository.findStreetByNameAndCity(streetName, cityName);
        validator.requirePresent(street, "Улица '" + streetName + "'");

        return landmarkRepository.findLandmarksByStreet(streetName, cityName);
    }

    // find - найти все достопримечательности города
    public List<Landmark> getLandmarksByCity(String cityName) {
        return landmarkRepository.findLandmarkByCity(cityName);
    }

    // find - найти все
    public List<Landmark> getAllLandmarks() {
        return landmarkRepository.findAllLandmarks();
    }

    // update - обновить описание
    public int updateLandmarkDescription(String name, String cityName, String streetName, String description){
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(name, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность '" + name + "'");

        return landmarkRepository.updateLandmarkDescription(name, cityName, streetName, description);
    }

    // update - обновить обе координаты
    public int updateLandmarkCoordinates(String name, String cityName, String streetName,
                                         UpdateLandmarkCoordinatesOnlyRequest request){
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(name, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность '" + name + "'");

        return landmarkRepository.updateLandmarkCoordinates(name, cityName, streetName,
                request.latitude(), request.longitude());
    }

    // update - обновить широту
    public int updateLandmarkLatitude(String name, String cityName, String streetName, BigDecimal newLatitude){
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(name, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность '" + name + "'");

        return landmarkRepository.updateLandmarkLatitude(name, cityName, streetName,
                newLatitude);
    }

    // update - обновить долготу
    public int updateLandmarkLongitude(String name, String cityName, String streetName, BigDecimal newLongitude){
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(name, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность '" + name + "'");

        return landmarkRepository.updateLandmarkLongitude(name, cityName, streetName,
                newLongitude);
    }

    // update - обновить все дополнительные данные
    public int updateLandmarkAdditionalData(AllDataLandmarkRequest request){
        Optional<Street> street = streetRepository.findStreetByNameAndCity(request.streetName(), request.cityName());
        validator.requirePresent(street, "Улица '" + request.streetName() + "'");

        Optional<Landmark> existingLandmark = landmarkRepository.findLandmarkByPrimaryKey(request.name(),
                request.cityName(), request.streetName());

        validator.requirePresent(existingLandmark, "Достопримечательность '" + request.name() + "'");

        return landmarkRepository.updateLandmarkAdditionalData(
                request.name(),
                request.cityName(),
                request.streetName(),
                request.description(),
                request.latitude(),
                request.longitude()
        );
    }


    // delete
    public int deleteLandmark(String name, String cityName, String streetName) {
        Optional<Landmark> landmark = landmarkRepository.findLandmarkByPrimaryKey(name, cityName, streetName);
        validator.requirePresent(landmark, "Достопримечательность не найдена");

        return landmarkRepository.deleteLandmark(name, cityName, streetName);
    }
}
