package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Dog;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dog")
@CrossOrigin(origins = "*")
public class DogController {

    private DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping("/save")
    public Integer saveDog(@RequestBody Dog dog) {
        return dogService.saveDog(dog);
    }

    @GetMapping("/list")
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable("id") Integer id) {
        return dogService.getDogById(id);
    }
}
