package model;

import java.io.Serializable;

public class Weather implements Serializable{

    public Cloudiness cloudiness = new Cloudiness();
    public Condition condition = new Condition();
    public Humidity humidity = new Humidity();
    public Place place = new Place();
    public Pressure pressure = new Pressure();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
}
