package com.tversu.urbanplanning.service;

import com.tversu.urbanplanning.entity.City;
import com.tversu.urbanplanning.repository.CityRepository;
import com.tversu.urbanplanning.util.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@Transactional
public class CityService {
    private final CityRepository cityRepository;
    private final ValidatorUtil validator;

    public CityService(CityRepository cityRepository, ValidatorUtil validator) {
        this.cityRepository = cityRepository;
        this.validator = validator;
    }

    // create
    public void createCity(String name) {
        Optional<City> existingCity = cityRepository.findCityByName(name);
        validator.requireAbsent(existingCity, "Город '" + name + "'");

        cityRepository.insertCity(name);
    }

    // find - найти по имени
    public Optional<City> getCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

    // find - найти все
    public List<City> getAllCities() {
        return cityRepository.findAllCities();
    }

    // update
    public int updateCity(String oldName, String newName) {
        Optional<City> oldCity = cityRepository.findCityByName(oldName);
        validator.requirePresent(oldCity, "Город '" + oldName + "'");

        Optional<City> newCity = cityRepository.findCityByName(newName);
        validator.requireAbsent(newCity, "Город '" + newName + "'");

        return cityRepository.updateCityName(oldName, newName);
    }

    // delete
    public int deleteCity(String name) {
        Optional<City> city = cityRepository.findCityByName(name);
        validator.requirePresent(city, "Город '" + name + "'");

        return cityRepository.deleteCity(name);
    }
}
