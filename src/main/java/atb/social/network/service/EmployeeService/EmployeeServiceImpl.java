package atb.social.network.service.EmployeeService;

import atb.social.network.dto.*;
import atb.social.network.model.EmployeeModel;
import atb.social.network.repository.BankBranchRepository;
import atb.social.network.repository.BankDepartmentRepository;
import atb.social.network.repository.EmployeeRepository;
import atb.social.network.repository.SubDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

                depId.add(employeeModels.get(i).getSubDepartment());
            }

            HashSet<Integer> hset = new HashSet<>(depId);
            System.out.println(hset.size());
            System.out.println(hset);

            for(Integer strNumber : hset){
                EmployeeBriefDto employeeBriefDto  = new EmployeeBriefDto();
                employeeBriefDto.setSubDepartmentName(subDepartmentRepository.findById(strNumber).get().getName());
                List<EmployeeBriefDetailsDto> employeeBriefDetailsDtos = new ArrayList<>();

                for(int t = 0 ;t<employeeModels.size();t++){
                    if(employeeModels.get(t).getSubDepartment()==strNumber) {

                        EmployeeBriefDetailsDto employeeBriefDetailsDto = new EmployeeBriefDetailsDto();
                        employeeBriefDetailsDto.setId(employeeModels.get(t).getId());
                        employeeBriefDetailsDto.setInternalNumber(employeeModels.get(t).getInternalNumber());
                        employeeBriefDetailsDto.setName(employeeModels.get(t).getName());
                        employeeBriefDetailsDto.setPosition(employeeModels.get(t).getPosition());
                        employeeBriefDetailsDto.setSurname(employeeModels.get(t).getSurname());

                        employeeBriefDetailsDtos.add(employeeBriefDetailsDto);
                    }

                }

                employeeBriefDto.setEmployeeBriefDetailsDtos(employeeBriefDetailsDtos);
                employeeBriefDtos.add(employeeBriefDto);

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

    @Override
    public   EmployeesBirthDayList  getEmployeeBirth() throws Exception {


        EmployeesBirthDayList employeesBirthDayList = new EmployeesBirthDayList();
        List<EmployeeBirhtDto> employeeBirhtDtos = new ArrayList<>();


        try {


            String pattern = " dd/MM/";
            String patternShow = " dd.MM.yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleDateFormatShow = new SimpleDateFormat(patternShow);

            String date = simpleDateFormat.format(new Date());
            System.out.println(date);
            employeesBirthDayList.setDate(simpleDateFormatShow.format(new Date()));


            List<EmployeeModel> employeeModels = employeeRepository.findAllByBirthDayWithoutYear(date);
            if(employeeModels.size()>0) {
                for (int i = 0; i < employeeModels.size(); i++) {
                    EmployeeBirhtDto employeeBirhtDto = new EmployeeBirhtDto();
                    employeeBirhtDto.setId(employeeModels.get(i).getId());
                    employeeBirhtDto.setName(employeeModels.get(i).getName());
                    employeeBirhtDto.setSurname(employeeModels.get(i).getSurname());
                    employeeBirhtDto.setBranch(bankBranchRepository.findById(employeeModels.get(i).getBranchId()).get().getBranchName());

                    employeeBirhtDtos.add(employeeBirhtDto);
                }
            }
            employeesBirthDayList.setEmployeeBirhtDtoList(employeeBirhtDtos);



        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }




        return employeesBirthDayList;
    }


}

