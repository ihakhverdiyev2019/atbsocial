package atb.social.network.service.HistoryService;

import atb.social.network.dto.EmployeePositionChangesDto;
import atb.social.network.model.EmployeeEditHistory;
import atb.social.network.model.EmployeeModel;
import atb.social.network.repository.EmployeeEditHistoryRepository;
import atb.social.network.repository.EmployeeRepository;
import atb.social.network.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private EmployeeEditHistoryRepository employeeEditHistoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;



    @Override
    public List<EmployeePositionChangesDto> getDetails() throws Exception {
        List<EmployeePositionChangesDto> employeePositionChangesDtos=new ArrayList<>();
        try{


            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date = simpleDateFormat.format(new Date());

            String result = date.substring(5);
            result = result.replaceAll("-", "");
            List<EmployeeEditHistory> employeeEditHistories = employeeEditHistoryRepository.findAllByDateWithoutYear(result);

            for(int i=0;i<employeeEditHistories.size();i++){
                EmployeePositionChangesDto employeePositionChangesDto = new EmployeePositionChangesDto();
                EmployeeModel employeeModel = employeeRepository.findById(employeeEditHistories.get(i).getEmployeeId());

                employeePositionChangesDto.setName(employeeModel.getName());
                employeePositionChangesDto.setSurname(employeeModel.getSurname());
                employeePositionChangesDto.setfPosition(positionRepository.findById(employeeEditHistories.get(i).getfPosition()).get().getPositionName());
                employeePositionChangesDto.setInternalNumber(employeeModel.getInternalNumber());
                employeePositionChangesDto.setlPosition(positionRepository.findById(employeeEditHistories.get(i).getlPosition()).get().getPositionName());
                employeePositionChangesDto.setStatus(employeeEditHistories.get(i).getStatus());

                employeePositionChangesDtos.add(employeePositionChangesDto);



            }





        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return employeePositionChangesDtos;
    }
}
