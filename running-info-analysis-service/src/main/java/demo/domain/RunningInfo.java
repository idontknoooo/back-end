package demo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;

@JsonIgnoreProperties({"getJson"})
public @Entity @Table(name = "RUNNING_INFO") @Getter @NoArgsConstructor @JsonInclude(JsonInclude.Include.NON_NULL) class RunningInfo {

    // Health Level Enum for ordering
    enum HealthWarningLevel {
        LOW, NORMAL, HIGH;
    }

    // Declare members
    @Id private String runningId; // Database ID
    private double latitude;
    private double longitude;
    private double runningDistance;
    private double totalRunningTime;
    private int heartRate;
    private Date timestamp;
    @Embedded private UserInfo userInfo; // Embed UserInfo instance
    private HealthWarningLevel healthWarningLevel;

    // Set HealthWarningLevel by condition
    private void setHealthWarningLevel() {
        if(heartRate >= 60 && heartRate <= 75)
            healthWarningLevel = HealthWarningLevel.LOW;
        else if (heartRate > 75 && heartRate <= 120)
            healthWarningLevel = HealthWarningLevel.NORMAL;
        else
            healthWarningLevel = HealthWarningLevel.HIGH;
    }

    // Json-style Info Creator & Constructor
    @JsonCreator
    public RunningInfo(@JsonProperty("runningId") String runningId,
                      @JsonProperty("latitude") String latitude,
                      @JsonProperty("longitude") String longitude,
                      @JsonProperty("runningDistance") String runningDistance,
                      @JsonProperty("totalRunningTime") String totalRunningTime,
                      @JsonProperty("heartRate") int heartRate,
                      @JsonProperty("timestamp") Date timestamp,
                      @JsonProperty("userInfo") UserInfo userInfo) {

        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);

        if(heartRate == 0) {
            Random rand = new Random();
            this.heartRate = 60 + rand.nextInt(140);
        }

        if(timestamp==null)
            this.timestamp = new Date();
        else
            this.timestamp = timestamp;

        this.userInfo = userInfo;

        setHealthWarningLevel();
    }

    public String getJson() {

        StringBuilder sb = new StringBuilder();

        String[] stringArr = new String[7];
        stringArr[0] = "{\n\"runningId\":\"" + runningId + "\",\n";
        stringArr[1] = "\"totalRunningTime\":\"" + Double.toString(totalRunningTime)+ "\",\n";
        stringArr[2] = "\"heartRate\":\"" + Integer.toString(heartRate)+ "\",\n";
        stringArr[3] = "\"userId\":\"" + Integer.toString(userInfo.getUserId())+ "\",\n";
        stringArr[4] = "\"username\":\"" + userInfo.getName() + "\",\n";
        stringArr[5] = "\"userAddress\":\"" + userInfo.getAddress() + "\",\n";
        stringArr[6] = "\"healthWarningLevel\":\"" + healthWarningLevel.toString() + "\",\n}";

        for(int i = 0; i < 7; ++i)
            sb.append(stringArr[i]);

        return sb.toString();
    }
}