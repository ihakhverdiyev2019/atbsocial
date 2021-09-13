package atb.social.network.controller;

import atb.social.network.dto.EmployeeBriefDto;
import atb.social.network.dto.EmployeeDto;

import atb.social.network.service.EmployeeService.EmployeeService;
import atb.social.network.service.SubDepartmentService.SubDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SubDepartmentService subDepartmentService;


    @RequestMapping(value = "/employees/{branchId}/{depId}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllEmployees(@PathVariable("branchId") String bID, @PathVariable("depId") String dID) throws Exception {
        List<EmployeeBriefDto> employeeModels = new ArrayList<>();
        try{

            employeeModels  = employeeService.getEmployeeBrief(Integer.parseInt(bID),Integer.parseInt(dID));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeeModels, HttpStatus.OK);



    }


    @RequestMapping(value = "/employee/{empId}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable("empId") String empId) throws Exception {
        EmployeeDto employeeDto = null;
        try{

            employeeDto  = employeeService.getEmployeeDetails(Integer.parseInt(empId));





        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeeDto, HttpStatus.OK);



    }




}
