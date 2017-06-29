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

public class WeatherInfoActivity extends Activity{
    private TextView cityName;
    private TextView tomorrowDate;

    private List<Weather> weatherList;
    private List<Weather> getWeather;
    private ListView listViewWeather;
    private List<WeatherSetter> setWeather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomorrow_weather_layout);
        listViewWeather = (ListView)findViewById(R.id.listViewTomorrowWeather);
        weatherList = Utils.weatherList;
        setWeather = new ArrayList<>();
        getWeather = new ArrayList<>();

        Intent intent = getIntent();
        Weather weather = (Weather) intent.getSerializableExtra("weatherData");

        cityName = (TextView)findViewById(R.id.tomorrow_weather_cityText);
        tomorrowDate = (TextView)findViewById(R.id.tomorrow_weather_dateText);

        setDataIntoFields(weather);

        this.listViewWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weather weather = getWeather.get(position);
                Intent intent = new Intent(WeatherInfoActivity.this, WeatherDetailActivity.class);
                intent.putExtra("weatherDetails",weather);
                startActivity(intent);
            }
        });
    }

    private void setDataIntoFields(Weather weather) {
        cityName.setText(weatherList.get(0).place.getCity()+"," +weatherList.get(0).place.getCountry());

        String[] tomorrowDateStr = weather.place.getForecastDate().split(" ");

        tomorrowDate.setText("Day: " + Utils.getDayOfTheWeek(tomorrowDateStr[0])+"");

        for(int i = 0; i < weatherList.size(); i++){
            String[] data = weatherList.get(i).place.getForecastDate().split(" ");
            if(tomorrowDateStr[0].equals(data[0])) {
                setWeather.add(new WeatherSetter(weatherList.get(i).condition.getCondition(),
                        weatherList.get(i).temperature.getTemperature(),
                        weatherList.get(i).place.getForecastDate(),
                        IconSetter.setWeatherIcon((weatherList.get(i).condition.getIcon()))));
                getWeather.add(weatherList.get(i));
            }
        }
        listViewWeather.setAdapter(new WeatherListAdapter(WeatherInfoActivity.this, setWeather));
    }
}
