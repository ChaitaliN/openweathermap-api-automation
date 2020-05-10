## OpenWeather API

- https://openweathermap.org/forecast5
- Forecast for 5 days for every 3 hours data
- Endpoint used https://api.openweathermap.org/data/2.5/forecast?q=Sydney&units=metric&appid=cd5e418ce783d7f890d373905054cacc

### STEPS

1. Pass city to OpenWeather API
2. Get weather forecast
3. Filter out response by expectedDay in week
4. Match expectedTemp in filtered out response

## NOTE

- OpenWeather API doesn't accept week day in query string, so had to filter out response by week day
- https://openweathermap.org/forecast16 is only for paid members, so used https://openweathermap.org/forecast5 instead
