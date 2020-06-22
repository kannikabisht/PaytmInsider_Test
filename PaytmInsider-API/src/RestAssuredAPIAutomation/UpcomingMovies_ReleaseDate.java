package RestAssuredAPIAutomation;

import java.util.List;
import java.util.Date; 
import java.text.ParseException; 
import java.text.SimpleDateFormat;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpcomingMovies_ReleaseDate {

	public static void main(String[] args) {
		try {
			RestAssured.baseURI = "https://apiproxy.paytm.com/v2/movies/upcoming";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.request(Method.GET);
			
			int statusCode = response.getStatusCode();
			if (statusCode == 200) {	
				JsonPath jsonPathEvaluator = response.jsonPath();
				Date current = new Date();
				List<Movies> movies  = jsonPathEvaluator.getList("upcomingMovieData",Movies.class);
				for(Movies movie : movies) {
					SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
					if (movie.releaseDate != null) {
						Date reDate = dateFormatter.parse(movie.releaseDate);
						if(reDate.compareTo(current) > 0) {
							System.out.println("Pass: Release Date is correct for " + movie.movie_name);
						} else {
							System.out.println("FAIL: Release Date is before Today's Date for " + movie.movie_name);
						}
					}
				}
				
			} else {
				System.out.println("FAIL: HTTP Request to the Server Failed.");
			}
		} catch (ParseException e) {
			System.out.println("FAIL: Release Date cannot be parsed");
		} catch (Exception e) {
			System.out.println("Something went wrong. Details: " + e.toString());
		}
	}

}

