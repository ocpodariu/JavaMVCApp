package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.app.domain.Employee;

import java.sql.Date;

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
    public Employee getOneEmployee() {
        Employee employee = new Employee();
        employee.setId(14);
        employee.setFirstName("Foo");
        employee.setLastName("Bar");
        employee.setEmail("fb@gmail.com");
        employee.setPhoneNumber("555-13706");

        return employee;
    }

}
