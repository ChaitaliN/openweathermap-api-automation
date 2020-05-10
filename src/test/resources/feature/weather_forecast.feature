@weather-forecast
Feature: Weather forecast

  @weather-forecast-positive
  Scenario Outline: A happy holidaymaker

    Given I like to holiday in "<location>"
    When I look up the weather forecast
    Then I receive the weather forecast
    And I only like to holiday on "<expectedDay>"
    And the temperature is warmer than "<expectedTemp>" degrees

    Examples:
      |location |expectedDay  |expectedTemp |
      |Sydney   |Thursday     |10           |
