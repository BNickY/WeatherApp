package grsu.bnicky.by.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import data.ImageSetter;
import model.Weather;
import util.Utils;

public class WeatherDetailActivity extends Activity {

    private RelativeLayout weatherDetail;
    private TextView detailCityTextName;
    private TextView detailDate;
    private TextView detailTemperature;
    private TextView detailMinTemperature;
    private TextView detailCondition;
    private TextView detailWindSpeed;
    private TextView detailPressure;
    private TextView detailHumidity;
    private ImageView humidityImg;
    private ImageView pressureImg;
    private ImageView windspeedImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        weatherDetail = (RelativeLayout)findViewById(R.id.weather_detail_activity);
        detailCityTextName = (TextView)findViewById(R.id.detailCityName);
        detailDate = (TextView)findViewById(R.id.detailDate);
        detailTemperature = (TextView)findViewById(R.id.detailTemperature);
        detailMinTemperature = (TextView)findViewById(R.id.detailMinTemperature);
        detailCondition = (TextView)findViewById(R.id.detailDescription);
        detailWindSpeed = (TextView)findViewById(R.id.detailWindText);
        detailPressure = (TextView)findViewById(R.id.detailPressureText);
        detailHumidity = (TextView)findViewById(R.id.detailHumidityText);
        humidityImg = (ImageView)findViewById(R.id.imageViewHumidity);
        pressureImg = (ImageView)findViewById(R.id.imageViewPressure);
        windspeedImg = (ImageView)findViewById(R.id.imageViewWindSpeed);

        Intent intent = getIntent();
        Weather weather = (Weather) intent.getSerializableExtra("weatherDetails");

        if( weather.condition.getIcon().equals("09d")||weather.condition.getIcon().equals("09n")||
            weather.condition.getIcon().equals("10d")||weather.condition.getIcon().equals("10n")||
            weather.condition.getIcon().equals("13d")||weather.condition.getIcon().equals("13n")){
            windspeedImg.setBackgroundResource(R.drawable.windspeed_iconblack);
            pressureImg.setBackgroundResource(R.drawable.pressure_iconblack);
            humidityImg.setBackgroundResource(R.drawable.humidity_iconblack);
            detailHumidity.setTextColor(getResources().getColor(R.color.colorDarkBlue));
            detailPressure.setTextColor(getResources().getColor(R.color.colorDarkBlue));
            detailWindSpeed.setTextColor(getResources().getColor(R.color.colorDarkBlue));
            detailCondition.setTextColor(getResources().getColor(R.color.colorDarkBlue));
            detailMinTemperature.setTextColor(getResources().getColor(R.color.colorDarkBlue));
            detailTemperature.setTextColor(getResources().getColor(R.color.colorDarkBlue));
        }

        DecimalFormat decimalFormatCondition = new DecimalFormat("#");
        String pressure = decimalFormatCondition.format(weather.pressure.getPressure());
        detailPressure.setText(""+pressure+" hPa");

        String[] simpleDate = weather.place.getForecastDate().split(" ");
        String dateString = Utils.getForecastDate(simpleDate[0]);
        detailCityTextName.setText(weather.place.getCity()+","+weather.place.getCountry());
        detailDate.setText(dateString);
        weatherDetail.setBackgroundResource(ImageSetter.setWeatherImage(weather.condition.getIcon()));

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String temperature = decimalFormat.format(weather.temperature.getTemperature());
        detailTemperature.setText(""+ temperature+ "°C");

        detailMinTemperature.setText("Feels Like " + (int)weather.temperature.getMinTemperature() + "°C");
        detailCondition.setText(weather.condition.getDescription());

        String windSpeed = decimalFormatCondition.format(weather.wind.getWindSpeed());
        detailWindSpeed.setText(""+windSpeed+" m/s");

        String humidity = decimalFormat.format(weather.humidity.getHumidity());
        detailHumidity.setText(""+humidity+" %");
    }
}
