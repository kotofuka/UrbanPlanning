package com.tversu.urbanplanning.service;

import com.tversu.urbanplanning.entity.Creator;
import com.tversu.urbanplanning.repository.CreatorRepository;
import com.tversu.urbanplanning.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatorService {
    private final CreatorRepository creatorRepository;
    private final ValidatorUtil validator;

    // create
    public void createCreator(String fullName) {
        Optional<Creator> existingCreator = creatorRepository.findCreatorByFullName(fullName);
        validator.requireAbsent(existingCreator, "Создатель '" + fullName + "'");

        creatorRepository.insertCreator(fullName);
    }

    // find - найти по имени
    public Optional<Creator> getCreatorByName(String fullName) {
        return creatorRepository.findCreatorByFullName(fullName);
    }

    // find - найти все
    public List<Creator> getAllCreators() {
        return creatorRepository.findAllCreators();
    }

    // update
    public int updateCreator(String oldName, String newName) {
        Optional<Creator> oldCreator = creatorRepository.findCreatorByFullName(oldName);
        validator.requirePresent(oldCreator, "Создатель '" + oldName + "'");

        Optional<Creator> newCreator = creatorRepository.findCreatorByFullName(newName);
        validator.requireAbsent(newCreator, "Создатель '" + newName + "'");

        return creatorRepository.updateCreatorFullName(oldName, newName);
    }

    // delete
    public int deleteCreator(String fullName) {
        Optional<Creator> creator = creatorRepository.findCreatorByFullName(fullName);
        validator.requirePresent(creator, "Создатель '" + fullName + "'");

        return creatorRepository.deleteCreator(fullName);
    }
}
