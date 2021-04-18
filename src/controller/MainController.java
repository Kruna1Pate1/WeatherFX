package controller;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller of Main.xml 
 */

public class MainController {

    WeatherData data = new WeatherData();
    String city;
    Image image;
    @FXML
    private Button fetchDataBtn;

    @FXML
    private TextField cityTextField;

    @FXML
    private ImageView conditionIcon;

    @FXML
    private Label cityLable, discriptionLable, tempLable, feelLable, rainChanceLable, windSpeedLable, humidityLable, pressureLable;

    private String cityL, discription, temp, feel, rainChance, windSpeed, humidity, pressure;
    @FXML
    public void fetchData(ActionEvent e) throws IOException, InterruptedException, ParseException {

        city = cityTextField.getText();

        data = WeatherData.getData(city);

        setData(data);

        conditionIcon.setImage(data.getWeatherIcon());
    }

    @FXML
    public void setData(WeatherData data) {

        cityL = data.getCity();
        discription = data.getDescription();
        temp = data.getTemp() + "° C";
        feel = " feels like " + data.getFeelLike() + "° C";
        rainChance = data.getRain() + "%";
        windSpeed = data.getWindSpeed() + " m/s";
        humidity = data.getHumidity() + "%";
        pressure = data.getPressure() + " hPa";

        cityLable.setText(cityL);
        discriptionLable.setText(discription);
        tempLable.setText(temp);
        feelLable.setText(feel);
        rainChanceLable.setText(rainChance);
        windSpeedLable.setText(windSpeed);
        humidityLable.setText(humidity);
        pressureLable.setText(pressure);
    }
}