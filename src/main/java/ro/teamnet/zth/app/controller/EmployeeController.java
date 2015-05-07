package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
@MyController(urlPath = "/employees")
public class EmployeeController {

    @MyRequestMethod(methodType = "GET", urlPath = "/all")
    public String getAllEmployees() {
        return "allEmployees";
    }

    @MyRequestMethod(methodType = "GET", urlPath = "/one")
    public String getOneEmployee() {
        return "oneRandomEmployee";
    }

}
