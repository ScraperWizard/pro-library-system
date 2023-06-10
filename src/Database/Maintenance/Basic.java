package Database.Maintenance;

public class Basic implements MaintenanceStatus {
    Maintenance globalMaintenanceObject = new Maintenance();
    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public void setStatus(String newStatus) {

    }
}
