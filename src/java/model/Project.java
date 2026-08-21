package model;

public class Project {
    private int id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private int creatorId;
    private String creatorName;

    public Project() {}
    public int getId(){return id;} public void setId(int v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getStartDate(){return startDate;} public void setStartDate(String v){startDate=v;}
    public String getEndDate(){return endDate;} public void setEndDate(String v){endDate=v;}
    public int getCreatorId(){return creatorId;} public void setCreatorId(int v){creatorId=v;}
    public String getCreatorName(){return creatorName;} public void setCreatorName(String v){creatorName=v;}
}
