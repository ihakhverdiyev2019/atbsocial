package atb.social.network.service.EmployeeService;

import atb.social.network.dto.*;
import atb.social.network.model.BankBranchModel;
import atb.social.network.model.EmployeeEditHistory;
import atb.social.network.model.EmployeeModel;
import atb.social.network.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;

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
        List<Integer> depId = new ArrayList<>();

        EmployeeGetDetailsBankDTO employeeGetDetailsBankDTO = new EmployeeGetDetailsBankDTO();
        BankBranchModel bankBranchModel = bankBranchRepository.findById(branchId).get();
        if(bankBranchModel.getIsMainBranch()==1){
            employeeGetDetailsBankDTO.setBranch(bankDepartmentRepository.findById(departmentId).get().getDepartmentName());
        }else {
            employeeGetDetailsBankDTO.setBranch(bankBranchModel.getBranchName());
        }

        List<EmployeeBriefDto> employeeBriefDtos = new ArrayList<>();
        List<EmployeeBriefDetailsDto> main = new ArrayList<>();

        try {

            List<EmployeeModel> employeeModels = employeeRepository.findAllByBranchIdAndDepartmentId(branchId,departmentId);

            for(int i =0;i<employeeModels.size();i++){

                depId.add(employeeModels.get(i).getSubDepartment());
            }

            HashSet<Integer> hset = new HashSet<>(depId);


            for(Integer strNumber : hset){
                EmployeeBriefDto employeeBriefDto  = new EmployeeBriefDto();
                if(strNumber!=0) {
                    employeeBriefDto.setSubDepartmentName(subDepartmentRepository.findById(strNumber).get().getName());
                }
                List<EmployeeBriefDetailsDto> employeeBriefDetailsDtos = new ArrayList<>();


                for(int t = 0 ;t<employeeModels.size();t++){
                    if(employeeModels.get(t).getSubDepartment()==strNumber) {
                        if(positionRepository.findById(employeeModels.get(t).getPosition()).get().getImportance()==0){
                            EmployeeBriefDetailsDto employeeBriefDetailsDto1 = new EmployeeBriefDetailsDto();
                            employeeBriefDetailsDto1.setPhoto(employeeModels.get(t).getPhotoBase64());
                            employeeBriefDetailsDto1.setSurname(employeeModels.get(t).getSurname());
                            employeeBriefDetailsDto1.setPosition(positionRepository.findById(employeeModels.get(t).getPosition()).get().getPositionName());
                            employeeBriefDetailsDto1.setImportance(positionRepository.findById(employeeModels.get(t).getPosition()).get().getImportance());
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
                            employeeBriefDetailsDto.setImportance(positionRepository.findById(employeeModels.get(t).getPosition()).get().getImportance());
                            employeeBriefDetailsDto.setPhoto(employeeModels.get(t).getPhotoBase64());
                            employeeBriefDetailsDto.setSurname(employeeModels.get(t).getSurname());

                            employeeBriefDetailsDtos.add(employeeBriefDetailsDto);
                        }
                    }

                }

                Collections.sort(employeeBriefDetailsDtos,
                        Comparator.comparingInt(
                                EmployeeBriefDetailsDto::getImportance));
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
            if(employeeModel.getDepartmentId()!=0) {
                employeeDto.setDepartmentName(bankDepartmentRepository.findById(employeeModel.getDepartmentId()).get().getDepartmentName() != null ? bankDepartmentRepository.findById(employeeModel.getDepartmentId()).get().getDepartmentName()
                        : null);
            }
            employeeDto.setEmail(employeeModel.getEmail());
            employeeDto.setInternalNumber(employeeModel.getInternalNumber());
            employeeDto.setName(employeeModel.getName());
            employeeDto.setPhoneNumber(employeeModel.getPhoneNumber());
            employeeDto.setPhoto(employeeModel.getPhotoBase64());
            employeeDto.setPosition(positionRepository.findById(employeeModel.getPosition()).get().getPositionName());
            employeeDto.setStartJobDate(employeeModel.getStartJobDate());
            if( positionRepository.findById(employeeModel.getPosition()).get().getImportance()!=0) {

                if(employeeModel.getSubDepartment()!=0) {
                    employeeDto.setSubDepartmentName(subDepartmentRepository.findById(employeeModel.getSubDepartment()).get().getName() != null ? subDepartmentRepository.findById(employeeModel.getSubDepartment()).get().getName()
                            : null);
                }
            }

            employeeDto.setSurname(employeeModel.getSurname());







        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return employeeDto;
    }

    @Override
    public   List<EmployeesBirthDayList>  getEmployeeBirth() throws Exception {


        List<EmployeesBirthDayList> employeesBirthDayList = new ArrayList<>();
        List<EmployeeBirhtDto> employeeBirhtDtos = new ArrayList<>();
        List<EmployeeBirhtDto> employeeBirhtDtosYesterday = new ArrayList<>();
        List<EmployeeBirhtDto> employeeBirhtDtosTwoDays = new ArrayList<>();




        try {


            String pattern = "yyyy-MM-dd";
            String patternShow = "dd.MM.yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleDateFormatShow = new SimpleDateFormat(patternShow);

            String date = simpleDateFormat.format(new Date());
            String str = date;
            String result = str.substring(5);
            result = result.replaceAll("-", "");
            LocalDate today = LocalDate.now();

            DayOfWeek day = DayOfWeek.of(today.get(ChronoField.DAY_OF_WEEK));


            if(day == DayOfWeek.FRIDAY){

                Date date1 = simpleDateFormat.parse(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date1);
                calendar.add(Calendar.DATE, -1);
                Date yesterday = calendar.getTime();

                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date1);
                calendar1.add(Calendar.DATE, -2);
                Date twoDaysBefore = calendar1.getTime();


                String yesterdayShow = simpleDateFormatShow.format(yesterday);
                String twoDaysBeforeShow = simpleDateFormatShow.format(twoDaysBefore);

                String yesterdayString = simpleDateFormat.format(yesterday);
                String twoDaysBeforeString = simpleDateFormat.format(twoDaysBefore);


                String yesterdayStringResult = str.substring(5);
                yesterdayStringResult = yesterdayStringResult.replaceAll("-", "");


                String twoDaysBeforeStringResult = str.substring(5);
                twoDaysBeforeStringResult = twoDaysBeforeStringResult.replaceAll("-", "");

                List<EmployeeModel> twoDaysBeforeStringEmpResult = employeeRepository.findAllByFilterBirth(twoDaysBeforeStringResult);

                List<EmployeeModel> yesterdayStringEmpResult = employeeRepository.findAllByFilterBirth(yesterdayStringResult);


            if(yesterdayStringEmpResult.size()>0) {
                EmployeesBirthDayList employeesBirthDayList3 = new EmployeesBirthDayList();
                employeesBirthDayList3.setDate(yesterdayShow);


                for (int y = 0; y < yesterdayStringEmpResult.size(); y++) {


                    EmployeeBirhtDto employeeBirhtDtoY = new EmployeeBirhtDto();

                    employeeBirhtDtoY.setId(yesterdayStringEmpResult.get(y).getId());

                    employeeBirhtDtoY.setName(yesterdayStringEmpResult.get(y).getName());

                    employeeBirhtDtoY.setSurname(yesterdayStringEmpResult.get(y).getSurname());

                    employeeBirhtDtoY.setInternalNum(yesterdayStringEmpResult.get(y).getInternalNumber());
                    employeeBirhtDtoY.setPhoto(yesterdayStringEmpResult.get(y).getPhotoBase64());


                    employeeBirhtDtosTwoDays.add(employeeBirhtDtoY);



                }

                employeesBirthDayList3.setEmployeeBirhtDtoList(employeeBirhtDtosTwoDays);
                employeesBirthDayList.add(employeesBirthDayList3);


            }


                if(twoDaysBeforeStringEmpResult.size()>0) {
                    EmployeesBirthDayList employeesBirthDayList1 = new EmployeesBirthDayList();
                    employeesBirthDayList1.setDate(twoDaysBeforeShow);

                    for (int u = 0; u < twoDaysBeforeStringEmpResult.size(); u++) {


                        EmployeeBirhtDto employeeBirhtDtoT = new EmployeeBirhtDto();

                        employeeBirhtDtoT.setId(twoDaysBeforeStringEmpResult.get(u).getId());

                        employeeBirhtDtoT.setName(twoDaysBeforeStringEmpResult.get(u).getName());

                        employeeBirhtDtoT.setSurname(twoDaysBeforeStringEmpResult.get(u).getSurname());

                        employeeBirhtDtoT.setInternalNum(twoDaysBeforeStringEmpResult.get(u).getInternalNumber());
                        employeeBirhtDtoT.setPhoto(twoDaysBeforeStringEmpResult.get(u).getPhotoBase64());


                        employeeBirhtDtosYesterday.add(employeeBirhtDtoT);



                    }

                    employeesBirthDayList1.setEmployeeBirhtDtoList(employeeBirhtDtosYesterday);
                    employeesBirthDayList.add(employeesBirthDayList1);


                }






            }


            List<EmployeeModel> employeeModels = employeeRepository.findAllByFilterBirth(result);
            if(employeeModels.size()>0) {
                EmployeesBirthDayList employeesBirthDayList2 = new EmployeesBirthDayList();
                employeesBirthDayList2.setDate(simpleDateFormatShow.format(new Date()));




                for (int i = 0; i < employeeModels.size(); i++) {




                    EmployeeBirhtDto employeeBirhtDto = new EmployeeBirhtDto();

                    employeeBirhtDto.setId(employeeModels.get(i).getId());

                    employeeBirhtDto.setName(employeeModels.get(i).getName());

                    employeeBirhtDto.setSurname(employeeModels.get(i).getSurname());

                    employeeBirhtDto.setInternalNum(employeeModels.get(i).getInternalNumber());
                    employeeBirhtDto.setPhoto(employeeModels.get(i).getPhotoBase64());




                    employeeBirhtDtos.add(employeeBirhtDto);



                }


                employeesBirthDayList2.setEmployeeBirhtDtoList(employeeBirhtDtos);
                employeesBirthDayList.add(employeesBirthDayList2);

            }



        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }




        return employeesBirthDayList;
    }

    @Override
    public void save(EmployeeModel employeeModel) throws Exception {
        try {

            if(employeeModel.getPosition()!=0) {
                employeeModel.setPositionName(positionRepository.findById(employeeModel.getPosition()).get().getPositionName());
            }

            employeeRepository.save(employeeModel);
            if(employeeModel.getId()>=1){
                EmployeeEditHistory employeeEditHistory  = new EmployeeEditHistory();
                employeeEditHistory.setStatus("Yeni əməkdaş");
                employeeEditHistory.setfBranch(employeeModel.getBranchId());
                employeeEditHistory.setfDepartment(employeeModel.getDepartmentId());
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
    public void edit(EmployeeEditDto employeeDto, int id) throws Exception {
        try{
            EmployeeModel employeeModel = employeeRepository.findById(id);

            if(employeeDto.getCheckBox()==1) {
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
                employeeEditHistory.setfBranch(employeeModel.getBranchId());
                employeeEditHistory.setlBranch(employeeDto.getBranchId());
                employeeEditHistory.setfDepartment(employeeModel.getDepartmentId());
                employeeEditHistory.setlDepartment(employeeDto.getDepartId());
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



    @Override
   public List<EmployeeModel> getAllEmployee( ) throws Exception{
        List<EmployeeModel> employeeModels;

        try{
            employeeModels =employeeRepository.findAll();








        }catch (Exception e) {

throw new Exception(e.getMessage());
        }

return employeeModels;

    }



    @Override
    public void remove(int id) throws Exception {
        try {
            EmployeeModel employeeModel = employeeRepository.findById(id);
            employeeRepository.delete(employeeModel);


        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }




}

