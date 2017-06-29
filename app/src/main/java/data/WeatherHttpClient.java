package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import util.Utils;

public class WeatherHttpClient {

    public String getWeatherData(String forecastType, String url){
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection)(new URL(url + forecastType + Utils.API_KEY)).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return  stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
