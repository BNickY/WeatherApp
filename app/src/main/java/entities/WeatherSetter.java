package entities;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import util.Utils;

public class WeatherSetter {

    private String condition;
    private String temperature;
    private String forecastDate;
    private int weatherPhoto;

    public WeatherSetter(int weatherPhoto, String forecastDate, double temperature, String condition){
        this.condition = condition;
        this.weatherPhoto = weatherPhoto;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String tempFormat = decimalFormat.format(temperature);
        this.temperature = tempFormat+"°C";
        String[] date = forecastDate.split(" ");


        this.forecastDate = Utils.getDayOfTheWeek(date[0]);
    }

    public WeatherSetter(String condition, double temperature, String forecastDate, int weatherPhoto) {

        this.condition = condition;

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String tempFormat = decimalFormat.format(temperature);
        this.temperature = tempFormat+"°C";

        String[] date = forecastDate.split(" ");
        String time = date[1];
        String[] neededTime = time.split(":");
        String necessaryTime = neededTime[0]+":" + neededTime[1];

        this.forecastDate = necessaryTime;
        this.weatherPhoto = weatherPhoto;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public int getWeatherPhoto() {
        return weatherPhoto;
    }

    public void setWeatherPhoto(int weatherPhoto) {
        this.weatherPhoto = weatherPhoto;
    }
}
