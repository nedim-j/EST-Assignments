# SatelliteLaunching solution

## A. Number of invocations

To test whether `onSatelliteLaunched` was called for each listener, we created two unit tests.

- `testPublishSatelliteLaunched`: Tests behaviour with one listener.
- `testPublishSatelliteLaunched_many`: Tests behaviour with four listeners.

In both cases, the method `onSatelliteLaunched` was called the correct amount of times in each listener.

## B. Content of invocations - `ArgumentCaptor`

One test case was added.

- `testPublishSatelliteLaunched_argumentCaptor`: Tests whether the correct LaunchEvent was passed to the 
  `onSatelliteLaunched` method.

The test case successfully asserted correct behaviour.

## C. Content of invocations - Increasing observability

There is no `MissionEventProcessor` class.

## D. Advantages

These techniques extend the observability of our classes without us having to extend the code itself. Since they are 
easy to use, it also doesn't take long to set up a spy or an `ArgumentCaptor`, which keeps writing tests simple.