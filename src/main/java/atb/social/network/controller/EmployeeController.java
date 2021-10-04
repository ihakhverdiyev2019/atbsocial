package atb.social.network.controller;

import atb.social.network.dto.*;

import atb.social.network.model.EmployeeModel;
import atb.social.network.service.EmployeeService.EmployeeService;
import atb.social.network.service.HistoryService.HistoryService;
import atb.social.network.service.SubDepartmentService.SubDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SubDepartmentService subDepartmentService;

    @Autowired
    private HistoryService historyService;



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/{branchId}/{depId}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllEmployeesByIDS(@PathVariable("branchId") String bID, @PathVariable("depId") String dID) throws Exception {
        EmployeeGetDetailsBankDTO employeeGetDetailsBankDTO;
        try{


            employeeGetDetailsBankDTO  = employeeService.getEmployeeBrief(Integer.parseInt(bID),Integer.parseInt(dID));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeeGetDetailsBankDTO, HttpStatus.OK);



    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/birthday" , method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeeBirht() throws Exception {

        List<EmployeesBirthDayList> employeesBirthDayList;

        try{

            employeesBirthDayList  = employeeService.getEmployeeBirth();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeesBirthDayList, HttpStatus.OK);



    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/changes" , method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeeChanges() throws Exception {

        List<EmployeePositionChangesDto> employeePositionChangesDtos;

        try{

            employeePositionChangesDtos  = historyService.getDetails();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeePositionChangesDtos, HttpStatus.OK);



    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllEmployees() throws Exception {

        List<EmployeeModel> employeeModels;

        try{

            employeeModels  = employeeService.getAllEmployee();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeeModels, HttpStatus.OK);



    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/save" , method = RequestMethod.POST)
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeSaveDto employee) throws Exception {
        try{
            EmployeeModel employeeModel = new EmployeeModel();

            if(!employee.getBirthDay().equals("")) {

                String str = employee.getBirthDay();
                String result = str.substring(5);
                result = result.replaceAll("-", "");

                employeeModel.setBirhtDate(employee.getBirthDay());
                employeeModel.setFilterBirth(result);

            }
            employeeModel.setBranchId(employee.getBranchId());
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
            employeeModel.setDepartmentId(employee.getDepartId());

          employeeService.save(employeeModel);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/edit/{id}" , method = RequestMethod.POST)
    public ResponseEntity<Object> editEmployee(@RequestBody EmployeeEditDto employee,@PathVariable("id") String id) throws Exception {
        try{
         employeeService.edit(employee,Integer.parseInt(id));

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/remove/{id}" , method = RequestMethod.POST)
    public ResponseEntity<Object> remoceEmployee(@PathVariable("id") String id) throws Exception {
        try{
            employeeService.remove(Integer.parseInt(id));

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }



}
