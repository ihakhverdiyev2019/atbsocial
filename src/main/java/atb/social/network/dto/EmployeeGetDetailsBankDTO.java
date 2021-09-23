package atb.social.network.dto;

import java.util.List;

public class EmployeeGetDetailsBankDTO {

    private List<EmployeeBriefDetailsDto> main;
    private List<EmployeeBriefDto> employeeBriefDtos;

    public EmployeeGetDetailsBankDTO() {
    }

    public List<EmployeeBriefDetailsDto> getMain() {
        return main;
    }

    public void setMain(List<EmployeeBriefDetailsDto> main) {
        this.main = main;
    }

    public List<EmployeeBriefDto> getEmployeeBriefDtos() {
        return employeeBriefDtos;
    }

    public void setEmployeeBriefDtos(List<EmployeeBriefDto> employeeBriefDtos) {
        this.employeeBriefDtos = employeeBriefDtos;
    }
}
