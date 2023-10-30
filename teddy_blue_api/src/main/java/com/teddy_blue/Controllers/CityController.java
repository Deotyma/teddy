package com.teddy_blue.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.City;
import com.teddy_blue.Entities.CityId;
import com.teddy_blue.Services.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{zipCode}/{cityName}")
    public ResponseEntity<City> getCityById(@PathVariable String zipCode, @PathVariable String cityName) {
        return cityService.getCityById(new CityId(zipCode, cityName))
                .map(city -> ResponseEntity.ok(city))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @DeleteMapping("/{zipCode}/{cityName}")
    public void deleteCity(@PathVariable String zipCode, @PathVariable String cityName) {
        cityService.deleteCity(new CityId(zipCode, cityName));
    }
}