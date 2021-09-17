package atb.social.network.dto;

public class DepartmentDto {

    private String departmenName;

    private int branchId;

    public DepartmentDto() {
    }

    public String getDepartmenName() {
        return departmenName;
    }

    public void setDepartmenName(String departmenName) {
        this.departmenName = departmenName;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
