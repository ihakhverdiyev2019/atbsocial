package atb.social.network.model;


import javax.persistence.*;

@Entity
public class NewsModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String title;

    private String mainPhoto;

    @Column(columnDefinition="TEXT")
    private String text;

    private String date;

    private String photo1;


    private String photo2;
    private String photo3;
    private String photo4;






    public NewsModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




//
//    @Transient
//    public String getPhotosImagePath() {
//        if(photoBase64==null)
//            return null;
//
//        return "/user-photos/" + id + "/" + photoBase64;
//    }

}
