package atb.social.network.controller;

import atb.social.network.dto.*;

import atb.social.network.model.EmployeeModel;
import atb.social.network.service.EmployeeService.EmployeeService;
import atb.social.network.service.SubDepartmentService.SubDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/employees/{empId}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable("empId") String empId) throws Exception {
        EmployeeDto employeeDto = null;
        try{

            employeeDto  = employeeService.getEmployeeDetails(Integer.parseInt(empId));





        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeeDto, HttpStatus.OK);



    }


    @RequestMapping(value = "/employees/birthday" , method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeeBirht() throws Exception {

        EmployeesBirthDayList employeesBirthDayList;

        try{

            employeesBirthDayList  = employeeService.getEmployeeBirth();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeesBirthDayList, HttpStatus.OK);



    }


    @RequestMapping(value = "/employees/save" , method = RequestMethod.GET)
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeSaveDto employee) throws Exception {
        try{

            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setBirhtDate(employee.getBirthDay());
            employeeModel.setBranchId(employee.getBranchId());
            employeeModel.setBirthDayWithoutYear(employee.getBirthDay());
            employeeModel.setBirhtDate(employee.getBirthDay());
            employeeModel.setEmail(employee.getEmail());
            employeeModel.setInternalNumber(employee.getInternalNum());
            employeeModel.setName(employee.getName());
            employeeModel.setPhoneNumber(employee.getNumber());
            employeeModel.setPhotoBase64(employee.getPhoto());
            employeeModel.setPosition(employee.getPosition());
            employeeModel.setStartJobDate(employee.getStartDate());
            employeeModel.setSurname(employee.getSurname());
            employeeModel.setSubDepartment(employee.getSubDepartId());

          employeeService.save(employeeModel);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }




}
