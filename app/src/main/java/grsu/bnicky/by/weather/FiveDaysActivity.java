package grsu.bnicky.by.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.WeatherListAdapter;
import data.IconSetter;
import entities.WeatherSetter;
import model.Weather;
import util.Utils;

public class FiveDaysActivity extends Activity{

    private TextView cityName;
    private TextView fiveDaysText;

    private List<Weather> weatherList;
    private ListView listViewTomorrowWeather;
    private List<WeatherSetter> setWeather;
    private List<Weather> getWeather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomorrow_weather_layout);

        listViewTomorrowWeather = (ListView)findViewById(R.id.listViewTomorrowWeather);
        weatherList = Utils.weatherList;
        setWeather = new ArrayList<>();
        getWeather = new ArrayList<>();

        cityName = (TextView)findViewById(R.id.tomorrow_weather_cityText);
        fiveDaysText = (TextView)findViewById(R.id.tomorrow_weather_dateText);

        setDataIntoFields();

        this.listViewTomorrowWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weather weather = getWeather.get(position);
                Intent intent = new Intent(FiveDaysActivity.this, WeatherInfoActivity.class);
                intent.putExtra("weatherData",weather);
                startActivity(intent);
            }
        });
    }

    private void setDataIntoFields() {
        cityName.setText(weatherList.get(0).place.getCity()+"," +weatherList.get(0).place.getCountry());

        String dateOfForecast = "15:00:00";
        List<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < weatherList.size(); i++){
            String[] date = weatherList.get(i).place.getForecastDate().split(" ");
            if(dateOfForecast.equals(date[1])){
                indexes.add(i);
            }
        }

        fiveDaysText.setText(""+"Next 5 days"+"");

        for(int i = 0; i < weatherList.size(); i++){
            if(indexes.contains(i)) {
                setWeather.add(new WeatherSetter(
                        IconSetter.setWeatherIcon((weatherList.get(i).condition.getIcon())),
                        weatherList.get(i).place.getForecastDate(),
                        weatherList.get(i).temperature.getTemperature(),
                        weatherList.get(i).condition.getCondition()));
                getWeather.add(weatherList.get(i));
            }
        }
        listViewTomorrowWeather.setAdapter(new WeatherListAdapter(FiveDaysActivity.this, setWeather));
    }
}