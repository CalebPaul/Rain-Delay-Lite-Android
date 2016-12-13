package calebpaul.raindelay.models;

import org.parceler.Parcel;

/**
 * Created by calebpaul on 12/13/16.
 */

@Parcel
public class RiderProfile {
    private String conditionName;
    private int windSpeed;
    private int tempHi;
    private int tempLo;

    public RiderProfile() {}

    public RiderProfile(String conditionName, int windSpeed, int tempHi, int tempLo) {
        this.conditionName = conditionName;
        this.windSpeed = windSpeed;
        this.tempHi = tempHi;
        this.tempLo =  tempLo;
    }

    public String getConditionName() {
        return conditionName;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getTempHi() {
        return tempHi;
    }

    public int getTempLo() {
        return tempLo;
    }
}
