package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import entities.WeatherSetter;
import grsu.bnicky.by.weather.R;

public class WeatherListAdapter extends ArrayAdapter<WeatherSetter>{

    private Context context;
    private List<WeatherSetter> weatherList;

    public WeatherListAdapter(Context context, List<WeatherSetter> weatherList){
        super(context, R.layout.tomorrow_weather_list_layout, weatherList);
        this.context = context;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tomorrow_weather_list_layout, parent, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.weatherIconTomorrow);
        imageView.setImageResource(weatherList.get(position).getWeatherPhoto());

        TextView textViewDate = (TextView)view.findViewById(R.id.tomorrow_dateTime);
        textViewDate.setText(weatherList.get(position).getForecastDate());

        TextView textViewCondition = (TextView)view.findViewById(R.id.tomorrow_condition);
        textViewCondition.setText(weatherList.get(position).getCondition());

        TextView textViewTemperature = (TextView)view.findViewById(R.id.tomorrow_temperature);
        textViewTemperature.setText(String.valueOf(weatherList.get(position).getTemperature()));
        return view;
    }
}
