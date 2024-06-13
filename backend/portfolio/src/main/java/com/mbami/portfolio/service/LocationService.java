package com.mbami.portfolio.service;

import java.util.List; // Add this import statement

import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.Location;
import com.mbami.portfolio.repository.LocationRepository;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
    }

    public Location getLocation(long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public void updateLocation(long id, Location location) {
        Location existingLocation = locationRepository.findById(id).orElse(null);
        if (existingLocation != null) {
            existingLocation.setCity(location.getCity());
            existingLocation.setCountry(location.getCountry());
            existingLocation.setState(location.getState());
            existingLocation.setZip(location.getZip());
            locationRepository.save(existingLocation);
        }
    }

    public void deleteAllLocations() {
        locationRepository.deleteAll();
    }

    public boolean isLocationExist(long id) {
        return locationRepository.existsById(id);
    }

    public long getLocationsCount() {
        return locationRepository.count();
    }

    public void saveLocations(List<Location> locations) {
        locationRepository.saveAll(locations);
    }

    public void deleteLocations(List<Long> ids) {
        for (long id : ids) {
            locationRepository.deleteById(id);
        }
    }

    public void updateLocations(List<Location> locations) {
        for (Location location : locations) {
            updateLocation(location.getId(), location);
        }
    }

}
