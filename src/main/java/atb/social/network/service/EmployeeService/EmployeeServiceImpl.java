package atb.social.network.service.EmployeeService;

import atb.social.network.dto.EmployeeBriefDetailsDto;
import atb.social.network.dto.EmployeeBriefDto;
import atb.social.network.dto.EmployeeDto;
import atb.social.network.model.EmployeeModel;
import atb.social.network.repository.BankBranchRepository;
import atb.social.network.repository.BankDepartmentRepository;
import atb.social.network.repository.EmployeeRepository;
import atb.social.network.repository.SubDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BankDepartmentRepository bankDepartmentRepository;

    @Autowired
    private BankBranchRepository bankBranchRepository;

    @Autowired
    private SubDepartmentRepository subDepartmentRepository;



    @Override
    public List<EmployeeBriefDto> getEmployeeBrief(int branchId, int departmentId) throws Exception {
        List<EmployeeModel> employeeModels=null;
        List<Integer> depId = new ArrayList<>();

        List<EmployeeBriefDto> employeeBriefDtos = new ArrayList<>();

        try {
            employeeModels= employeeRepository.findAllByBranchIdAndDepartmentId(branchId,departmentId);

            for(int i =0;i<employeeModels.size();i++){

                depId.add(employeeModels.get(i).getDepartmentId());
            }

            HashSet<Integer> hset = new HashSet<>(depId);

            for(Integer strNumber : hset){
                EmployeeBriefDto employeeBriefDto  = new EmployeeBriefDto();
                employeeBriefDto.setSubDepartmentName(bankDepartmentRepository.findById(strNumber).get().getDepartmentName());
                List<EmployeeBriefDetailsDto> employeeBriefDetailsDtos = new ArrayList<>();

                for(int t = 0 ;t<employeeModels.size();t++){
                    if(employeeModels.get(t).getDepartmentId()==strNumber) {

                        EmployeeBriefDetailsDto employeeBriefDetailsDto = new EmployeeBriefDetailsDto();
                        employeeBriefDetailsDto.setInternalNumber(employeeModels.get(t).getInternalNumber());
                        employeeBriefDetailsDto.setName(employeeModels.get(t).getName());
                        employeeBriefDetailsDto.setPosition(employeeModels.get(t).getPosition());
                        employeeBriefDetailsDto.setSurname(employeeModels.get(t).getSurname());

                        employeeBriefDetailsDtos.add(employeeBriefDetailsDto);
                    }

                }

                employeeBriefDto.setEmployeeBriefDetailsDtos(employeeBriefDetailsDtos);

            }


        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return employeeBriefDtos;
    }


    @Override
    public EmployeeDto getEmployeeDetails(int employeeId) throws Exception {
        EmployeeModel employeeModel=null;
        EmployeeDto employeeDto = new EmployeeDto();
        try {
            employeeModel= employeeRepository.findById(employeeId);
            employeeDto.setBirhtDate(employeeModel.getBirhtDate());
            employeeDto.setBranchName(bankBranchRepository.findById(employeeModel.getBranchId()).get().getBranchName());
            employeeDto.setDepartmentName(bankDepartmentRepository.findById(employeeModel.getDepartmentId()).get().getDepartmentName() != null ? bankDepartmentRepository.findById(employeeModel.getDepartmentId()).get().getDepartmentName()
                    :null);
            employeeDto.setEmail(employeeModel.getEmail());
            employeeDto.setInternalNumber(employeeModel.getInternalNumber());
            employeeDto.setName(employeeModel.getName());
            employeeDto.setPhoneNumber(employeeModel.getPhoneNumber());
            employeeDto.setPhotoBase64(employeeModel.getPhotoBase64());
            employeeDto.setPosition(employeeModel.getPosition());
            employeeDto.setStartJobDate(employeeModel.getStartJobDate());
            employeeDto.setSubDepartmentName(subDepartmentRepository.findById(employeeModel.getSubDepartment()).get().getName());
            employeeDto.setSurname(employeeModel.getSurname());







        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return employeeDto;
    }


}

