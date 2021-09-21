package atb.social.network.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeEditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date;

    private int employeeId;

    private int fPosition;

    private int lPosition;

    private String dateWithoutYear;

    private String status;

    public EmployeeEditHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getfPosition() {
        return fPosition;
    }

    public void setfPosition(int fPosition) {
        this.fPosition = fPosition;
    }

    public int getlPosition() {
        return lPosition;
    }

    public void setlPosition(int lPosition) {
        this.lPosition = lPosition;
    }

    public String getDateWithoutYear() {
        return dateWithoutYear;
    }

    public void setDateWithoutYear(String dateWithoutYear) {
        this.dateWithoutYear = dateWithoutYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
