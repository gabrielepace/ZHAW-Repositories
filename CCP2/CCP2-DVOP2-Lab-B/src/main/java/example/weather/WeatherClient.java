package example.weather;

import org.springframework.beans.factory.annotation.Autowired; //new
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Component
public class WeatherClient {

    private static final String LATITUDE = "53.5511";
    private static final String LONGITUDE = "9.9937";
    //private final RestTemplate restTemplate;//original
    @Autowired private final RestTemplate restTemplate;// new 
    private final String weatherServiceUrl;
    private final String weatherServiceApiKey;
    //temporary for debugging
    private  String url; //new

    @Autowired
    public WeatherClient(RestTemplate restTemplate,
                         @Value("${weather.url}")  String weatherServiceUrl,
                         @Value("${weather.api_secret}")  String weatherServiceApiKey) {
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
        this.weatherServiceApiKey = weatherServiceApiKey;
    }

    public Optional<WeatherResponse> fetchWeather() {
        //temporary for debugging
    	//String url = String.format("%s/%s/%s,%s", weatherServiceUrl, weatherServiceApiKey, LATITUDE, LONGITUDE); //original
    	//this.url = String.format("%s/%s/%s,%s", weatherServiceUrl, weatherServiceApiKey, LATITUDE, LONGITUDE); //new
    	//this.url = String.format("%s/%s/%s,%s", weatherServiceUrl, weatherServiceApiKey, LATITUDE, LONGITUDE); //new
    	this.url = String.format("%s%s",weatherServiceUrl, "?lat="+LATITUDE+"&lon="+LONGITUDE+"&appid="+weatherServiceApiKey);// new2

        try {
            //return Optional.ofNullable(restTemplate.getForObject(url, WeatherResponse.class)); //original
        	Optional optionalValue= Optional.ofNullable(restTemplate.getForObject(url, WeatherResponse.class)); //new2 
        	System.out.println("3. optionalValue.toString(): "+optionalValue.toString());//new2 
        	RestTemplate rest = new RestTemplate();
            ResponseEntity<String> response = rest.getForEntity(url, String.class);

        	return Optional.ofNullable(new WeatherResponse(response.getBody()));//new2
        	//return optionalValue; //other option - new2
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
    

    public String getUrl() {
		return url;
	}

	@Override
    public String toString() {
        return "WeatherClient{" +
                "weatherServiceUrl='" + weatherServiceUrl + '\'' +
                ", weatherServiceApiKey='" + weatherServiceApiKey + '\'' +
                ", url='" + url + '\'' + //new
                '}';
    }
	
	
	
}
