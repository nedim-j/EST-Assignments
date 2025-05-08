import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherForecastTest {
  private WeatherApiService weatherApiService;
  private CacheService cacheService;
  private WeatherForecast weatherForecast;

  @BeforeEach
  void setup() {
    weatherApiService = mock(WeatherApiService.class);
    cacheService = mock(CacheService.class);
    weatherForecast = new WeatherForecast(weatherApiService, cacheService);
  }

  @Test
  void returnsCachedForecast() {
    ForecastDetails cached = new ForecastDetails("Zurich", new WeatherData("Zurich", "Sunny, 25C"));
    when(cacheService.getDetails("Zurich")).thenReturn(cached);

    ForecastDetails result = weatherForecast.getForecast("Zurich");

    assertEquals(cached, result);
    verify(cacheService, times(1)).getDetails("Zurich");
    verify(weatherApiService, times(0)).retrieveWeather("Zurich");
  }

  @Test
  void retrievesApiWhenCacheMisses() {
    WeatherData weatherData = new WeatherData("Zurich", "Sunny, 25C");
    ForecastDetails forecastDetails = new ForecastDetails("Zurich", weatherData);
    when(cacheService.getDetails("Zurich")).thenReturn(null);
    when(weatherApiService.retrieveWeather("Zurich")).thenReturn(weatherData);

    ForecastDetails result = weatherForecast.getForecast("Zurich");

    assertEquals(forecastDetails.getCityName(), result.getCityName());
    assertEquals(forecastDetails.getWeatherData(), result.getWeatherData());
    verify(cacheService, times(1)).getDetails("Zurich");
    verify(weatherApiService, times(1)).retrieveWeather("Zurich");
    verify(cacheService, times(1)).cacheDetails("Zurich", forecastDetails);
  }

  @Test
  void returnsNullForNullCityName() {
    ForecastDetails result = weatherForecast.getForecast(null);
    assertNull(result);
    verify(cacheService, times(0)).getDetails(anyString());
    verify(weatherApiService, times(0)).retrieveWeather(anyString());
  }

  @Test
  void returnsNullForEmptyCityName() {
    ForecastDetails result = weatherForecast.getForecast("");
    assertNull(result);
    verify(cacheService, times(0)).getDetails(anyString());
    verify(weatherApiService, times(0)).retrieveWeather(anyString());
  }

  @Test
  void throwsRuntimeExceptionForApiFailure() {
    when(cacheService.getDetails("Zurich")).thenReturn(null);
    when(weatherApiService.retrieveWeather("Zurich")).thenThrow(new RuntimeException("API failure"));

    assertThrows(RuntimeException.class, () -> weatherForecast.getForecast("Zurich"));
    verify(cacheService, times(1)).getDetails("Zurich");
    verify(weatherApiService, times(1)).retrieveWeather("Zurich");
  }
}
