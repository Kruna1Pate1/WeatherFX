package controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller of Main.xml 
 */

public class MainController {

    WeatherData data = new WeatherData();
    String city;
    @FXML
    private Button fetchDataBtn;

    @FXML
    private TextField cityTextField;

    @FXML
    public void fetchData(ActionEvent e) throws IOException, InterruptedException, ParseException {

        city = cityTextField.getText();

        data = WeatherData.getData(city);
        System.out.println("City :" + data.getCity());
        System.out.println("Temprature :" + data.getTemp());
        System.out.println("Type :" + data.getWeatherType());
        System.out.println("Description :" + data.getDescription());
        System.out.println("Code :" + data.getCondition());

    }
}