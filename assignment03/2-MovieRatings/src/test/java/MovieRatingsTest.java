import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

public class MovieRatingsTest {
    // Task A
    @Test
    void testTopRatedMoviesNormalCase() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection) {
            @Override
            public List<Movie> all() {
                return List.of(
                        new Movie("Amazing movie", "Director1", 5),
                        new Movie("Good movie", "Director1", 4),
                        new Movie("Average movie", "Director2", 3)
                );
            }
        };

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<Movie> topRatedMovies = movieRatings.topRatedMovies();

        List<Movie> expectedTopRatedMovies = List.of(
                new Movie("Amazing movie", "Director1", 5),
                new Movie("Good movie", "Director1", 4)
        );

        assertEquals(expectedTopRatedMovies.size(), topRatedMovies.size());

        for (int i = 0; i < expectedTopRatedMovies.size(); i++) {
            assertEquals(expectedTopRatedMovies.get(i).getTitle(), topRatedMovies.get(i).getTitle());
            assertEquals(expectedTopRatedMovies.get(i).getDirector(), topRatedMovies.get(i).getDirector());
            assertEquals(expectedTopRatedMovies.get(i).getRating(), topRatedMovies.get(i).getRating());
        }

        verify(mockDbConnection).close();
    }

    @Test
    void testTopRatedMoviesAllBelowRating4() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection) {
            @Override
            public List<Movie> all() {
                return List.of(
                        new Movie("Average movie", "Director2", 3),
                        new Movie("Below average movie", "Director3", 2),
                        new Movie("Bad movie", "Director4", 1)
                );
            }
        };

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<Movie> topRatedMovies = movieRatings.topRatedMovies();

        assertEquals(0, topRatedMovies.size());

        verify(mockDbConnection).close();
    }

    @Test
    void testTopRatedMoviesWithEmptyMovieList() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection) {
            @Override
            public List<Movie> all() {
                return List.of();
            }
        };

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<Movie> topRatedMovies = movieRatings.topRatedMovies();

        assertEquals(0, topRatedMovies.size());

        verify(mockDbConnection).close();
    }

    @Test
    void testTopRatedMoviesWithNullMovieList() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection);

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<Movie> topRatedMovies = movieRatings.topRatedMovies();

        assertEquals(0, topRatedMovies.size());

        verify(mockDbConnection).close();
    }

    // Task B
    @Test
    void testUniqueDirectors() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection) {
            @Override
            public List<Movie> all() {
                return List.of(
                        new Movie("Amazing movie", "Director1", 5),
                        new Movie("Average movie", "Director2", 3),
                        new Movie("Good movie", "Director1", 4),
                        new Movie("Bad movie", "Director3", 1)
                );
            }
        };

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<String> uniqueDirectors = movieRatings.uniqueDirectors();

        List<String> expectedUniqueDirectors = List.of("Director1", "Director2", "Director3");

        assertEquals(expectedUniqueDirectors.size(), uniqueDirectors.size());

        for (int i = 0; i < expectedUniqueDirectors.size(); i++) {
            assertEquals(expectedUniqueDirectors.get(i), uniqueDirectors.get(i));
        }

        verify(mockDbConnection).close();
    }

    @Test
    void testUniqueDirectorsSingleDirector() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection) {
            @Override
            public List<Movie> all() {
                return List.of(
                        new Movie("Amazing movie", "Director1", 5),
                        new Movie("Average movie", "Director1", 3),
                        new Movie("Good movie", "Director1", 4),
                        new Movie("Bad movie", "Director1", 1)
                );
            }
        };

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<String> uniqueDirectors = movieRatings.uniqueDirectors();

        assertEquals(1, uniqueDirectors.size());
        assertEquals("Director1", uniqueDirectors.get(0));

        verify(mockDbConnection).close();
    }

    @Test
    void testUniqueDirectorsEmptyMovieList() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection) {
            @Override
            public List<Movie> all() {
                return List.of();
            }
        };

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<String> uniqueDirectors = movieRatings.uniqueDirectors();

        assertEquals(0, uniqueDirectors.size());

        verify(mockDbConnection).close();
    }

//    @Test
//    void testUniqueDirectorsWithNullMovieList() {
//        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);
//
//        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection);
//
//        MovieRatings movieRatings = new MovieRatings(mockFetcher);
//        List<String> uniqueDirectors = movieRatings.uniqueDirectors();
//
//        assertEquals(0, uniqueDirectors.size());
//
//        verify(mockDbConnection).close();
//    }

    @Test
    void testUniqueDirectorsWithNullDirector() {
        DatabaseConnection mockDbConnection = mock(DatabaseConnection.class);

        MovieRatingsFetcher mockFetcher = new MovieRatingsFetcher(mockDbConnection) {
            @Override
            public List<Movie> all() {
                return List.of(
                        new Movie("Amazing movie", null, 5),
                        new Movie("Average movie", "Director2", 3),
                        new Movie("Bad movie", "Director3", 1)
                );
            }
        };

        MovieRatings movieRatings = new MovieRatings(mockFetcher);
        List<String> uniqueDirectors = movieRatings.uniqueDirectors();

        assertEquals(2, uniqueDirectors.size());
        assertEquals("Director2", uniqueDirectors.get(0));
        assertEquals("Director3", uniqueDirectors.get(1));

        verify(mockDbConnection).close();
    }

}
