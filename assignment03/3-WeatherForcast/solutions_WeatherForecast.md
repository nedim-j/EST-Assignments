# Solution WeeatherForecast

## Write unit tests for the getForecast method in the WeatherForecast:

We have stubbed both the `WeatherApiService` and `CacheService` for each test. They get instantiated beforeeach test to reduce duplicate code.
We have designed the following test cases:
- `returnsCachedForecast`: This test checks whether the program correctly returns a cached forecast if it exists in the `CacheService`. For this, we have stubbed `CacheService` to return a hardcoded `ForecastDetails` object, and then verified that the cacheService was called, but weatherApiService never.
- `retrievesApiWhenCacheMisses`: This test checks whether the program correctly returns a forecast from the `WeatherApiService` if it is not available in the `CacheService`. For this, we have stubbed `CacheService` to return null and `WeatherApiService` to return a hardcoded `ForecastDetails` object. We then verified that both the cacheService and weatherApiService were called.
- `returnsNullForNullCityName`: This test checks whether the program correctly returns null if the city name is null. For this, we have stubbed `CacheService` and `WeatherApiService` to return null. We then verified that both the cacheService and weatherApiService were never called.
- `returnsNullForEmptyCityName`: This test checks whether the program correctly returns null if the city name is empty. For this, we have stubbed `CacheService` and `WeatherApiService` to return null. We then verified that both the cacheService and weatherApiService were never called.
- `throwsRuntimeExceptionForApiFailure`: This test checks whether the program correctly throws a `RuntimeException` if the `WeatherApiService` fails (such as when the API is down). For this, we have stubbed `CacheService` to return null and `WeatherApiService` to throw a `RuntimeException`. We then verified that both the cacheService and weatherApiService were called.

## Test Coverage
Jacoco reports a 100% Line and Branch coverage for the `WeatherForecast` class.

## Additions
We have overwritten the `equals` method in the `ForecastDetails` class such that we can more easily compare the returned objects with our hardcoded examples.