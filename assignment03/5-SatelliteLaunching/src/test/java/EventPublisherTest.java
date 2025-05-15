import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
        SatelliteRegistryManager satelliteRegistryManager = Mockito.spy(new SatelliteRegistryManager());

        List<EventListener> expectedListeners = Arrays.asList(satelliteRegistryManager);

        eventPublisher.subscribe(satelliteRegistryManager);

        List<EventListener> actualListeners = eventPublisher.getListeners();

        assertEquals(expectedListeners, actualListeners);
    }

    @Test
    public void testSubscribe_many() {
        SatelliteRegistryManager satelliteRegistryManager1 = Mockito.spy(SatelliteRegistryManager.class);
        SatelliteRegistryManager satelliteRegistryManager2 = Mockito.spy(SatelliteRegistryManager.class);
        SatelliteRegistryManager satelliteRegistryManager3 = Mockito.spy(SatelliteRegistryManager.class);
        SatelliteRegistryManager satelliteRegistryManager4 = Mockito.spy(SatelliteRegistryManager.class);

        List<EventListener> expectedListeners = Arrays.asList(
                satelliteRegistryManager1,
                satelliteRegistryManager2,
                satelliteRegistryManager3,
                satelliteRegistryManager4
        );

        eventPublisher.subscribe(satelliteRegistryManager1);
        eventPublisher.subscribe(satelliteRegistryManager2);
        eventPublisher.subscribe(satelliteRegistryManager3);
        eventPublisher.subscribe(satelliteRegistryManager4);

        List<EventListener> actualListeners = eventPublisher.getListeners();

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

    @Test
    public void testPublishSatelliteLaunched_many() {
        SatelliteRegistryManager satelliteRegistryManager1 = Mockito.spy(SatelliteRegistryManager.class);
        SatelliteRegistryManager satelliteRegistryManager2 = Mockito.spy(SatelliteRegistryManager.class);
        SatelliteRegistryManager satelliteRegistryManager3 = Mockito.spy(SatelliteRegistryManager.class);
        SatelliteRegistryManager satelliteRegistryManager4 = Mockito.spy(SatelliteRegistryManager.class);

        LaunchEvent launchEvent = new LaunchEvent("1", "Sputnik");

        eventPublisher.subscribe(satelliteRegistryManager1);
        eventPublisher.subscribe(satelliteRegistryManager2);
        eventPublisher.subscribe(satelliteRegistryManager3);
        eventPublisher.subscribe(satelliteRegistryManager4);

        eventPublisher.publishSatelliteLaunched(launchEvent);

        verify(satelliteRegistryManager1).onSatelliteLaunched(launchEvent);
        verify(satelliteRegistryManager2).onSatelliteLaunched(launchEvent);
        verify(satelliteRegistryManager3).onSatelliteLaunched(launchEvent);
        verify(satelliteRegistryManager4).onSatelliteLaunched(launchEvent);
    }

    @Test
    public void testPublishSatelliteLaunched_argumentCaptor() {
        SatelliteRegistryManager satelliteRegistryManager = Mockito.spy(SatelliteRegistryManager.class);

        LaunchEvent launchEvent = new LaunchEvent("1", "Sputnik");

        eventPublisher.subscribe(satelliteRegistryManager);

        eventPublisher.publishSatelliteLaunched(launchEvent);

        ArgumentCaptor<LaunchEvent> launchEventArgumentCaptor = ArgumentCaptor.forClass(LaunchEvent.class);
        verify(satelliteRegistryManager).onSatelliteLaunched(launchEventArgumentCaptor.capture());
        LaunchEvent capturedLaunchEvent = launchEventArgumentCaptor.getValue();

        verify(satelliteRegistryManager).onSatelliteLaunched(launchEvent);
        assertEquals(launchEvent, capturedLaunchEvent);
    }

    @Test
    public void testPublishSatelliteLaunched_betterObservability() {
        SatelliteRegistryManager satelliteRegistryManager = Mockito.spy(SatelliteRegistryManager.class);

        LaunchEvent launchEvent = new LaunchEvent("1", "Sputnik");

        eventPublisher.subscribe(satelliteRegistryManager);

        eventPublisher.publishSatelliteLaunched(launchEvent);

        LaunchEvent savedLaunchEvent = satelliteRegistryManager.getLaunchEvents().get(0);

        verify(satelliteRegistryManager).onSatelliteLaunched(launchEvent);
        assertEquals(launchEvent, savedLaunchEvent);
    }
}

