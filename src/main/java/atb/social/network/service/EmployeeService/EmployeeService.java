package atb.social.network.service.EmployeeService;

import atb.social.network.dto.EmployeeBirhtDto;
import atb.social.network.dto.EmployeeBriefDto;
import atb.social.network.dto.EmployeeDto;
import atb.social.network.dto.EmployeesBirthDayList;
import atb.social.network.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeBriefDto> getEmployeeBrief(int branchId, int departmentId) throws Exception;

    EmployeeDto getEmployeeDetails(int employeeId) throws Exception;

    EmployeesBirthDayList getEmployeeBirth() throws Exception;

    void save(EmployeeModel employeeModel);

}
