package RestAssuredAPIAutomation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpcomingMovies_PosterURL {

	public static void main(String[] args) {
		try {
			RestAssured.baseURI = "https://apiproxy.paytm.com/v2/movies/upcoming";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.request(Method.GET);
			
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {	
				JsonPath jsonPathEvaluator = response.jsonPath();
				List<Movies> movies  = jsonPathEvaluator.getList("upcomingMovieData",Movies.class);
				for(Movies movie : movies) {
					if (movie.releaseDate != null) {
						if (movie.moviePosterUrl != null && movie.moviePosterUrl.contains(".jpg")) {
							System.out.println("Pass: Poster Url format is correct for " + movie.movie_name);
						} else {
							System.out.println("FAIL: Poster Url format is incorrect for " + movie.movie_name);
						}
					}
				}
			} else {
				System.out.println("FAIL: HTTP Request to the Server Failed.");
			}
		} catch (Exception e) {
			System.out.println("Something went wrong. Details: " + e.toString());
		}

	}

}
