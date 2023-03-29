package com.example.demo.service;

import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

    private DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Integer saveDog(Dog dog) {
        dog = dogRepository.save(dog);
        return dog.getId();
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public Dog getDogById(Integer id) {
        return dogRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Dog not found")
        );
    }
}
