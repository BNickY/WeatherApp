package data;

import grsu.bnicky.by.weather.R;

public class ImageSetter {
    public static int setWeatherImage(String name) {
        if (name.equals("01d")) {
            return R.drawable.classic_background_sun_day_bg;
        }
        if (name.equals("01n")) {
            return R.drawable.classic_background_sun_night_bg;
        }
        if (name.equals("02d")) {
            return R.drawable.classic_background_cloudy_day_bg;
        }
        if (name.equals("02n")) {
            return R.drawable.classic_background_cloudy_night_bg;
        }
        if (name.equals("03d")) {
            return R.drawable.classic_background_overcast_day_bg;
        }
        if (name.equals("03n")) {
            return R.drawable.classic_background_overcast_night_bg;
        }
        if (name.equals("04d")) {
            return R.drawable.classic_background_overcast_day_bg;
        }
        if (name.equals("04n")) {
            return R.drawable.classic_background_overcast_night_bg;
        }
        if (name.equals("09d")) {
            return R.drawable.classic_background_rainy_day_bg;
        }
        if (name.equals("09n")) {
            return R.drawable.classic_background_rainy_night_bg;
        }
        if (name.equals("10d")) {
            return R.drawable.classic_background_rainy_day_bg;
        }
        if (name.equals("10n")) {
            return R.drawable.classic_background_rainy_night_bg;
        }
        if (name.equals("11d")) {
            return R.drawable.classic_background_thunderstorm_day_bg2;
        }
        if (name.equals("11n")) {
            return R.drawable.classic_background_thunderstorm_night_bg2;
        }
        if (name.equals("13d")) {
            return R.drawable.classic_background_snowy_day_bg;
        }
        if (name.equals("13n")) {
            return R.drawable.classic_background_snowy_night_bg;
        }
        if (name.equals("50d")) {
            return R.drawable.classic_background_foggy_day_bg;
        }
        if (name.equals("50n")) {
            return R.drawable.classic_background_foggy_night_bg;
        }
        return 0;
    }
}
