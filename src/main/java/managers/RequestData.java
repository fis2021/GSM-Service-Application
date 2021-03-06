package managers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RequestData {

    private final IntegerProperty ID;
    private final StringProperty username;
    private final StringProperty brand;
    private final StringProperty model;
    private final StringProperty problem;
    private final StringProperty interval;
    private final StringProperty status;
    private final StringProperty progress;

    public RequestData(Integer id, String username, String brand, String model, String problem, String interval, String status, String progress){
        this.ID = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.problem = new SimpleStringProperty(problem);
        this.interval = new SimpleStringProperty(interval);
        this.status = new SimpleStringProperty(status);
        this.progress = new SimpleStringProperty(progress);
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getProblem() {
        return problem.get();
    }

    public StringProperty problemProperty() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem.set(problem);
    }

    public String getInterval() {
        return interval.get();
    }

    public StringProperty intervalProperty() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval.set(interval);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getProgress() {
        return progress.get();
    }

    public StringProperty progressProperty() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress.set(progress);
    }
}
