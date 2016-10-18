package org.oaci.oaci;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class OaciRessources {
	private List<Map<String, String>> oaciMap = new LinkedList<Map<String, String>>();
	private Map<String, Map<String, String>> oaciDic = new HashMap<String, Map<String, String>>();

	private static OaciRessources instance = null;
	
	private OaciRessources() {
		super();
		try {
			readRessources();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static OaciRessources getInstance(){
		if (instance == null) {
			instance = new OaciRessources();
		}
		return instance;
	}

	private void readRessources() throws JsonProcessingException, IOException {
		// http://openflights.org/data.html
		readOaciDatabase(Utilities.getFile("Airports.dat"));
	}

	private void readOaciDatabase(final File file) throws JsonProcessingException, IOException {
		// Source: https://github.com/FasterXML/jackson-dataformat-csv
		// Examples: http://www.programcreek.com/java-api-examples/index.php?api=com.fasterxml.jackson.dataformat.csv.CsvMapper
		//List<Map<String, String>> response = new LinkedList<Map<String, String>>();
		CsvMapper mapper = new CsvMapper();
		//CsvSchema schema = CsvSchema.emptySchema().withHeader();
		CsvSchema schema = CsvSchema.builder()
		        .addColumn("AirportID", CsvSchema.ColumnType.NUMBER)
		        .addColumn("Name", CsvSchema.ColumnType.STRING)
		        .addColumn("City", CsvSchema.ColumnType.STRING)
		        .addColumn("Country", CsvSchema.ColumnType.STRING)
		        .addColumn("IATA/FAA", CsvSchema.ColumnType.STRING)
		        .addColumn("ICAO", CsvSchema.ColumnType.STRING)
		        .addColumn("Latitude", CsvSchema.ColumnType.NUMBER)
		        .addColumn("Longitude", CsvSchema.ColumnType.NUMBER)
		        .addColumn("Altitude", CsvSchema.ColumnType.NUMBER)
		        .addColumn("Timezone", CsvSchema.ColumnType.NUMBER)
		        .addColumn("DST", CsvSchema.ColumnType.STRING)
		        .addColumn("TzDatabaseTimeZone", CsvSchema.ColumnType.STRING)
		        .build();
		@SuppressWarnings("deprecation")
		MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class).with(schema.withoutHeader()).readValues(file);
		while (iterator.hasNext()) {
			Map<String, String> next = iterator.next();
			oaciMap.add(next);
			oaciDic.put(next.get("ICAO"), next);
		}
	}

	public List<Map<String, String>> getOaciMap() {
		return oaciMap;
	}

	public Map<String, Map<String, String>> getOaciDic() {
		return oaciDic;
	}
	
}

