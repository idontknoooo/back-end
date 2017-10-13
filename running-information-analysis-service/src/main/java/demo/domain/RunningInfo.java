package demo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Random;

import static demo.domain.RunningInfo.healthWarningLevel.HIGH;
import static demo.domain.RunningInfo.healthWarningLevel.LOW;
import static demo.domain.RunningInfo.healthWarningLevel.NORMAL;


public @Entity @Table(name="RUNNING_INFO") @Data @NoArgsConstructor @JsonInclude(JsonInclude.Include.NON_NULL)
class RunningInfo {

    enum healthWarningLevel{
        LOW, NORMAL, HIGH;
    }

    @Id
    private String runningId;

    @JsonIgnore
    private double latitude;
    @JsonIgnore
    private double longitude;
    @JsonIgnore
    private double runningDistance;
    private double totalRunningTime;
    private int heartRate;
    @JsonIgnore
    private Date timeStamp;

    //private String userInfo;
    @Embedded
    private UserInfo userInfo;

    private healthWarningLevel healthWarningLevel;

    @JsonCreator
    public RunningInfo(@JsonProperty("runningId") String runningId,
                       @JsonProperty("latitude") String latitude,
                       @JsonProperty("longitude") String longitude,
                       @JsonProperty("runningDistance") String runningDistance,
                       @JsonProperty("totalRunningTime") String totalRunningTime,
                       @JsonProperty("heartRate") int heartRate,
                       @JsonProperty("timeStamp") Date timeStamp,
                       @JsonProperty("userInfo") UserInfo userInfo){

        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);

        if(heartRate==0){
            Random rand = new Random();
            this.heartRate = 60 + rand.nextInt(140);
        }

        if(timeStamp != null)
            this.timeStamp = timeStamp;
        else
            this.timeStamp = new Date();

        this.userInfo = userInfo;

        if(heartRate >= 60 && heartRate <= 75)
            this.healthWarningLevel = LOW;
        else if (heartRate > 75 && heartRate <= 120)
            this.healthWarningLevel = NORMAL;
        else
            this.healthWarningLevel = HIGH;
    }


}
