# Solution

## Task 2

<b>Pre-conditions: </b><br>
- The input for `numCourses` should be an integer representing the number of courses a student must complete.
  It must hold: 1 <= `numCourses` <= 64.
- The input for `prerequisites ` should be a two-dimensional array of integers storing course pairs `[i, j]`,
where the course `j` needs to be completed as a prerequisite for `i`.
  - It must hold: Length of `prerequisites` pairs = 2.
  - It must hold: `0 <= i < numCourses` and `0 <= j < numCourses`.
  - It must hold: `0 <= prerequisites.length <= 64`.

As per the readme, there can be duplicates in the prerequisites array, a course can be a prerequisite for multiple courses, and
the prerequisites array can be empty. It can also be that there are less prerequisites than courses. So we didn't
add any checks for these cases.  


<b>Post-conditions: </b><br>
- The output should be a boolean declaring whether a degree can be finished (whether there is no cycle).

<b>Invariants: </b><br>
No meaningful invariants have been found.


## Task 3

We needed to make the methods in the `CourseSchedule` class static to be able to test them.

We implemented the following tests:

- `testCycle`: For a course schedule with 2 courses and a cycle in the prerequisites, we expect `false` as output.
- `testNoCycle`: For a course schedule with 3 courses and no cycle in the prerequisites, we expect `true` as output.
- `testVisitedMultipleTimes`: For a course schedule with 3 courses, no cycle but a duplicate prerequisite, we expect `true` as output.

## Task 1

After executing the tests from task 3, JaCoCo reports 100% line coverage in the `CourseSchedule` class.

## Task 4

We chose not to implement property-based tests for `CourseSchedule`, since it's very hard to generate random
prerequisites that don't contain any cycles, as complex constraints would be needed. We think for this problem
the testing methods we implemented are sufficient already.

## Bugs

No bugs were found in this implementation of the `CourseSchedule` functions.