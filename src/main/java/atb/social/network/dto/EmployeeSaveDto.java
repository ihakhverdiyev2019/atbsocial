package atb.social.network.dto;

import java.sql.Clob;

public class EmployeeSaveDto {

    private int branchId;

    private int departId;

    private int subDepartId;

    private String name;

    private String surname;

    private String email;

    private int position;

    private Clob photo;

    private String birthDay;

    private String startDate;

    private String internalNum;

    private String number;

    public EmployeeSaveDto() {
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public int getSubDepartId() {
        return subDepartId;
    }

    public void setSubDepartId(int subDepartId) {
        this.subDepartId = subDepartId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Clob getPhoto() {
        return photo;
    }

    public void setPhoto(Clob photo) {
        this.photo = photo;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getInternalNum() {
        return internalNum;
    }

    public void setInternalNum(String internalNum) {
        this.internalNum = internalNum;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
