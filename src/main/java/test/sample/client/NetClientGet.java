package test.sample.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NetClientGet {

	public static void main( String[] args ) {

		File tFile = new File("test.json");		
		try {

			URL url = new URL( "http://api.openweathermap.org/data/2.5/find?q=berlin&type=like&cnt=2" );
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if ( conn.getResponseCode() != 200 ) {
				throw new RuntimeException( "Failed : HTTP error code : " + conn.getResponseCode() );
			}

			BufferedReader br = new BufferedReader( new InputStreamReader( ( conn.getInputStream() ) ) );

			FileWriter fr = new FileWriter( tFile );
			BufferedWriter bw  = new BufferedWriter(fr);

			String output;
			System.out.println("Output from Server .... \n");
			while ( ( output = br.readLine() ) != null ) {
				System.out.println( output );
				bw.write( output );
			}			
			conn.disconnect();
			bw.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();			
		}

		// then parse the response
		System.out.println("-----------------");

		ObjectMapper mapper = new ObjectMapper();
		JsonFactory jfactory = mapper.getFactory();
		JsonParser jParser;
		try {
			jParser = jfactory.createParser( tFile );
			JsonNode node = mapper.readTree( jParser);
			int count = node.get("count").asInt();
			for ( int i = 0; i < count; i++ ) {
			  System.out.print( "City: " + node.get("list").get(i).get("name").asText() );
			  System.out.println( " , Absolute temperature: " + node.get("list").get(i).get("main").get("temp").asText() );
			}
			jParser.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	

		System.out.println("-----------------");

	}

}