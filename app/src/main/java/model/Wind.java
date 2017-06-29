package model;

import java.io.Serializable;

public class Wind implements Serializable{
    private float windSpeed;

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }
}
