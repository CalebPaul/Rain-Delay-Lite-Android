package calebpaul.raindelay.models;

/**
 * Created by calebpaul on 12/3/16.
 */
public class Forecast {
    private String mTime;
    private String mSummary;
    private String mIcon;
    private int mTemperature;
    private int mRainProbability;
    private int mRainIntensity;
    private int mWindSpeed;

    public Forecast(String mTime, String mSummary, String mIcon, int mTemperature, int mRainProbability, int mRainIntensity, int mWindSpeed) {
        this.mTime = mTime;
        this.mSummary = mSummary;
        this.mIcon = mIcon;
        this.mTemperature = mTemperature;
        this.mRainProbability = mRainProbability;
        this.mRainIntensity = mRainIntensity;
        this.mWindSpeed = mWindSpeed;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmSummary() {
        return mSummary;
    }

    public String getmIcon() {
        return mIcon;
    }

    public int getmTemperature() {
        return mTemperature;
    }

    public int getmRainProbability() {
        return mRainProbability;
    }

    public int getmRainIntensity() {
        return mRainIntensity;
    }

    public int getmWindSpeed() {
        return mWindSpeed;
    }
}
