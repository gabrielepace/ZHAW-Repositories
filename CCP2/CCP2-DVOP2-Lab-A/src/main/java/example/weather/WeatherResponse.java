package example.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired; //new

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    	@Autowired private Currently currently; //new

    public WeatherResponse() {
    	
    }

    public WeatherResponse(String currentSummary) {
        this.currently = new Currently(currentSummary);
    }

    public Currently getCurrently() {
        return currently;
    }

    public String getSummary() {
    	System.out.println("currently!=null: "+ (currently!=null)); //new2
    	if(currently!=null) //new2
    		return currently.getSummary();
    	else //new2
    		return "Problems in obtaining the summary: see class \"WeatherResponse\"..";//new2
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherResponse response = (WeatherResponse) o;

        return currently != null ? currently.equals(response.currently) : response.currently == null;
    }

    @Override
    public int hashCode() {
        return currently != null ? currently.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "currently=" + currently +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currently {
        private String summary;

        public Currently() {}

        public Currently(String summary) {
            this.summary = summary;
        }

        public String getSummary() {
            return summary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Currently currently = (Currently) o;

            return summary != null ? summary.equals(currently.summary) : currently.summary == null;
        }

        @Override
        public int hashCode() {
            return summary != null ? summary.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "Currently{" +
                    "summary='" + summary + '\'' +
                    '}';
        }
    }
}
