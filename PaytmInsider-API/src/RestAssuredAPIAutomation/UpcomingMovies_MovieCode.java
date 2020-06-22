package RestAssuredAPIAutomation;

import java.util.List;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpcomingMovies_MovieCode {

	public static void main(String[] args) {
		try {
			RestAssured.baseURI = "https://apiproxy.paytm.com/v2/movies/upcoming";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.request(Method.GET);
			
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {	
				JsonPath jsonPathEvaluator = response.jsonPath();
				List<String> codes = jsonPathEvaluator.get("upcomingMovieData.paytmMovieCode");
				List<Movies> movies  = jsonPathEvaluator.getList("upcomingMovieData",Movies.class);
				for(Movies movie : movies) {
					codes.remove(movie.paytmMovieCode);
					if (codes.contains(movie.paytmMovieCode)) {
						System.out.println("FAIL: Movie has duplicate code " + movie.movie_name);
					} else {
						System.out.println("Pass: Movie code is unique " + movie.movie_name);
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
