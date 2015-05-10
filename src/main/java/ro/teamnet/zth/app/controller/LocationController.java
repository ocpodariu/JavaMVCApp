package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.app.domain.Location;
import ro.teamnet.zth.app.service.LocationService;
import ro.teamnet.zth.app.service.LocationServiceImpl;

import java.util.List;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
@MyController(urlPath = "/locations")
public class LocationController {

    private LocationService locationService = new LocationServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Location> getAllLocations() {
        return locationService.findAllLocations();
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneLocation() {
        return "oneRandomLocation";
    }

}
