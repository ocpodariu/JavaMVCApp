package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
@MyController(urlPath = "/departments")
public class LocationController {

    @MyRequestMethod(urlPath = "/all")
    public String getAllLocations() {
        return "allLocations";
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneLocation() {
        return "oneRandomLocation";
    }

}
