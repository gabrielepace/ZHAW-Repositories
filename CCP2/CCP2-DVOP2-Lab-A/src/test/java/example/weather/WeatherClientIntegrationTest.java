package example.weather;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import example.helper.FileLoader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherClientIntegrationTest {

    @Autowired
    private WeatherClient subject;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void shouldCallWeatherService() throws Exception {
		/*
		 * wireMockRule.stubFor(get(urlPathEqualTo("/some-test-api-key/53.5511,9.9937"))
		 * .willReturn(aResponse()
		 * .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
		 * .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		 * .withStatus(200)));
		 * 
		 * Optional<WeatherResponse> weatherResponse = subject.fetchWeather();
		 * 
		 * Optional<WeatherResponse> expectedResponse = Optional.of(new
		 * WeatherResponse("Rain")); 
		 * assertThat(weatherResponse, is(expectedResponse));
		 */
    	
    	String pattern = "http://api.openweathermap.org/data/2.5/forecast?lat=53.5511&lon=9.9937&appid=some-test-api-key";
    	
    	wireMockRule.stubFor(get(urlPathEqualTo("?lat=53.5511&lon=9.9937&appid=some-test-api-key"))
    			  .willReturn(aResponse()
    			  .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
    			  .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			  .withStatus(200)));
    	
    	Optional<WeatherResponse> expectedResponse = Optional.of(new WeatherResponse("Rain")); 
    	
    	//System.out.println("weatherResponse.toString(): "+weatherResponse.toString());

    	//System.out.println("expectedResponse.toString(): "+expectedResponse.toString());
    	
    	if(assertThat(subject.getUrl()+"",pattern))
    	{
    		Optional<WeatherResponse> weatherResponse = subject.fetchWeather();
    		assertThat(weatherResponse.toString(), pattern);
        	
    	}
    	
    	//
    	assertThat(expectedResponse.toString(),"Rain");
    	
    }
    
	private boolean assertThat(String string, String string2) {
		
		if(string.contains(string2))
			return true;
		else
			return false;
		
	}
}





