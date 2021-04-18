package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.image.Image;

public class WeatherData {

    private String mTemperature, mCity, mIcon, mWeatherType, mTemp, mDescription, mPressure, mHumidity, mFeelLike, mWeatherIcon,
            mRain, mWindSpeed;
    private int mCondition;

    public static WeatherData getData(String city) throws IOException, InterruptedException, ParseException {

        WeatherData weatherData = new WeatherData();
        JSONObject jObject = reqData(city);
        JSONObject tempJsonObject;
        Double tempDouble;

        try {
            weatherData.mCity = jObject.get("name").toString();

            JSONArray jsonArray = (JSONArray) jObject.get("weather");
            Iterator<JSONObject> iterator = jsonArray.iterator();
            tempJsonObject = iterator.next();
            weatherData.mCondition = Integer.parseInt(tempJsonObject.get("id").toString());
            weatherData.mWeatherType = tempJsonObject.get("main").toString();
            weatherData.mDescription = tempJsonObject.get("description").toString();

            tempJsonObject = (JSONObject) jObject.get("main");
            tempDouble = Double.parseDouble(tempJsonObject.get("temp").toString()) - 273.15;
            weatherData.mTemp = String.valueOf((int) Math.rint(tempDouble));
            weatherData.mPressure = tempJsonObject.get("pressure").toString();
            weatherData.mHumidity = tempJsonObject.get("humidity").toString();
            tempDouble = Double.parseDouble(tempJsonObject.get("feels_like").toString()) - 273.15;
            weatherData.mFeelLike = String.valueOf((int) Math.rint(tempDouble));

            // tempJsonObject = (JSONObject) jObject.get("rain"); //rain data not awailable temporary
            // weatherData.mRain = tempJsonObject.get("3h").toString();

            tempJsonObject = (JSONObject) jObject.get("wind");
            weatherData.mWindSpeed = tempJsonObject.get("speed").toString();

            weatherData.mWeatherIcon = updateWeatherIcon(weatherData.mCondition);
            return weatherData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject reqData(String city) throws IOException, InterruptedException, ParseException {

        final String API_KEY = "9c89c5ba8f538cb8edb73de06e7a643f";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return toJson(response.body());
    }

    private static JSONObject toJson(String s) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(s);

        System.out.println(jsonObject.get("name"));
        return jsonObject;
    }

    private static String updateWeatherIcon(int condition) {
        if (condition >= 0 && condition <= 300) {
            return "thunderstrom1";
        } else if (condition >= 300 && condition <= 500) {
            return "lightrain";
        } else if (condition >= 500 && condition <= 600) {
            return "shower";
        } else if (condition >= 600 && condition <= 700) {
            return "snow2";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition <= 800) {
            return "overcast";
        } else if (condition == 800) {
            return "clear_sky";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy";
        } else if (condition >= 900 && condition <= 902) {
            return "thunderstrom1";
        } else if (condition == 903) {
            return "snow1";
        } else if (condition == 904) {
            return "clear_sky";
        } else if (condition >= 905 && condition <= 1000) {
            return "thunderstrom2";
        }
        return "default01";
    }

    public String getTemperature() {
        return mTemperature;
    }

    public String getCity() {
        return mCity;
    }

    public String getIcon() {
        return mIcon;
    }

    public String getWeatherType() {
        return mWeatherType;
    }

    public String getTemp() {
        return mTemp;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPressure() {
        return mPressure;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public String getFeelLike() {
        return mFeelLike;
    }

    public String getRain() {
        return "3"; //rain data unawailable temporary
    }

    public String getWindSpeed() {
        return mWindSpeed;
    }

    public Image getWeatherIcon() {
        String imageUrl = getClass().getResource("../assets/" + mWeatherIcon + ".png").toString();
        return new Image(imageUrl);
    }
    public int getCondition() {
        return mCondition;
    }

}
