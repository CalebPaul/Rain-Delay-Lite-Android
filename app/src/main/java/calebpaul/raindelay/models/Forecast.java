package calebpaul.raindelay.models;

/**
 * Created by calebpaul on 12/3/16.
 */
public class Forecast {
    private int mTime;
    private String mSummary;
    private String mIcon;
    private int mTemperature;
    private int mRainProbability;
    private int mRainIntensity;
    private int mWindSpeed;

    public Forecast(int mTime, String mSummary, String mIcon, int mTemperature, int mRainProbability, int mRainIntensity, int mWindSpeed) {
        this.mTime = mTime;
        this.mSummary = mSummary;
        this.mIcon = mIcon;
        this.mTemperature = mTemperature;
        this.mRainProbability = mRainProbability;
        this.mRainIntensity = mRainIntensity;
        this.mWindSpeed = mWindSpeed;
    }

    public int getTime() {
        return mTime;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getIcon() {
        return mIcon;
    }

    public int getTemperature() {
        return mTemperature;
    }

    public int getRainProbability() {
        return mRainProbability;
    }

    public int getRainIntensity() {
        return mRainIntensity;
    }

    public int getWindSpeed() {
        return mWindSpeed;
    }
}
