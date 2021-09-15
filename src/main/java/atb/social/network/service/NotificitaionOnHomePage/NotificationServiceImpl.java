package atb.social.network.service.NotificitaionOnHomePage;


import atb.social.network.model.NotificationOnHomePageModel;
import atb.social.network.repository.NotificationOnHomePageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private NotificationOnHomePageRepository notificationOnHomePageRepository;





    @Override
    public List<NotificationOnHomePageModel> getNotificationByDate() throws Exception {
        List<NotificationOnHomePageModel> notificationOnHomePageModels;
        try{
            String pattern = " dd.MM.yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            System.out.println(date);
            notificationOnHomePageModels = notificationOnHomePageRepository.findAllByDate(date);


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return notificationOnHomePageModels;
    }
}
