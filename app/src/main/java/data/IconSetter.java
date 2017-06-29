package data;

import grsu.bnicky.by.weather.R;

public class IconSetter {
    public static int setWeatherIcon(String name){
        if(name.equals("01d")){
            return R.drawable.clearskyday;
        }
        if(name.equals("01n")){
            return R.drawable.clearskynight;
        }
        if(name.equals("02d")){
            return R.drawable.fewcloudsday;
        }
        if(name.equals("02n")){
            return R.drawable.fewcloudsnight;
        }
        if(name.equals("03d")){
            return R.drawable.scatteredcloudsday;
        }
        if(name.equals("03n")){
            return R.drawable.scatteredcloudsnight;
        }
        if(name.equals("04d")){
            return R.drawable.brokencloudsday;
        }
        if(name.equals("04n")){
            return R.drawable.brokencloudsnight;
        }
        if(name.equals("09d")){
            return R.drawable.showerrainday;
        }
        if(name.equals("09n")){
            return R.drawable.showerrainnight;
        }
        if(name.equals("10d")){
            return R.drawable.rainday;
        }
        if(name.equals("10n")){
            return R.drawable.rainnight;
        }
        if(name.equals("11d")){
            return R.drawable.thunderstormday;
        }
        if(name.equals("11n")){
            return R.drawable.thunderstormnight;
        }
        if(name.equals("13d")){
            return R.drawable.snowday;
        }
        if(name.equals("13n")){
            return R.drawable.snownight;
        }
        if(name.equals("50d")){
            return R.drawable.mistday;
        }
        if(name.equals("50n")){
            return R.drawable.mistnight;
        }
        return 0;
    }
}
