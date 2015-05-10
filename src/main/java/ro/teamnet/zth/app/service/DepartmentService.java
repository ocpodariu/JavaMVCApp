package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.domain.Department;

import java.util.List;

/**
 * Author: Ovidiu
 * Date:   5/10/2015
 */
public interface DepartmentService {

    List<Department> findAllDepartments();

}
