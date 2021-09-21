package atb.social.network.dto;

public class EmployeePositionChangesDto {
    private String name;
    private String surname;

    private String internalNumber;

    private String status;

    private String fPosition;
    private String lPosition;

    public EmployeePositionChangesDto() {
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

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getfPosition() {
        return fPosition;
    }

    public void setfPosition(String fPosition) {
        this.fPosition = fPosition;
    }

    public String getlPosition() {
        return lPosition;
    }

    public void setlPosition(String lPosition) {
        this.lPosition = lPosition;
    }
}
