# Solution MovieRatings

## Task A.

### Answers to questions

>What are the external dependencies? Which of these dependencies should be tested using doubles and which should not? Explain your rationale.

In the `MovieRatings` class, the external dependences are:
* `Movie`: This class represents a movie object/entity. As mentioned in the book, (small) entities should not be mocked.
Instead, we instantiate `Movie` objects with the constructor and use them in the tests.
* `DatabaseConnection`: This class is used to connect to the SQL database and pass the connection to `MovieRatingsFetcher`.
It should be mocked, because it communicates with external infrastructure (a SQL database). This can make testing slow or 
non-deterministic otherwise. It could also be infeasible to set up a DB for testing. Furthermore, `MovieRatingsFetcher`
requires a `DatabaseConnection` object in its constructor, so we can inject a mock of `DatabaseConnection` into it.
* `MovieRatingsFetcher`: This class fetches the movie data from a SQL database, using the `DatabaseConnection` object.
It should be mocked, because it depends on `DatabaseConnection`, which is an external dependency. The same reasons as in
the previous point apply here.

>For the dependencies that should be tested using doubles, should the production code be refactored to make it possible?
If so, do the refactoring and implement the tests.

The first `MovieRatings` constructor, which takes a `MovieRatingsFetcher` as an argument, allows dependency injection. 
This means that in the tests, we can create a mock of `MovieRatingsFetcher` and pass it to that constructor of `MovieRatings`
to test the `topRatedMovies` method. This means that we don't have to refactor the code, because it can be
tested as it is.

>What are the disadvantages of using doubles in your tests? Answer with examples from the MovieRatings class.

xxx

### Unit tests

* `testTopRatedMoviesNormalCase`: Tests the happy case, where a list of movies with different ratings is given and
    the expected result is a select list of movies with a rating >= 4.
* `testTopRatedMoviesAllBelowRating4`: Tests the case, where a list of movies with all ratings < 4 is given, in which case
    an empty list is expected.
* `testTopRatedMoviesWithEmptyMovieList`: Tests the case, where an empty movie list is given, in which case an empty
list is expected as a result.

## Task B.

### TDD documentation

We started with writing unit tests for the new method:
* `testUniqueDirectors`: Tests the happy case, where a list of distinct movies with directors is given, some of which
directed multiple movies. The expected result is a list of unique directors.
* `testUniqueDirectorsSingleDirector`: Tests the case, where a list of movies with only one director is given,
    in which case the expected result is a list with that single director.
* `testUniqueDirectorsEmptyMovieList`: Tests the case, where an empty movie list is given, in which case an empty
    list of directors is expected as a result.
* `testUniqueDirectorsNullMovieList`: ???
* `testUniqueDirectorsWithNullDirector`: Tests the case, where a list of movies with director is given, where one of
them is null. The expected result is a list of all the other (valid) directors.

The tests failed, as expected.

For the subsequent implementation of `uniqueDirectors` in `MovieRatings`, we took inspiration from the `topRatedMovies`
method. 


xxx

After that, we ran the tests again and they all passed. In a next step of the TDD process, the implementation and test
code should be refactored. In our case, we didn't have to refactor the code, because it was already clean, readable and
extensible.

### Answers to questions

>What are the external dependencies? Which of these dependencies should be tested using doubles and which should not? Explain your rationale.



>For the dependencies that should be tested using doubles, should the production code be refactored to make it possible? If so, do the refactoring and implement the tests.




>What are the disadvantages of using doubles in your tests? Answer with examples from the EmailNotificationSystem class.

