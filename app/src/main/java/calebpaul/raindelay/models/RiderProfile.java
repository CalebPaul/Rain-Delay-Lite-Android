package calebpaul.raindelay.models;

import org.parceler.Parcel;

/**
 * Created by calebpaul on 12/13/16.
 */

@Parcel
public class RiderProfile {
    private String mConditionName;
    private double mWindSpeed;
    private double mTempHi;
    private double mTempLo;

    public RiderProfile() {}

    public RiderProfile(String mConditionName, double mWindSpeed, double mTempHi, double mTempLo) {
        this.mConditionName = mConditionName;
        this.mWindSpeed = mWindSpeed;
        this.mTempHi = mTempHi;
        this.mTempLo = mTempLo;
    }

    public String getmConditionName() {
        return mConditionName;
    }

    public double getmWindSpeed() {
        return mWindSpeed;
    }

    public double getmTempHi() {
        return mTempHi;
    }

    public double getmTempLo() {
        return mTempLo;
    }
}
