package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.app.domain.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
@MyController(urlPath = "/departments")
public class DepartmentController {

    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();

        Department department1 = new Department();
        department1.setId(403);
        department1.setDepartmentName("IT");

        Department department2 = new Department();
        department2.setId(770);
        department2.setDepartmentName("HR");

        departments.add(department1);
        departments.add(department2);

        return departments;
    }

}
