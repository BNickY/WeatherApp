package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Weather;
import util.Utils;

public class JSONWeatherParser {
    public static Weather getWeather(String data) {
        Weather weather = new Weather();

        try {
            JSONObject jsonObject = new JSONObject(data);

            //main(temperature, max-min temperature, pressure, humidity
            JSONObject mainObj = Utils.getObject("main",jsonObject);
            weather.temperature.setTemperature(Utils.getDouble("temp",mainObj));
            weather.temperature.setMinTemperature(Utils.getFloat("temp_min",mainObj));
            weather.temperature.setMaxTemperature(Utils.getFloat("temp_max",mainObj));
            weather.pressure.setPressure(Utils.getFloat("pressure",mainObj));
            weather.humidity.setHumidity(Utils.getFloat("humidity",mainObj));

            //wind speed
            JSONObject windObj = Utils.getObject("wind",jsonObject);
            weather.wind.setWindSpeed(Utils.getFloat("speed",windObj));

            //clouds
            JSONObject cloudsObj = Utils.getObject("clouds",jsonObject);
            weather.cloudiness.setCloudiness(Utils.getFloat("all",cloudsObj));

            //sys info
            JSONObject sysObj = Utils.getObject("sys",jsonObject);
            weather.place.setCity(Utils.getString("name",jsonObject));
            weather.place.setCountry(Utils.getString("country",sysObj));
            weather.place.setLastupdate(Utils.getInt("dt",jsonObject));
            weather.place.setSunrise(Utils.getInt("sunrise",sysObj));
            weather.place.setSunset(Utils.getInt("sunset",sysObj));

            //get weather description
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.condition.setCondition(Utils.getString("main",jsonWeather));
            weather.condition.setIcon(Utils.getString("icon",jsonWeather));
            weather.condition.setDescription(Utils.getString("description",jsonWeather));

            return weather;

        } catch (JSONException e) {
            return null;
        }
    }
}
