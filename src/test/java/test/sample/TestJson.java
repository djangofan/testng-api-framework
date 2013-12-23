package test.sample;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class TestJson {
	
	public static File dataDir = new File("data");
	
	@BeforeClass
	public void verifyDir() {
		if ( !dataDir.exists() ) dataDir.mkdirs();
	}

	@Test
	public void testJson() {
		try {
			JsonFactory jfactory = new JsonFactory();
			JsonGenerator jGenerator = jfactory.createGenerator( new File( dataDir, "user.json"), JsonEncoding.UTF8 );
			jGenerator.useDefaultPrettyPrinter();
			jGenerator.writeStartObject(); // {
			jGenerator.writeObjectFieldStart("node"); // node: {
			jGenerator.writeStringField("type", "community"); // "type" : "community"
			jGenerator.writeObjectFieldStart("field_incentives"); // "field_incentives" : {
			jGenerator.writeFieldName("und"); // "und" :
			jGenerator.writeStartArray(); // [
			jGenerator.writeStartObject(); // {
			jGenerator.writeStringField("value", "fun"); // "value" : "fun"
			jGenerator.writeStringField("value", "nice"); // "value" : "nice"
			jGenerator.writeEndObject(); // }
			jGenerator.writeEndArray(); // ]
			jGenerator.writeEndObject(); // } end of field_incentives
			jGenerator.writeObjectFieldStart("field_community_email"); // "field_community_email" : {
			jGenerator.writeFieldName("und"); // "und" :
			jGenerator.writeStartArray(); // [
			jGenerator.writeStartObject(); // {
			jGenerator.writeStringField("value", "some@one.com"); // "value" : "fun"
			jGenerator.writeEndObject(); // }
			jGenerator.writeEndArray(); // ]
			jGenerator.writeEndObject(); // } end of field_community_email
			jGenerator.writeEndObject(); // } end of node
			jGenerator.writeEndObject(); // }
			jGenerator.close();		
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXml() {
		try {
			XmlFactory xfactory = new XmlFactory();
			ToXmlGenerator xGenerator = xfactory.createGenerator( new File( dataDir, "user.xml"), JsonEncoding.UTF8 );
			xGenerator.useDefaultPrettyPrinter();
			xGenerator.writeStartObject(); // {
			xGenerator.writeObjectFieldStart("node"); // node: {
			//xGenerator.writeStringField("type", "community"); // "type" : "community"
			//xGenerator.writeObjectFieldStart("field_incentives"); // "field_incentives" : {
			//xGenerator.writeFieldName("und"); // "und" :
			//xGenerator.writeStartArray(); // [
			//xGenerator.writeStartObject(); // {
			//xGenerator.writeStringField("value", "fun"); // "value" : "fun"
			//xGenerator.writeStringField("value", "nice"); // "value" : "nice"
			//xGenerator.writeEndObject(); // }
			//xGenerator.writeEndArray(); // ]
			//xGenerator.writeEndObject(); // } end of field_incentives
			//xGenerator.writeObjectFieldStart("field_community_email"); // "field_community_email" : {
			//xGenerator.writeFieldName("und"); // "und" :
			//xGenerator.writeStartArray(); // [
			//xGenerator.writeStartObject(); // {
			//xGenerator.writeStringField("value", "some@one.com"); // "value" : "fun"
			//xGenerator.writeEndObject(); // }
			//xGenerator.writeEndArray(); // ]
			//xGenerator.writeEndObject(); // } end of field_community_email
			xGenerator.writeEndObject(); // } end of node
			xGenerator.writeEndObject(); // }
			xGenerator.close();		
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
