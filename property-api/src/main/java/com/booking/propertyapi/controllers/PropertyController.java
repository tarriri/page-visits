package com.booking.propertyapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.booking.propertyapi.models.DTO.PropertyDTO;
import com.booking.propertyapi.services.PropertyService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/{id}")
    public PropertyDTO get(@PathVariable("id") UUID id){
        return propertyService.GetPropertyById(id);
    }

    @PostMapping()
    public PropertyDTO post(@RequestBody PropertyDTO property)
    {
        if(property.getName().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return propertyService.CreateProperty(property.getName());
    }

    @GetMapping("/")
    public List<PropertyDTO> getAll(){
        return propertyService.GetAllProperties();
    }
}
