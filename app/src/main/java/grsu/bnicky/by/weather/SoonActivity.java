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

public class SoonActivity extends Activity{

    private TextView cityName;
    private TextView soonDate;

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
        soonDate = (TextView)findViewById(R.id.tomorrow_weather_dateText);

        setDataIntoFields();

        this.listViewTomorrowWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weather weather = getWeather.get(position);
                Intent intent = new Intent(SoonActivity.this, WeatherDetailActivity.class);
                intent.putExtra("weatherDetails",weather);
                startActivity(intent);
            }
        });
    }

    private void setDataIntoFields() {
        cityName.setText(weatherList.get(0).place.getCity()+"," +weatherList.get(0).place.getCountry());

        String[] tomorrowDateStr = weatherList.get(0).place.getForecastDate().split(" ");
        String dateOfForecast = tomorrowDateStr[0];
        int amountOfForecastNeeded = 0;

        while (dateOfForecast.equals(tomorrowDateStr[0])){
            amountOfForecastNeeded++;
            String[] date = weatherList.get(amountOfForecastNeeded).place.getForecastDate().split(" ");
            dateOfForecast = date[0];
        }

        soonDate.setText("Today: " + Utils.getDayOfTheWeek(tomorrowDateStr[0])+"");

        for(int i = 0; i < amountOfForecastNeeded; i++){
            setWeather.add(new WeatherSetter(weatherList.get(i).condition.getCondition(),
                    weatherList.get(i).temperature.getTemperature(),
                    weatherList.get(i).place.getForecastDate(),
                    IconSetter.setWeatherIcon((weatherList.get(i).condition.getIcon()))));
            getWeather.add(weatherList.get(i));
        }
        listViewTomorrowWeather.setAdapter(new WeatherListAdapter(SoonActivity.this, setWeather));
    }
}
