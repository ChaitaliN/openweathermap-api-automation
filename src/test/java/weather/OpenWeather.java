package weather;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;

public class OpenWeather extends BaseWeatherSystem implements WeatherSystem {
    final String temperatureUnit = "metric";
    final String forecastCount = "40";
    final String openWeatherAppID = "cd5e418ce783d7f890d373905054cacc";

    public OpenWeather() {
    }

    public RequestSpecification forecastFor(String location) {
        // Prepare request
        return given().queryParam("q", location).queryParam("units", temperatureUnit).queryParam("cnt", forecastCount)
                .queryParam("appid", openWeatherAppID);
    }

    public Response getForecast(RequestSpecification request) {
        // Trigger get request
        return request.when().get();
    }

    public List<Double> forecastForDay(Response response, String day) {
        // Filter out response based on week day

        String weekDay = day.toUpperCase();
        List<Double> temps = new ArrayList<Double>();

        JsonPath jp = response.jsonPath();
        List<Map<String, Map<String, String>>> dates = jp.getList("list");

        for (Map<String, Map<String, String>> d : dates) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            LocalDate dateTime = LocalDate.parse(String.valueOf(d.get("dt_txt")), formatter);

            if (weekDay.equals(dateTime.getDayOfWeek().toString())) {
                temps.add(Double.valueOf(String.valueOf(d.get("main").get("temp"))));
            }
        }
        return temps;
    }

    public Double averageTemperature(List<Double> allTemperatures) {
        // Get average of all temperatures for a day
        return allTemperatures.stream().mapToDouble(val -> val).average().orElse(Double.NaN);
    }
}
