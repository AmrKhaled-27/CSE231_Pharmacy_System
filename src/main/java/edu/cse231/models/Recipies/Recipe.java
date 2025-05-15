package edu.cse231.models.Recipies;

import java.util.Date;

public class Recipe {

    private final String id;
    private final String patientName;
    private final String doctorName;
    private final Date dateIssued;
    private final Date expirationDate;
    private boolean isActive;

    public Recipe(String id, String patientName, String doctorName, Date dateIssued, Date expirationDate) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateIssued = new Date(dateIssued.getTime());
        this.expirationDate = new Date(expirationDate.getTime());
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Date getDateIssued() {
        return new Date(dateIssued.getTime());
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public boolean isValid() {
        Date currentDate = new Date();
        return currentDate.before(expirationDate) && !currentDate.before(dateIssued);
    }

    public boolean isActive() {
        return isActive;
    }

    public void markAsFulfilled() {
        isActive = false;
    }
}
