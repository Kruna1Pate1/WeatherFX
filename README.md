# ⛈ WeatherFX ⛈

## About WeatherFX

WeatherFx is created in JavaFX whcih peovide current weather for any city. It uses [openweathermap](http://openweathermap.org/ "OpenWeatherMap") api for fetching data.

---

## Installation from source

For installing from source with **vscode** follow below steps :

In cmd type 
```
git clone https://github.com/Kruna1Pate1/WeatherFX.git
code WeatherFx
```
In explorer panel at below in `Java Project` you will see `Refrenced Libraries`.

Click on `+` and add javaFX refrence libraries. For downloading them [visit here](https://gluonhq.com/products/javafx "JavaFX refrence Libraries")

Now you have to make some changes in `.vscode/launch.json` to able to run this.

Go to line **31** 

~~~
replace
"vmArgs": "--module-path \"D:/Program Files - D/JavaFXlib/javafx-sdk-11.0.2/lib\" --add-modules javafx.controls,javafx.fxml",

with
"vmArgs": "--module-path \"<your-refrence-lib-path>\" --add-modules javafx.controls,javafx.fxml",
~~~

---

## Preview

![Preview-1](https://github.com/Kruna1Pate1/WeatherFX/blob/main/src/assets/preview/WeatherFX-1.png "WeatherFX preview-1")

![Preview-2](https://github.com/Kruna1Pate1/WeatherFX/blob/main/src/assets/preview/WeatherFX-2.png "WeatherFX preview-2")
