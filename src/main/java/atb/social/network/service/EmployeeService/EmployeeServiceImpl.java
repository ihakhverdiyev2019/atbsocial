package atb.social.network.service.EmployeeService;

import atb.social.network.config.FileConfig;
import atb.social.network.dto.*;
import atb.social.network.model.BankBranchModel;
import atb.social.network.model.CongratModel;
import atb.social.network.model.EmployeeEditHistory;
import atb.social.network.model.EmployeeModel;
import atb.social.network.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    @Autowired
    private CongratRepository congratRepository;



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
                            employeeBriefDetailsDto1.setPhoto(employeeModels.get(t).getPhotosImagePath());
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
                            employeeBriefDetailsDto.setPhoto(employeeModels.get(t).getPhotosImagePath());
                            employeeBriefDetailsDto.setSurname(employeeModels.get(t).getSurname());

                            employeeBriefDetailsDtos.add(employeeBriefDetailsDto);
                        }
                    }

                }

                Collections.sort(employeeBriefDetailsDtos,
                        Comparator.comparingDouble(
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
    public EmployeeDto getEmployeeDetails(int employeeId, String userIp) throws Exception {
        EmployeeModel employeeModel=null;
        EmployeeDto employeeDto = new EmployeeDto();



        try {

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date = simpleDateFormat.format(new Date());
            String str = date;
            String result = str.substring(5);
            result = result.replaceAll("-", "");


            Date date1 = simpleDateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DATE, -1);
            Date yesterday = calendar.getTime();

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            calendar1.add(Calendar.DATE, -2);
            Date twoDaysBefore = calendar1.getTime();

            String yesterdayString = simpleDateFormat.format(yesterday);
            String twoDaysBeforeString = simpleDateFormat.format(twoDaysBefore);


            String yesterdayStringResult = yesterdayString.substring(5);
            yesterdayStringResult = yesterdayStringResult.replaceAll("-", "");


            String twoDaysBeforeStringResult = twoDaysBeforeString.substring(5);
            twoDaysBeforeStringResult = twoDaysBeforeStringResult.replaceAll("-", "");

            employeeModel= employeeRepository.findById(employeeId);
            CongratModel congratModel = congratRepository.findByUserIpAndToId(userIp,employeeId);

            employeeDto.setEmpId(employeeId);
            if(congratModel==null){
                employeeDto.setCanCongrat(1);
            }else {
                employeeDto.setCanCongrat(0);
            }
            LocalDate today = LocalDate.now();


            DayOfWeek day = DayOfWeek.of(today.get(ChronoField.DAY_OF_WEEK));
            System.out.println(day.toString());
            System.out.println(employeeModel.getFilterBirth());
            System.out.println(twoDaysBeforeStringResult);
            System.out.println(yesterdayStringResult);
            if(day == DayOfWeek.MONDAY){

                if (employeeModel.getFilterBirth().equals(yesterdayStringResult) || employeeModel.getFilterBirth().equals(twoDaysBeforeStringResult)) {
                    employeeDto.setIsBirth(1);

                } else {
                    employeeDto.setIsBirth(0);
                }

            }else {

                if (employeeModel.getFilterBirth().equals(result)) {
                    employeeDto.setIsBirth(1);

                } else {
                    employeeDto.setIsBirth(0);
                }

            }
            SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
            employeeDto.setBirhtDate(employeeModel.getBirhtDate());

            if(!employeeModel.getStartJobDate().equals("")) {
                employeeDto.setStartJobDate(myFormat.format(fromUser.parse(employeeModel.getStartJobDate())));
            }
            employeeDto.setBranchName(bankBranchRepository.findById(employeeModel.getBranchId()).get().getBranchName());
            if(employeeModel.getDepartmentId()!=0) {
                employeeDto.setDepartmentName(bankDepartmentRepository.findById(employeeModel.getDepartmentId()).get().getDepartmentName() != null ? bankDepartmentRepository.findById(employeeModel.getDepartmentId()).get().getDepartmentName()
                        : null);
            }
            employeeDto.setEmail(employeeModel.getEmail());
            employeeDto.setInternalNumber(employeeModel.getInternalNumber());
            employeeDto.setName(employeeModel.getName());
            employeeDto.setPhoneNumber(employeeModel.getPhoneNumber());
            employeeDto.setPhoto(employeeModel.getPhotosImagePath());
            System.out.println(employeeModel.getPhotosImagePath());
            employeeDto.setPosition(positionRepository.findById(employeeModel.getPosition()).get().getPositionName());
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

            if(day == DayOfWeek.MONDAY){

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


                String yesterdayStringResult = yesterdayString.substring(5);
                yesterdayStringResult = yesterdayStringResult.replaceAll("-", "");


                String twoDaysBeforeStringResult = twoDaysBeforeString.substring(5);
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




        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }




        return employeesBirthDayList;
    }

    @Override
    public void save(EmployeeSaveDto employee,MultipartFile multipartFile) throws Exception {
        try {
            EmployeeModel employeeModel = new EmployeeModel();

            if(!employee.getBirthDay().equals("")) {

                String str = employee.getBirthDay();
                String result = str.substring(5);
                result = result.replaceAll("-", "");

                employeeModel.setBirhtDate(employee.getBirthDay());
                employeeModel.setFilterBirth(result);

            }

            employeeModel.setBirhtDate(employee.getBirthDay());
            employeeModel.setStartJobDate(employee.getStartDate());


            employeeModel.setBranchId(employee.getBranchId());
            employeeModel.setEmail(employee.getEmail());
            employeeModel.setInternalNumber(employee.getInternalNum());
            employeeModel.setName(employee.getName());
            employeeModel.setPhoneNumber(employee.getNumber());
            employeeModel.setPosition(employee.getPosition());
            employeeModel.setSurname(employee.getSurname());
            employeeModel.setSubDepartment(employee.getSubDepartId());
            employeeModel.setDepartmentId(employee.getDepartId());

            if(employee.getPosition()!=0) {
                employeeModel.setPositionName(positionRepository.findById(employeeModel.getPosition()).get().getPositionName());
            }
            System.out.println(employeeModel.getId());


            employeeRepository.save(employeeModel);
            System.out.println(employeeModel.getId());


            if(multipartFile==null) {
                employeeModel.setPhotoBase64(employeeModel.getPhotoBase64());


            }else{
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());


                employeeModel.setPhotoBase64(fileName);
                String uploadDir = "photos/user-photos/" + employeeModel.getId();


                FileConfig.saveFile(uploadDir, fileName, multipartFile);
            }







            if(employeeModel.getId()>=390){
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
    public void edit(EmployeeEditDto employeeDto, int id, MultipartFile multipartFile) throws Exception {
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
            employeeModel.setPositionName(positionRepository.findById(employeeDto.getPosition()).get().getPositionName());
if(multipartFile==null) {
    employeeModel.setPhotoBase64(employeeModel.getPhotoBase64());


}else{
    Path path = Paths.get("photos/user-photos/" + id);

    if (Files.exists(path)) {

        FileConfig.deleteDirectoryJava7("photos/user-photos/" + employeeModel.getId());
    }
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());


    employeeModel.setPhotoBase64(fileName);
    String uploadDir = "photos/user-photos/" + employeeModel.getId();


    FileConfig.saveFile(uploadDir, fileName, multipartFile);
}
            employeeModel.setPhoneNumber(employeeDto.getNumber());
            employeeModel.setName(employeeDto.getName());
            employeeModel.setInternalNumber(employeeDto.getInternalNum());
            employeeModel.setEmail(employeeDto.getEmail());
            employeeModel.setBirhtDate(employeeDto.getBirthDay());
            String str = employeeDto.getBirthDay();
            if(!str.equals("")) {

                String result = str.substring(5);
                result = result.replaceAll("-", "");
                employeeModel.setFilterBirth(result);

            }
            employeeModel.setBranchId(employeeDto.getBranchId());
            employeeRepository.save(employeeModel);





        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }

    }

    @Override
    public void congrat(int empId, int toId,String userIp) {

        CongratModel congratModel = new CongratModel();
        String pattern = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        EmployeeModel employeeModel = employeeRepository.findById(empId);
        CongratModel congratModel1 = congratRepository.findByEmpIdAndToId(empId,toId);
        if(employeeModel!=null && congratModel1 ==null) {

            String date = simpleDateFormat.format(new Date());
            congratModel.setUserIp(userIp);
            congratModel.setEmpId(empId);
            congratModel.setToId(toId);
            congratModel.setYear(date);
            congratRepository.save(congratModel);
        }





    }


    @Override
    public List<CongratsResponse> getCongrats(int toId) {
        List<CongratsResponse> congratsResponses = new ArrayList<>();
        String pattern = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        List<CongratModel> congratModel = congratRepository.findAllByToIdAndYear(toId,date);


        for(int i = 0 ; i<congratModel.size();i++){
            EmployeeModel employeeModel = employeeRepository.findById(congratModel.get(i).getEmpId());

            CongratsResponse congratsResponse = new CongratsResponse();
            congratsResponse.setId(congratModel.get(i).getId());
             congratsResponse.setEmpId(congratModel.get(i).getEmpId());
                congratsResponse.setName(employeeModel.getName());
                congratsResponse.setSurname(employeeModel.getSurname());
                 congratsResponse.setPosition(employeeModel.getPositionName());
                 congratsResponse.setPhoto(employeeModel.getPhotosImagePath());
                 congratsResponses.add(congratsResponse);

        }

        congratsResponses.sort(Comparator.comparingInt(CongratsResponse::getId).reversed());


            return congratsResponses;



    }












    @Override
   public Set<EmployeeModel> getAllEmployee( ) throws Exception{
        Set<EmployeeModel> employeeModels;

        try{

            employeeModels =employeeRepository.findAll();









        }catch (Exception e) {

throw new Exception(e.getMessage());
        }

return employeeModels;

    }



    @Override
    public void remove(int id) throws Exception {

            EmployeeModel employeeModel = employeeRepository.findById(id);
            EmployeeEditHistory employeeEditHistory = employeeEditHistoryRepository.findByEmployeeId(employeeModel.getId());
        Path path = Paths.get("photos/user-photos/" + id);

        if (Files.exists(path)) {
            FileConfig.deleteDirectoryJava7("photos/user-photos/" + id);

                }
            if (employeeEditHistory!=null){

                employeeEditHistoryRepository.delete(employeeEditHistory);
            }
            employeeRepository.delete(employeeModel);



    }




}

