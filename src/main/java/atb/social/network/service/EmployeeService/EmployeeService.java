package atb.social.network.service.EmployeeService;

import atb.social.network.dto.*;
import atb.social.network.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeBriefDto> getEmployeeBrief(int branchId, int departmentId) throws Exception;

    EmployeeDto getEmployeeDetails(int employeeId) throws Exception;

    EmployeesBirthDayList getEmployeeBirth() throws Exception;

    void save(EmployeeModel employeeModel) throws Exception;

    void edit(EmployeeSaveDto employeeDto, int id, int checkBox) throws Exception;

}
