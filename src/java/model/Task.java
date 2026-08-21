package model;

public class Task {
    private int id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private int assigneeId;
    private int projectId;
    private String status;
    private String assigneeName;
    private String projectName;

    public Task() {}
    public int getId(){return id;} public void setId(int v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getStartDate(){return startDate;} public void setStartDate(String v){startDate=v;}
    public String getEndDate(){return endDate;} public void setEndDate(String v){endDate=v;}
    public int getAssigneeId(){return assigneeId;} public void setAssigneeId(int v){assigneeId=v;}
    public int getProjectId(){return projectId;} public void setProjectId(int v){projectId=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getAssigneeName(){return assigneeName;} public void setAssigneeName(String v){assigneeName=v;}
    public String getProjectName(){return projectName;} public void setProjectName(String v){projectName=v;}
}
