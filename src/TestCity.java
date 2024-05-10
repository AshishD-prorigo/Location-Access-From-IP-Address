import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Continent;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

public class TestCity {
	public static final String language = "en";
	
	public static void main(String args[]) throws GeoIp2Exception , IOException {
		
		File database = new File("C:\\Users\\ashishd\\Downloads\\GeoLite2-City.mmdb");

		DatabaseReader reader = new DatabaseReader.Builder(database).build();

		// 202.174.120.125 HYD
		// 152.58.31.191 PUNE
		InetAddress ipAddress = InetAddress.getByName("59.91.165.54");
		 

		CityResponse response = reader.city(ipAddress);
		
		List<Subdivision> subdivision = response.getSubdivisions();
		
		for (Subdivision state : subdivision) {
			if(state != null && state.getNames() != null) {
				for(Map.Entry<String, String> key : state.getNames().entrySet()) {
					if(key.getKey().equalsIgnoreCase(language)) {
						System.out.println("State Name : "+key.getValue());
					}
				}
			}
		}
		
		Postal postal = response.getPostal();
		System.out.println("Postal Code : "+ postal.getCode());
		
		Location location = response.getLocation();
		if(location != null ) {
			System.out.println("Longitude : "+location.getLongitude());
			System.out.println("Latitude : "+location.getLatitude());
			System.out.println("TimeZone : "+location.getTimeZone());
		}
		
		
		City city = response.getCity();
		if(city != null && city.getNames() != null) {
			for(Map.Entry<String, String> key : city.getNames().entrySet()) {
				if(key.getKey().equalsIgnoreCase(language)) {
					System.out.println("City Name : "+key.getValue());
				}
			}
		}
		
		Country country = response.getCountry();
		if(country != null && country.getNames() != null) {
			for(Map.Entry<String, String> key : country.getNames().entrySet()) {
				if(key.getKey().equalsIgnoreCase(language)) {
					System.out.println("Country Name : "+key.getValue());
				}
			}
		}
		
		Continent continent = response.getContinent();
		if(continent != null && continent.getNames() != null) {
			for(Map.Entry<String, String> key : continent.getNames().entrySet()) {
				if(key.getKey().equalsIgnoreCase(language)) {
					System.out.println("continent Name : "+key.getValue());
				}
			}
		}

	}

}
