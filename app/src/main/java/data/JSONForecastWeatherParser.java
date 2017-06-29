package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Weather;
import util.Utils;

public class JSONForecastWeatherParser {

    public static List<Weather> getForecastWeather(String data){
        List<Weather> weatherList = new ArrayList<Weather>();

        try {

            JSONObject jsonObject = new JSONObject(data);

            //get amount of weather forecasts
            int amountOfWeatherForecasts = Utils.getInt("cnt",jsonObject);

            //array of list
            JSONArray jsonArray = jsonObject.getJSONArray("list");

            for(int i = 0; i< amountOfWeatherForecasts; i++){
                JSONObject jsonList = jsonArray.getJSONObject(i);
                Weather weather  = new Weather();

                //main
                JSONObject mainObj = Utils.getObject("main",jsonList);
                weather.temperature.setTemperature(Utils.getDouble("temp",mainObj));
                weather.temperature.setMinTemperature(Utils.getFloat("temp_min",mainObj));
                weather.temperature.setMaxTemperature(Utils.getFloat("temp_max",mainObj));
                weather.pressure.setPressure(Utils.getFloat("pressure",mainObj));
                weather.humidity.setHumidity(Utils.getFloat("humidity",mainObj));

                //wind speed
                JSONObject windObj = Utils.getObject("wind",jsonList);
                weather.wind.setWindSpeed(Utils.getFloat("speed",windObj));

                //clouds
                JSONObject cloudsObj = Utils.getObject("clouds",jsonList);
                weather.cloudiness.setCloudiness(Utils.getFloat("all",cloudsObj));

                //weather info
                JSONArray jsonWeatherArray = jsonList.getJSONArray("weather");
                JSONObject weatherObj = jsonWeatherArray.getJSONObject(0);
                weather.condition.setCondition(Utils.getString("main",weatherObj));
                weather.condition.setDescription(Utils.getString("description",weatherObj));
                weather.condition.setIcon(Utils.getString("icon",weatherObj));

                //place
                JSONObject placeObj = Utils.getObject("city",jsonObject);
                weather.place.setCity(Utils.getString("name",placeObj));
                weather.place.setCountry(Utils.getString("country",placeObj));
                weather.place.setForecastDate(Utils.getString("dt_txt",jsonList));
                weather.place.setLastupdate(Utils.getInt("dt",jsonList));
                weatherList.add(weather);
            }
            return weatherList;
        } catch (JSONException e) {
            return null;
        }
    }
}
