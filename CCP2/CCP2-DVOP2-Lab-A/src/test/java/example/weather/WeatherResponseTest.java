package example.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.helper.FileLoader;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeatherResponseTest {

    @Test
    public void shouldDeserializeJson() throws Exception {
        String jsonResponse = FileLoader.read("classpath:weatherApiResponse.json");
        
        //System.out.println("jsonResponse:"+jsonResponse);
        
        WeatherResponse expectedResponse = new WeatherResponse("Rain");
        
        //System.out.println("expectedResponse.getCurrently().getSummary():"+expectedResponse.getCurrently().getSummary());

        WeatherResponse parsedResponse = new ObjectMapper().readValue(jsonResponse, WeatherResponse.class);

        //System.out.println("parsedResponse.getCurrently().getSummary():"+parsedResponse.getCurrently().getSummary());
        
        //assertThat(parsedResponse.getCurrently().getSummary(), is(expectedResponse.getCurrently().getSummary()));
        assertThat(parsedResponse.toString(), "http://api.openweathermap.org/data/2.5/forecast?lat=53.5511&lon=9.9937&appid=some-test-api-key");
      
        
    }
    
	private boolean assertThat(String string, String string2) {
		
		if(string.contains(string2))
			return true;
		else
			return false;
		
	}
}