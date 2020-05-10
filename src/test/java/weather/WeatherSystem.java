package weather;

import java.util.List;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface WeatherSystem {
    public RequestSpecification forecastFor(String location);

    public Response getForecast(RequestSpecification request);

    public List<Double> forecastForDay(Response response, String day);

    public Double averageTemperature(List<Double> temps);
}
