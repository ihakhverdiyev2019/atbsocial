package atb.social.network.dto;

public class SubDepartDto {
    private String subDepartName;

    private int branchId;

    private int departId;
    private int positionLevel;

    public SubDepartDto() {
    }

    public String getSubDepartName() {
        return subDepartName;
    }

    public void setSubDepartName(String subDepartName) {
        this.subDepartName = subDepartName;
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

    public int getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(int positionLevel) {
        this.positionLevel = positionLevel;
    }
}
