package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.app.domain.Employee;
import ro.teamnet.zth.app.service.EmployeeService;
import ro.teamnet.zth.app.service.EmployeeServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
@MyController(urlPath = "/employees")
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @MyRequestMethod(methodType = "GET", urlPath = "/all")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @MyRequestMethod(methodType = "GET", urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParam(paramName = "idEmployee") String idEmployee) {
        Integer id = Integer.valueOf(idEmployee);
        return employeeService.findOneEmployee(id);
    }

}
