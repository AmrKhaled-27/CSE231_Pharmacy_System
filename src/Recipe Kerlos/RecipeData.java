package pharmacysystem;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RecipeData {
    private final StringProperty patientName;
    private final StringProperty doctorName;
    private final ObjectProperty<LocalDate> issuedDate;
    private final ObjectProperty<LocalDate> expirationDate;

    public RecipeData(String patientName, String doctorName, LocalDate issuedDate, LocalDate expirationDate) {
        this.patientName = new SimpleStringProperty(patientName);
        this.doctorName = new SimpleStringProperty(doctorName);
        this.issuedDate = new SimpleObjectProperty<>(issuedDate);
        this.expirationDate = new SimpleObjectProperty<>(expirationDate);
    }

    public String getPatientName() {
        return patientName.get();
    }

    public StringProperty patientNameProperty() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName.get();
    }

    public StringProperty doctorNameProperty() {
        return doctorName;
    }

    public LocalDate getIssuedDate() {
        return issuedDate.get();
    }

    public ObjectProperty<LocalDate> issuedDateProperty() {
        return issuedDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate.get();
    }

    public ObjectProperty<LocalDate> expirationDateProperty() {
        return expirationDate;
    }
}