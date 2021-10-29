package atb.social.network.controller;

import atb.social.network.config.HttpUtils;
import atb.social.network.dto.*;

import atb.social.network.model.EmployeeModel;
import atb.social.network.service.EmployeeService.EmployeeService;
import atb.social.network.service.HistoryService.HistoryService;
import atb.social.network.service.QuoteService.QuoteService;
import atb.social.network.service.SubDepartmentService.SubDepartmentService;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SubDepartmentService subDepartmentService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private QuoteService quoteService;




    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/{branchId}/{depId}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllEmployeesByIDS(@PathVariable("branchId") String bID, @PathVariable("depId") String dID, HttpServletRequest httpRequest) throws Exception {
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
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable("empId") String empId,HttpServletRequest httpRequest) throws Exception {
        EmployeeDto employeeDto = null;
        String userIp = httpRequest.getHeaders("X-Real-IP").nextElement();

        try{

            employeeDto  = employeeService.getEmployeeDetails(Integer.parseInt(empId),userIp);





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
    @RequestMapping(value = "/employees/congrat-details/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeeCongrats(@PathVariable("id") String id) throws Exception {

        List<CongratsResponse> congratsResponses;

        try{

            congratsResponses  = employeeService.getCongrats(Integer.parseInt(id));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(congratsResponses, HttpStatus.OK);



    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/congrat/{empid}/{toId}" , method = RequestMethod.POST)
    public ResponseEntity<Object> congratEmployee(@PathVariable("empid") String empid,@PathVariable("toId") String toId, HttpServletRequest httpServletRequest) throws Exception {
        String userIp = httpServletRequest.getHeaders("X-Real-IP").nextElement();


        try{

             employeeService.congrat(Integer.parseInt(empid),Integer.parseInt(toId),userIp);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllEmployees(HttpServletRequest httpRequest) throws Exception {

        Set<EmployeeModel> employeeModels;



        try{

            employeeModels  = employeeService.getAllEmployee();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(employeeModels, HttpStatus.OK);



    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/save" , method = RequestMethod.POST ,consumes =  {"multipart/form-data"} )
    public ResponseEntity<Object> saveEmployee(@ModelAttribute EmployeeSaveDto employee,  @RequestParam(value = "photo", required = false) MultipartFile photo) throws Exception {




        employeeService.save(employee,photo);



        return new ResponseEntity("DONE", HttpStatus.OK);



    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/employees/edit/{id}" , method = RequestMethod.POST)
    public ResponseEntity<Object> editEmployee(@ModelAttribute EmployeeEditDto employee,@PathVariable("id") String id, @RequestParam(value = "photo", required = false) MultipartFile photo
    ) throws Exception {



          employeeService.edit(employee,Integer.parseInt(id), photo);


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
