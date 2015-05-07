package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.app.domain.Employee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
@MyController(urlPath = "/employees")
public class EmployeeController {

    @MyRequestMethod(methodType = "GET", urlPath = "/all")
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setId(14);
        employee1.setFirstName("Michael");

        Employee employee2 = new Employee();
        employee2.setId(17);
        employee2.setFirstName("Phoebe");

        employees.add(employee1);
        employees.add(employee2);

        return employees;
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
