import java.util.ArrayList;
import java.util.List;

public class SatelliteRegistryManager implements EventListener {
    private List<LaunchEvent> launchEvents = new ArrayList<>();

    @Override
    public void onSatelliteLaunched(LaunchEvent event) {
        System.out.println("Updating Satellite Registry with ID: " + event.getSatelliteId() +
                " and Mission: " + event.getMissionName());
        // Imagine here you would update a real database

        launchEvents.add(event);
    }

    public List<LaunchEvent> getLaunchEvents() {
        return launchEvents;
    }
}
