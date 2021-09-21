package atb.social.network.controller;


import atb.social.network.dto.PositionDto;
import atb.social.network.model.PositionModel;
import atb.social.network.service.PositionService.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionController {


    @Autowired
    private PositionService positionService;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/positions/{bid}/{did}/{sdid}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllBranches(@PathVariable("bid") String bid, @PathVariable("did") String did,@PathVariable("sdid") String sdid) throws Exception {
        List<PositionModel> positionModels;
        try{

            positionModels  = positionService.getAllPosition(Integer.parseInt(bid),Integer.parseInt(did),Integer.parseInt(sdid));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(positionModels, HttpStatus.OK);



    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/positions/save" , method = RequestMethod.POST)
    public ResponseEntity<Object> getAllBranches(@RequestBody PositionDto positionDto) throws Exception {
        try{

        PositionModel positionModel = new PositionModel();

        positionModel.setBranchId(positionDto.getBranchId());
        positionModel.setDepartId(positionDto.getDepartId());
        positionModel.setPositionName(positionDto.getPositionName());
        positionModel.setSubDepartId(positionDto.getSubDepartId());
        positionService.savePosition(positionModel);


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }
}
