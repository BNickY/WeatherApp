package grsu.bnicky.by.weather;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import data.CityPreference;
import data.DataPreference;
import data.ForecastPreference;
import data.JSONForecastWeatherParser;
import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Pressure;
import model.Weather;
import util.Utils;

public class MainActivity extends AppCompatActivity {

    private TextView cityName;
    private TextView temperature;
    private TextView condition;
    private TextView minTemperature;
    private ImageView iconView;
    private TextView wind;
    private TextView pressure;
    private TextView humidity;
    private TextView cloudiness;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    private TextView currentDate;

    private Bitmap bitmap;
    Weather weather = new Weather();
    Weather offlineWeather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        cityName = (TextView) findViewById(R.id.cityText);
        temperature = (TextView) findViewById(R.id.temperatureText);
        condition = (TextView) findViewById(R.id.conditionText);
        minTemperature = (TextView) findViewById(R.id.maxMinTempText);
        iconView = (ImageView)findViewById(R.id.weatherIcon);
        wind = (TextView) findViewById(R.id.windText);
        pressure = (TextView) findViewById(R.id.pressureText);
        humidity = (TextView) findViewById(R.id.humidityText);
        cloudiness = (TextView) findViewById(R.id.cloudinessText);
        sunrise = (TextView) findViewById(R.id.sunriseText);
        sunset = (TextView) findViewById(R.id.sunsetText);
        updated = (TextView) findViewById(R.id.updateText);
        currentDate = (TextView)findViewById(R.id.dateText);

        if(!Utils.isOnline(MainActivity.this)){
            connectionDialog();
        }else {
            CityPreference cityPreference = new CityPreference(MainActivity.this);
            renderWeatherData(cityPreference.getCity());
        }
    }

    private void connectionDialog() {
        AlertDialog.Builder checkBuilder = new AlertDialog.Builder(MainActivity.this);
        checkBuilder.setIcon(R.drawable.error);
        checkBuilder.setTitle("Error!");
        checkBuilder.setMessage("Check your Internet connection");

        final DataPreference dataPreference = new DataPreference(MainActivity.this);
        final ForecastPreference forecastPreference = new ForecastPreference(MainActivity.this);

        checkBuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.recreate();
            }
        });

        checkBuilder.setNeutralButton("Offline", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!dataPreference.getOfflineData().equals("null") &&
                        !forecastPreference.getOfflineForecast().equals("null")){

                    String data = dataPreference.getOfflineData();
                    offlineWeather = JSONWeatherParser.getWeather(data);
                    setDataIntoFields(offlineWeather);

                    String forecast = forecastPreference.getOfflineForecast();
                    Utils.weatherList = JSONForecastWeatherParser.getForecastWeather(forecast);
                }else {
                    connectionDialog();
                }
            }
        });

        checkBuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = checkBuilder.create();
        alertDialog.show();
    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric",Utils.BASE_URL,Utils.FORECAST_URL});
    }

    //on click listeners
    //click to see 5 days info forecast
    public void onClick5days(View view) {
        Intent intent = new Intent(MainActivity.this,FiveDaysActivity.class);
        startActivity(intent);
    }

    //click to see forecast soon
    public void onClickSoon(View view) {
        Intent intent = new Intent(MainActivity.this,SoonActivity.class);
        startActivity(intent);
    }

    //click to see tomorrow forecast
    public void onClickTomorrow(View view) {
        Intent intent = new Intent(MainActivity.this, TomorrowActivity.class);
        startActivity(intent);
    }


    private class WeatherTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... params) {
            String data = new WeatherHttpClient().getWeatherData(params[0],params[1]);
            DataPreference dataPreference = new DataPreference(MainActivity.this);
            dataPreference.setOfflineData(data);

            String forecastData = new WeatherHttpClient().getWeatherData(params[0],params[2]);
            ForecastPreference forecastPreference = new ForecastPreference(MainActivity.this);
            forecastPreference.setOfflineForecast(forecastData);

            Utils.weatherList = JSONForecastWeatherParser.getForecastWeather(forecastData);
            weather = JSONWeatherParser.getWeather(data);

            String weatherIcon = weather.condition.getIcon();
            bitmap = downloadImage(weatherIcon);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            setDataIntoFields(weather);
        }

        private Bitmap downloadImage(String code){
            try {
                URL url = new URL(Utils.ICON_URL + code + ".png");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            }catch (IOException e){
                Log.e("DownloadImage","Error: " + e.toString());
                return null;
            }

        }
    }

    private void setDataIntoFields(Weather weather) {
        Date dateSunrise = new Date(weather.place.getSunrise()*1000L);
        Date dateSunset = new Date(weather.place.getSunset()*1000L);
        Date dateUpdate = new Date(weather.place.getLastupdate()*1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String sunriseDate = dateFormat.format(dateSunrise);
        String sunsetDate = dateFormat.format(dateSunset);
        String updateDate = dateFormat.format(dateUpdate);

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String tempFormat = decimalFormat.format(weather.temperature.getTemperature());

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMM");
        String dateString = sdf.format(date);

        currentDate.setText(dateString);
        iconView.setImageBitmap(bitmap);
        cityName.setText(weather.place.getCity() + "," + weather.place.getCountry());
        temperature.setText(""+ tempFormat + "°C");
        minTemperature.setText("Feels Like " + (int)weather.temperature.getMinTemperature() + "°C");
        condition.setText(weather.condition.getDescription());
        wind.setText("" + weather.wind.getWindSpeed() + " m/s");
        pressure.setText("" + weather.pressure.getPressure() + " hPa");
        humidity.setText("" + weather.humidity.getHumidity() + " %");
        cloudiness.setText("" + weather.cloudiness.getCloudiness()+ " %");
        sunrise.setText(sunriseDate);
        sunset.setText(sunsetDate);
        updated.setText("Updated: " + updateDate + "");
    }

    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change city");

        final EditText cityInput = new EditText(MainActivity.this);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("London,UK");
        builder.setView(cityInput);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CityPreference preference = new CityPreference(MainActivity.this);
                preference.setCity(cityInput.getText().toString());
                String newCity = preference.getCity();
                renderWeatherData(newCity);
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.recreate_Activity: {
                MainActivity.this.recreate();
                break;
            }
            case R.id.change_cityId: {
                showInputDialog();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
