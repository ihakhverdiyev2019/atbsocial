package atb.social.network.service.News;

import atb.social.network.config.FileConfig;
import atb.social.network.dto.NewsDTO;
import atb.social.network.dto.NewsViewDTO;
import atb.social.network.model.NewsCounterModel;
import atb.social.network.model.NewsLikeModel;
import atb.social.network.model.NewsModel;
import atb.social.network.repository.NewsCounterRepository;
import atb.social.network.repository.NewsLikeRepository;
import atb.social.network.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsCounterRepository newsCounterRepository;

    @Autowired
    private NewsLikeRepository newsLikeRepository;


    @Override
    public List<NewsViewDTO> getAllNews(int count,int page,String userIP) throws Exception {
        List<NewsViewDTO> newsModelsFilter=new ArrayList<>();

        try{
            List<NewsModel> newsModels = newsRepository.findAll();
            int lastnum;
            if(page*count>newsModels.size()){
                lastnum=newsModels.size();
            }else {
                lastnum = page*count;
            }

            for(int i = (page-1)*count ; i<lastnum ; i++){
                NewsViewDTO newsViewDTO = new NewsViewDTO();
                NewsModel newsModel = newsModels.get(i);
                List<NewsCounterModel> newsCounterModels = newsCounterRepository.findAllByNewsId(newsModel.getId());
                List<NewsLikeModel> like = newsLikeRepository.findAllByNewsIdAndStatus(newsModel.getId(),1);
                List<NewsLikeModel> dislike =newsLikeRepository.findAllByNewsIdAndStatus(newsModel.getId(),-1);
                NewsLikeModel newsLikeModel = newsLikeRepository.findByNewsIdAndUserip(newsModel.getId(),userIP);
                newsViewDTO.setId(newsModel.getId());
                newsViewDTO.setCounter(newsCounterModels.size());
                newsViewDTO.setDate(newsModel.getDate());
                newsViewDTO.setDislike(dislike.size());
                newsViewDTO.setLike(like.size());
                newsViewDTO.setMainPhoto(newsModel.getMainPhoto());
                newsViewDTO.setPhoto1(newsModel.getPhoto1());
                newsViewDTO.setPhoto2(newsModel.getPhoto2());
                newsViewDTO.setPhoto3(newsModel.getPhoto3());
                newsViewDTO.setPhoto4(newsModel.getPhoto4());
                newsViewDTO.setText(newsModel.getText());
                newsViewDTO.setStatus((newsLikeModel==null) ? 0 : newsLikeModel.getStatus());
                newsViewDTO.setTitle(newsModel.getTitle());


                newsModelsFilter.add(newsViewDTO);

            }



        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return newsModelsFilter;
    }

    @Override
    public NewsModel getNewsById(int id,String userIp) throws Exception {
        NewsModel newsModels;
        try{

            NewsCounterModel newsCounterModel = newsCounterRepository.findByNewsIdAndIp(id,userIp);

            if(newsCounterModel==null){
                NewsCounterModel newsCounterModel1 = new NewsCounterModel();
                newsCounterModel1.setIp(userIp);
                newsCounterModel1.setNewsId(id);
                newsCounterRepository.save(newsCounterModel1);

            }


            newsModels = newsRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return newsModels;
    }


    @Override
    public void saveNews(NewsDTO newsDTO, MultipartFile mainPhoto,MultipartFile m1,MultipartFile m2,MultipartFile m3,MultipartFile m4) throws Exception{
        try {
            NewsModel newsModel =  new NewsModel();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss");




            String date = simpleDateFormat.format(new Date());
            System.out.println(date);

            newsModel.setDate(date);
            newsModel.setText(newsDTO.getText());
            newsModel.setTitle(newsDTO.getTitle());

            newsRepository.save(newsModel);

            if(mainPhoto!=null) {

                String mainPhoto1 = StringUtils.cleanPath(mainPhoto.getOriginalFilename());


                newsModel.setMainPhoto(mainPhoto1);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, mainPhoto1, mainPhoto);
            }else if(m1!=null){
                String m11 = StringUtils.cleanPath(m1.getOriginalFilename());


                newsModel.setMainPhoto(m11);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m11, m1);
            }else if(m2!=null){
                String m22 = StringUtils.cleanPath(m2.getOriginalFilename());


                newsModel.setMainPhoto(m22);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m22, m2);
            }
            else if(m3!=null){
                String m33 = StringUtils.cleanPath(m1.getOriginalFilename());


                newsModel.setMainPhoto(m33);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m33, m3);
            }
            else if(m4!=null){
                String m44 = StringUtils.cleanPath(m4.getOriginalFilename());


                newsModel.setMainPhoto(m44);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m44, m4);
            }

            newsRepository.save(newsModel);




        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editNews( int id,NewsDTO newsDTO, MultipartFile mainPhoto,MultipartFile m1,MultipartFile m2,MultipartFile m3,MultipartFile m4) throws Exception{
        try {
            NewsModel newsModel = newsRepository.findById(id);

            newsModel.setText(newsDTO.getText());
            newsModel.setTitle(newsDTO.getTitle());


            if(mainPhoto!=null) {
                FileConfig.deleteFile("photos/news/" + newsModel.getId() +"/" + newsModel.getMainPhoto());

                String mainPhoto1 = StringUtils.cleanPath(mainPhoto.getOriginalFilename());


                newsModel.setMainPhoto(mainPhoto1);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, mainPhoto1, mainPhoto);
            }else if(m1!=null){
                FileConfig.deleteFile("photos/news/" + newsModel.getId() +"/" + newsModel.getPhoto1());

                String m11 = StringUtils.cleanPath(m1.getOriginalFilename());


                newsModel.setMainPhoto(m11);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m11, m1);
            }else if(m2!=null){
                FileConfig.deleteFile("photos/news/" + newsModel.getId() +"/" + newsModel.getPhoto2());

                String m22 = StringUtils.cleanPath(m2.getOriginalFilename());


                newsModel.setMainPhoto(m22);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m22, m2);
            }
            else if(m3!=null){
                FileConfig.deleteFile("photos/news/" + newsModel.getId() +"/" + newsModel.getPhoto3());

                String m33 = StringUtils.cleanPath(m1.getOriginalFilename());


                newsModel.setMainPhoto(m33);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m33, m3);
            }
            else if(m4!=null){
                FileConfig.deleteFile("photos/news/" + newsModel.getId() +"/" + newsModel.getPhoto4());

                String m44 = StringUtils.cleanPath(m4.getOriginalFilename());


                newsModel.setMainPhoto(m44);
                String uploadDir = "photos/news/" + newsModel.getId();


                FileConfig.saveFile(uploadDir, m44, m4);
            }

            newsRepository.save(newsModel);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeNews(int id) throws Exception{
        try {
            NewsModel newsModel = newsRepository.findById(id);

            FileConfig.deleteDirectoryJava7("photos/news/" + newsModel.getId());


            newsRepository.delete(newsModel);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public void likeNews(int id, String userIp) throws Exception{
        try {
           NewsLikeModel newsLikeModel = newsLikeRepository.findByNewsIdAndUserip(id, userIp);
           NewsLikeModel newsLikeModelDislike = newsLikeRepository.findByNewsIdAndUseripAndStatus(id, userIp,-1);

            if(newsLikeModel==null){

               NewsLikeModel newsLikeModel1 = new NewsLikeModel();
               newsLikeModel1.setNewsId(id);
               newsLikeModel1.setUserip(userIp);
               newsLikeModel1.setStatus(1);
               newsLikeRepository.save(newsLikeModel1);

           }else if(newsLikeModelDislike != null) {
                newsLikeRepository.delete(newsLikeModelDislike);
                NewsLikeModel newsLikeModel2 = new NewsLikeModel();
                newsLikeModel2.setNewsId(id);
                newsLikeModel2.setUserip(userIp);
                newsLikeModel2.setStatus(1);
                newsLikeRepository.save(newsLikeModel2);




            }else {
                newsLikeRepository.delete(newsLikeModel);

            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public void dislikeNews(int id,String userIp) throws Exception {
        try {
            NewsLikeModel newsLikeModel = newsLikeRepository.findByNewsIdAndUserip(id, userIp);
            NewsLikeModel newsLikeModelLike = newsLikeRepository.findByNewsIdAndUseripAndStatus(id, userIp,1);

            if (newsLikeModel == null) {

                NewsLikeModel newsLikeModel1 = new NewsLikeModel();
                newsLikeModel1.setNewsId(id);
                newsLikeModel1.setUserip(userIp);
                newsLikeModel1.setStatus(-1);

            }
            else if(newsLikeModelLike != null) {
                newsLikeRepository.delete(newsLikeModelLike);
                NewsLikeModel newsLikeModel2 = new NewsLikeModel();
                newsLikeModel2.setNewsId(id);
                newsLikeModel2.setUserip(userIp);
                newsLikeModel2.setStatus(-1);
                newsLikeRepository.save(newsLikeModel2);




            }else {
                newsLikeRepository.delete(newsLikeModel);

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
