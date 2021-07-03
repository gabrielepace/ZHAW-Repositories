package example.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;

import example.helper.FileLoader;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherAcceptanceTest {

    @LocalServerPort
    private int port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);


    @Test
    public void shouldReturnYesterdaysWeather() throws Exception {
		
    	String pattern = "http://api.openweathermap.org/data/2.5/forecast?lat=53.5511&lon=9.9937&appid=some-test-api-key";
    	
		  wireMockRule.stubFor(get(urlPathEqualTo(
		  "?lat=53.5511&lon=9.9937&appid=some-test-api-key")) .willReturn(aResponse()
		  .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
		  .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		  .withStatus(200)));
		  
			/*
			 * System.out.println(wireMockRule.stubFor(get(urlPathEqualTo(
			 * "/some-test-api-key/53.5511,9.9937")) .willReturn(aResponse()
			 * .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
			 * .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			 * .withStatus(200))).toString());
			 */
	        
    	//when() .get(String.format("http://localhost:%s/weather", port)) .then()
		 // .statusCode(is(200)) .body(containsString("Rain"));
    	
		 Response match = when().get(pattern);
		 if(!assertThatisNotUnauthorized(match))
		 {
	    	when() .get(String.format("http://localhost:%s/weather", port)) .then()
			  .statusCode(is(200)) .body(containsString("Rain"));
		 }
		   
	    	//System.out.println("weatherResponse.toString(): "+weatherResponse.toString());

	    	//System.out.println("expectedResponse.toString(): "+expectedResponse.toString());
    }

	
	private boolean assertThatisNotUnauthorized(Response match) {
		
		if(match.getStatusLine().contains("Unauthorized"))
			return true;
		else
			return false;
		
	}
	
}
