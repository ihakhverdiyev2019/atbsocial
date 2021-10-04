package atb.social.network.service.EmployeeService;

import atb.social.network.dto.*;
import atb.social.network.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    EmployeeGetDetailsBankDTO getEmployeeBrief(int branchId, int departmentId) throws Exception;

    EmployeeDto getEmployeeDetails(int employeeId) throws Exception;

    List<EmployeesBirthDayList> getEmployeeBirth() throws Exception;

    List<EmployeeModel> getAllEmployee( ) throws Exception;

    void save(EmployeeModel employeeModel) throws Exception;

    void edit(EmployeeEditDto employeeDto, int id) throws Exception;


    void remove( int id) throws Exception;

}
