package atb.social.network.service.EmployeeService;

import atb.social.network.dto.*;
import atb.social.network.model.EmployeeModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    EmployeeGetDetailsBankDTO getEmployeeBrief(int branchId, int departmentId) throws Exception;

    EmployeeDto getEmployeeDetails(int employeeId, String userIp) throws Exception;

    List<EmployeesBirthDayList> getEmployeeBirth() throws Exception;

    Set<EmployeeModel> getAllEmployee( ) throws Exception;

    void save(EmployeeSaveDto employee,MultipartFile multipartFile) throws Exception;

    void edit(EmployeeEditDto employeeDto, int id,MultipartFile multipartFile) throws Exception;

    void congrat(int empId, int toId,String userIp);
    List<CongratsResponse> getCongrats(int toId);

    void remove( int id) throws Exception;

}
