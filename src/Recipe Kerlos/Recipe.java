package pharmacysystem;

import java.util.Date;

public class Recipe {
    private String id;
    private String patientName;
    private String doctorName;
    private Date dateIssued;
    private Date expirationDate;
    private boolean isActive;

    public Recipe(String id, String patientName, String doctorName, Date dateIssued, Date expirationDate) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateIssued = dateIssued;
        this.expirationDate = expirationDate;
        this.isActive = true;
    }

    public String getId() { return id; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public Date getDateIssued() { return dateIssued; }
    public Date getExpirationDate() { return expirationDate; }
    public boolean isActive() { return isActive; }
    public void markAsFulfilled() { isActive = false; }
}
