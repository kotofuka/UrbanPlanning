package com.tversu.urbanplanning.service;

import com.tversu.urbanplanning.entity.City;
import com.tversu.urbanplanning.entity.Street;
import com.tversu.urbanplanning.repository.CityRepository;
import com.tversu.urbanplanning.repository.StreetRepository;
import com.tversu.urbanplanning.util.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StreetService {
    private final StreetRepository streetRepository;
    private final CityRepository cityRepository;
    private final ValidatorUtil validator;

    // create
    public void createStreet(String streetName, String cityName) {
        validator.requireNotBlank(streetName, "Название улицы");
        validator.requireNotBlank(cityName, "Название города");

        Optional<City> city = cityRepository.findCityByName(cityName);
        validator.requirePresent(city, "Город '" + cityName + "'");

        Optional<Street> existingStreet = streetRepository.findStreetByNameAndCity(streetName, cityName);
        validator.requireAbsent(existingStreet, "Улица '" + streetName + "' в городе '" + cityName + "'");

        streetRepository.insertStreet(streetName, cityName);
    }

    // find - найти по составному ключу
    public Optional<Street> getStreetByNameAndCity(String streetName, String cityName) {
        validator.requireNotBlank(streetName, "Название улицы");
        validator.requireNotBlank(cityName, "Название города");
        return streetRepository.findStreetByNameAndCity(streetName, cityName);
    }

    // find - найти все улицы города
    public List<Street> getStreetsByCity(String cityName) {
        validator.requireNotBlank(cityName, "Название города");

        Optional<City> city = cityRepository.findCityByName(cityName);
        validator.requirePresent(city, "Город '" + cityName + "'");

        return streetRepository.findStreetsByCity(cityName);
    }

    // find - найти все
    public List<Street> getAllStreets() {
        return streetRepository.findAllStreets();
    }

    // update
    public int updateStreetName(String oldStreetName, String newStreetName, String cityName) {
        validator.requireNotBlank(oldStreetName, "Старое название улицы");
        validator.requireNotBlank(newStreetName, "Новое название улицы");
        validator.requireNotBlank(cityName, "Название города");

        Optional<Street> oldStreet = streetRepository.findStreetByNameAndCity(oldStreetName, cityName);
        validator.requirePresent(oldStreet, "Улица '" + oldStreetName + "'");

        Optional<Street> newStreet = streetRepository.findStreetByNameAndCity(newStreetName, cityName);
        validator.requireAbsent(newStreet, "Улица '" + newStreetName + "'");

        return streetRepository.updateStreetName(oldStreetName, newStreetName, cityName);
    }

    // delete
    public int deleteStreet(String streetName, String cityName) {
        validator.requireNotBlank(streetName, "Название улицы");
        validator.requireNotBlank(cityName, "Название города");

        Optional<Street> street = streetRepository.findStreetByNameAndCity(streetName, cityName);
        validator.requirePresent(street, "Улица '" + streetName + "'");

        return streetRepository.deleteStreet(streetName, cityName);
    }
}
