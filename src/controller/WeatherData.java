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

public class WeatherData {

    private String mTemperature, mCity, mIcon, mWeatherType, mTemp, mDescription, mPressure, mHumidity;
    private int mCondition;

    public static WeatherData getData(String city) throws IOException, InterruptedException, ParseException {

        WeatherData weatherData = new WeatherData();
        JSONObject jObject = reqData(city);
        JSONObject tempJsonObject;

        try {
            weatherData.mCity = jObject.get("name").toString();

            JSONArray jsonArray = (JSONArray) jObject.get("weather");
            Iterator<JSONObject> iterator = jsonArray.iterator();
            tempJsonObject = iterator.next();
            weatherData.mCondition = Integer.parseInt(tempJsonObject.get("id").toString());
            weatherData.mWeatherType = tempJsonObject.get("main").toString();
            weatherData.mDescription = tempJsonObject.get("description").toString();

            tempJsonObject = (JSONObject) jObject.get("main");
            weatherData.mTemp = String.valueOf(Double.parseDouble(tempJsonObject.get("temp").toString()) - 273.15);
            weatherData.mPressure = tempJsonObject.get("pressure").toString();
            weatherData.mHumidity = tempJsonObject.get("humidity").toString();
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

    public int getCondition() {
        return mCondition;
    }

}
