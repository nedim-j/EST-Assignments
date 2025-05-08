import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class EventPublisherTest {
    EventPublisher eventPublisher = new EventPublisher();

    @Test
    public void testSubscribe() {
        SatelliteRegistryManager satelliteRegistryManager = new SatelliteRegistryManager();

        List<EventListener> expectedListeners = Arrays.asList(satelliteRegistryManager);

        List<EventListener> actualListeners = eventPublisher.getListeners();

        eventPublisher.subscribe(satelliteRegistryManager);

        assertEquals(expectedListeners, actualListeners);
    }

    @Test
    public void testPublishSatelliteLaunched() {
        SatelliteRegistryManager satelliteRegistryManager = Mockito.spy(new SatelliteRegistryManager());

        LaunchEvent launchEvent = new LaunchEvent("1", "Sputnik");

        eventPublisher.subscribe(satelliteRegistryManager);

        eventPublisher.publishSatelliteLaunched(launchEvent);

        verify(satelliteRegistryManager).onSatelliteLaunched(launchEvent);
    }
}

