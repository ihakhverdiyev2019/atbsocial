package atb.social.network.service.EmployeeService;

import atb.social.network.dto.*;
import atb.social.network.model.EmployeeEditHistory;
import atb.social.network.model.EmployeeModel;
import atb.social.network.repository.*;
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

    @Autowired
    private EmployeeEditHistoryRepository employeeEditHistoryRepository;

    @Autowired
    private PositionRepository positionRepository;



    @Override
    public EmployeeGetDetailsBankDTO getEmployeeBrief(int branchId, int departmentId) throws Exception {
        List<EmployeeModel> employeeModels=null;
        List<Integer> depId = new ArrayList<>();

        EmployeeGetDetailsBankDTO employeeGetDetailsBankDTO = new EmployeeGetDetailsBankDTO();

        List<EmployeeBriefDto> employeeBriefDtos = new ArrayList<>();
        List<EmployeeBriefDetailsDto> main = new ArrayList<>();

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
                        if(positionRepository.findById(employeeModels.get(t).getPosition()).get().getImportance()==0){
                            EmployeeBriefDetailsDto employeeBriefDetailsDto1 = new EmployeeBriefDetailsDto();
                            employeeBriefDetailsDto1.setPhoto(employeeModels.get(t).getPhotoBase64());
                            employeeBriefDetailsDto1.setSurname(employeeModels.get(t).getSurname());
                            employeeBriefDetailsDto1.setPosition(positionRepository.findById(employeeModels.get(t).getPosition()).get().getPositionName());
                            employeeBriefDetailsDto1.setId(employeeModels.get(t).getId());
                            employeeBriefDetailsDto1.setName(employeeModels.get(t).getName());
                            employeeBriefDetailsDto1.setInternalNumber(employeeModels.get(t).getInternalNumber());
                            main.add(employeeBriefDetailsDto1);


                        }else {

                            EmployeeBriefDetailsDto employeeBriefDetailsDto = new EmployeeBriefDetailsDto();
                            employeeBriefDetailsDto.setId(employeeModels.get(t).getId());
                            employeeBriefDetailsDto.setInternalNumber(employeeModels.get(t).getInternalNumber());
                            employeeBriefDetailsDto.setName(employeeModels.get(t).getName());
                            employeeBriefDetailsDto.setPosition(positionRepository.findById(employeeModels.get(t).getPosition()).get().getPositionName());
                            employeeBriefDetailsDto.setPhoto(employeeModels.get(t).getPhotoBase64());
                            employeeBriefDetailsDto.setSurname(employeeModels.get(t).getSurname());

                            employeeBriefDetailsDtos.add(employeeBriefDetailsDto);
                        }
                    }

                }

                employeeBriefDto.setEmployeeBriefDetailsDtos(employeeBriefDetailsDtos);
                employeeBriefDtos.add(employeeBriefDto);

            }
            employeeGetDetailsBankDTO.setEmployeeBriefDtos(employeeBriefDtos);
            employeeGetDetailsBankDTO.setMain(main);



        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return employeeGetDetailsBankDTO;
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
            System.out.println("Employee Photo: " +employeeModel.getPhotoBase64());
            employeeDto.setPhoto(employeeModel.getPhotoBase64());
            employeeDto.setPosition(positionRepository.findById(employeeModel.getPosition()).get().getPositionName());
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


            String pattern = "yyyy-MM-dd";
            String patternShow = "dd.MM.yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleDateFormatShow = new SimpleDateFormat(patternShow);

            String date = simpleDateFormat.format(new Date());
            String str = date;
            String result = str.substring(5);
            result = result.replaceAll("-", "");
            System.out.println("Date Result: " + result);
            employeesBirthDayList.setDate(simpleDateFormatShow.format(new Date()));


            List<EmployeeModel> employeeModels = employeeRepository.findAllByFilterBirth(result);
            System.out.println("Birth Size: " + employeeModels.size());
            System.out.println("1");
            if(employeeModels.size()>0) {
                System.out.println("2");

                for (int i = 0; i < employeeModels.size(); i++) {
                    System.out.println("3");

                    EmployeeBirhtDto employeeBirhtDto = new EmployeeBirhtDto();
                    System.out.println("4");

                    employeeBirhtDto.setId(employeeModels.get(i).getId());
                    System.out.println("5");

                    employeeBirhtDto.setName(employeeModels.get(i).getName());
                    System.out.println("6");

                    employeeBirhtDto.setSurname(employeeModels.get(i).getSurname());
                    System.out.println("7");

                    employeeBirhtDto.setInternalNum(employeeModels.get(i).getInternalNumber());
                    System.out.println("8");
                    employeeBirhtDto.setPhoto(employeeModels.get(i).getPhotoBase64());
                    System.out.println("Birth Photo: " + employeeModels.get(i).getPhotoBase64());


                    employeeBirhtDtos.add(employeeBirhtDto);
                    System.out.println("9");

                }
            }
            employeesBirthDayList.setEmployeeBirhtDtoList(employeeBirhtDtos);
            System.out.println("Result size: " + employeeBirhtDtos.size() );



        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }




        return employeesBirthDayList;
    }

    @Override
    public void save(EmployeeModel employeeModel) throws Exception {
        try {
            employeeRepository.save(employeeModel);
            if(employeeModel.getId()>=400){
                EmployeeEditHistory employeeEditHistory  = new EmployeeEditHistory();
                employeeEditHistory.setStatus("Yeni əməkdaş");
                employeeEditHistory.setfPosition(employeeModel.getPosition());
                employeeEditHistory.setEmployeeId(employeeModel.getId());
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                String date = simpleDateFormat.format(new Date());

                String result = date.substring(5);
                result = result.replaceAll("-", "");
                employeeEditHistory.setDateWithoutYear(result);
                employeeEditHistory.setDate(date);
                employeeEditHistoryRepository.save(employeeEditHistory);
            }


        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    public void edit(EmployeeSaveDto employeeDto, int id, int checkBox) throws Exception {
        try{
            EmployeeModel employeeModel = employeeRepository.findById(id);
            if(checkBox==1) {
                EmployeeEditHistory employeeEditHistory = new EmployeeEditHistory();
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                String date = simpleDateFormat.format(new Date());

                String result = date.substring(5);
                result = result.replaceAll("-", "");
                employeeEditHistory.setDate(date);
                employeeEditHistory.setDateWithoutYear(result);
                employeeEditHistory.setEmployeeId(id);
                employeeEditHistory.setStatus("Vəzifə dəyişikliyi");
                employeeEditHistory.setfPosition(employeeModel.getPosition());
                employeeEditHistory.setlPosition(employeeDto.getPosition());
                employeeEditHistoryRepository.save(employeeEditHistory);


            }

            employeeModel.setDepartmentId(employeeDto.getDepartId());
            employeeModel.setSubDepartment(employeeDto.getSubDepartId());
            employeeModel.setSurname(employeeDto.getSurname());
            employeeModel.setStartJobDate(employeeDto.getStartDate());
            employeeModel.setPosition(employeeDto.getPosition());
            employeeModel.setPhotoBase64(employeeDto.getPhoto());
            employeeModel.setPhoneNumber(employeeDto.getNumber());
            employeeModel.setName(employeeDto.getName());
            employeeModel.setInternalNumber(employeeDto.getInternalNum());
            employeeModel.setEmail(employeeDto.getEmail());
            employeeModel.setBirhtDate(employeeDto.getBirthDay());
            String str = employeeDto.getBirthDay();
            String result = str.substring(5);
            result = result.replaceAll("-", "");
            employeeModel.setFilterBirth(result);
            employeeModel.setBranchId(employeeDto.getBranchId());
            employeeRepository.save(employeeModel);





        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }

    }


}

