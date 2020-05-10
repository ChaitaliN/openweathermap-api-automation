package stepDefinition;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.collection.IsEmptyCollection;
import weather.OpenWeather;
import weather.WeatherSystem;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;

public class WeatherForecast {
    private RequestSpecification request;
    private Response response;
    public WeatherSystem weather;
    public List<Double> allTemperatures;

    @Before()
    public void setup() {
        weather = new OpenWeather();
    }

    @Given("^I like to holiday in \"([^\"]*)\"$")
    public void i_like_to_holiday_in(String location) throws Throwable {
        // Prepare request
        request = weather.forecastFor(location);
    }

    @When("^I look up the weather forecast$")
    public void i_look_up_the_weather_forecast() throws Throwable {
        // Trigger get request
        response = weather.getForecast(request);
    }

    @Then("^I receive the weather forecast$")
    public void i_receive_the_weather_forecast() throws Throwable {
        // Validate response status code
        response.then().statusCode(200);
    }

    @Then("^I only like to holiday on \"([^\"]*)\"$")
    public void i_only_like_to_holiday_on(String day) throws Throwable {
        // Filter out response based on week day
        allTemperatures = weather.forecastForDay(response, day);
        assertThat(allTemperatures, not(IsEmptyCollection.empty()));
    }

    @Then("^the temperature is warmer than \"([^\"]*)\" degrees$")
    public void the_temperature_is_warmer_than_degrees(String expectedTemperature) throws Throwable {
        // Get average of all temperatures for a day
        Double temperature = weather.averageTemperature(allTemperatures);
        assertThat(temperature, greaterThan(Double.valueOf(expectedTemperature)));
    }
}
