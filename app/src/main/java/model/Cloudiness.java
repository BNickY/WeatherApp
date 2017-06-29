package model;

import java.io.Serializable;

public class Cloudiness implements Serializable{
    private float cloudiness;

    public float getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(float cloudiness) {
        this.cloudiness = cloudiness;
    }
}
