package atb.social.network.dto;

import java.util.List;

public class EmployeeGetDetailsBankDTO {

    private EmployeeBriefDetailsDto director;
    private List<EmployeeBriefDto> employeeBriefDtos;

    public EmployeeGetDetailsBankDTO() {
    }

    public EmployeeBriefDetailsDto getDirector() {
        return director;
    }

    public void setDirector(EmployeeBriefDetailsDto director) {
        this.director = director;
    }

    public List<EmployeeBriefDto> getEmployeeBriefDtos() {
        return employeeBriefDtos;
    }

    public void setEmployeeBriefDtos(List<EmployeeBriefDto> employeeBriefDtos) {
        this.employeeBriefDtos = employeeBriefDtos;
    }
}
