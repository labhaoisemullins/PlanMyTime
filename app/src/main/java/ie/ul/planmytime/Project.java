package ie.ul.planmytime;

public class Project {
    private String module;
    private String dueDate;
    private String percentage;


    public Project(String module, String dueDate, String percentage) {
        this.module = module;
        this.dueDate = dueDate;
        this.percentage = percentage;
    }

    public String getProjModule() {
        return module;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPercentage() {
        return percentage;
    }
}