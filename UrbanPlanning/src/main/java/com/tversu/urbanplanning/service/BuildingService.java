package com.tversu.urbanplanning.service;

import com.tversu.urbanplanning.entity.Building;
import com.tversu.urbanplanning.entity.Street;
import com.tversu.urbanplanning.repository.BuildingRepository;
import com.tversu.urbanplanning.repository.StreetRepository;
import com.tversu.urbanplanning.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final StreetRepository streetRepository;
    private final ValidatorUtil validator;


    // create
    public void createBuilding(String houseNumber, String cityName, String streetName) {
        Optional<Street> street = streetRepository.findStreetByNameAndCity(streetName, cityName);
        validator.requirePresent(street, "Улица '" + streetName + "' в городе '" + cityName + "'");

        Optional<Building> existingBuilding = buildingRepository.findBuildingByPrimaryKey(houseNumber, cityName, streetName);
        validator.requireAbsent(existingBuilding, "Здание " + houseNumber);

        buildingRepository.insertBuilding(houseNumber, cityName, streetName);
    }


    // find - найти по составному ключу
    public Optional<Building> getBuildingByPrimaryKey(String houseNumber, String cityName, String streetName) {
        return buildingRepository.findBuildingByPrimaryKey(houseNumber, cityName, streetName);
    }

    // find - найти все здания на улице
    public List<Building> getBuildingsByCityAndStreet(String streetName, String cityName) {
        Optional<Street> street = streetRepository.findStreetByNameAndCity(streetName, cityName);
        validator.requirePresent(street, "Улица '" + streetName + "'");

        return buildingRepository.findBuildingsByCityAndStreet(streetName, cityName);
    }

    // find - найти все
    public List<Building> getAllBuildings() {
        return buildingRepository.findAllBuildings();
    }


    // update - изменить номер дома
    public int updateBuildingHouseNumber(String oldHouseNumber, String newHouseNumber, String cityName, String streetName) {
        Optional<Building> oldBuilding = buildingRepository.findBuildingByPrimaryKey(oldHouseNumber, cityName, streetName);
        validator.requirePresent(oldBuilding, "Здание " + oldHouseNumber);

        Optional<Building> newBuilding = buildingRepository.findBuildingByPrimaryKey(newHouseNumber, cityName, streetName);
        validator.requireAbsent(newBuilding, "Здание " + newHouseNumber);

        return buildingRepository.updateBuildingHouseNumber(oldHouseNumber, newHouseNumber, cityName, streetName);
    }

    // update - переместить здание на другую улицу (в том же городе)
    public int updateBuildingStreetOnly(String houseNumber, String cityName, String oldStreetName, String newStreetName) {
        Optional<Building> oldBuilding = buildingRepository.findBuildingByPrimaryKey(houseNumber, cityName, oldStreetName);
        validator.requirePresent(oldBuilding, "Здание на улице '" + oldStreetName + "'");

        Optional<Street> newStreet = streetRepository.findStreetByNameAndCity(newStreetName, cityName);
        validator.requirePresent(newStreet, "Улица '" + newStreetName + "'");

        return buildingRepository.updateBuildingStreetOnly(houseNumber, cityName, oldStreetName, newStreetName);
    }

    // update - изменить улицу и город
    public int updateBuildingStreetAndCity(String houseNumber, String oldCityName, String oldStreetName,
                                           String newCityName, String newStreetName) {
        Optional<Building> oldBuilding = buildingRepository.findBuildingByPrimaryKey(houseNumber, oldCityName, oldStreetName);
        validator.requirePresent(oldBuilding, "Здание не найдено");

        Optional<Street> newStreet = streetRepository.findStreetByNameAndCity(newStreetName, newCityName);
        validator.requirePresent(newStreet, "Улица '" + newStreetName + "'");

        return buildingRepository.updateBuildingStreetAndCity(houseNumber, oldCityName, oldStreetName,
                newCityName, newStreetName);

    }

    // delete
    public int deleteBuilding(String houseNumber, String cityName, String streetName){
        Optional<Building> building = buildingRepository.findBuildingByPrimaryKey(houseNumber, cityName, streetName);
        validator.requirePresent(building, "Здание не найдено");

        return buildingRepository.deleteBuilding(houseNumber, cityName, streetName);
    }
}
