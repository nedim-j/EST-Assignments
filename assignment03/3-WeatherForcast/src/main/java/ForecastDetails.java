import java.util.Objects;

public class ForecastDetails {
    private String cityName;
    private WeatherData weatherData;

    // Constructor
    public ForecastDetails(String cityName, WeatherData weatherData) {
        this.cityName = cityName;
        this.weatherData = weatherData;
    }

    public String getCityName() {
        return cityName;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForecastDetails)) return false;

        ForecastDetails that = (ForecastDetails) o;

        return Objects.equals(cityName, that.cityName) && Objects.equals(weatherData, that.weatherData);
    }
}
