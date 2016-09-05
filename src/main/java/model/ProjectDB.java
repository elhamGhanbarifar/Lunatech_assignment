package main.java.model;

import main.java.DBEntity.Airport;
import main.java.DBEntity.Country;
import main.java.DBEntity.Runway;
import main.java.controller.ExceptionHandler;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDB {
    final static Logger logger = Logger.getLogger(ProjectDB.class);

    private final Map<String, List<Airport>> AIRPORTS_BY_COUNTRY = new HashMap<>();
    private final Map<Long, List<Runway>> RUNWAYS_BY_AIRPORT = new HashMap<>();
    private final Map<String, String> COUNTRY_CODE_BY_NAME = new HashMap<>();

    private static ProjectDB projectDB = null;

    private ProjectDB() throws ExceptionHandler {
        logger.debug(" :: START initiating ProjectDB :: ");
        fillCountries();
        fillAirports();
        fillRunways();
    }

    public static ProjectDB getInstance() throws ExceptionHandler {
        if (ProjectDB.projectDB == null) {
            ProjectDB.projectDB = new ProjectDB();
        }

        return ProjectDB.projectDB;
    }

    private void fillCountries() throws ExceptionHandler {
        logger.debug(" :: START fillCountries :: ");
        try {
            Country country;
            String filePath = "F:/temp/countries.csv";
            CSVParser countries = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : countries) {
                country = new Country();
                country.setId(Long.parseLong(record.get("id")));
                country.setCode(record.get("code"));
                country.setName(record.get("name"));
                COUNTRY_CODE_BY_NAME.put(record.get("name"), record.get("code"));
            }

            countries.close();
            logger.debug(" :: END fillCountries :: ");
        } catch (FileNotFoundException e) {
            logger.error(e, e);
            throw new ExceptionHandler(ExceptionHandler.FILE_NOT_FOUND_EXCEPTION);
        } catch (IOException e) {
            logger.error(e, e);
            throw new ExceptionHandler(ExceptionHandler.READING_FILE_EXCEPTION);
        }
    }

    private void fillAirports() throws ExceptionHandler {
        logger.debug(" :: START fillAirports :: ");
        try {
            Airport airport;
            String filePath = "F:/temp/airports.csv";
            CSVParser airports = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : airports) {
                airport = new Airport();
                airport.setId(Long.parseLong(record.get("id")));
                airport.setName(record.get("name"));
                airport.setIso_country(record.get("iso_country"));
                airport.setIdent(record.get("ident"));
                airport.setType(record.get("type"));

                if (AIRPORTS_BY_COUNTRY.get(airport.getIso_country()) == null) {
                    List<Airport> airportList = new ArrayList<>();
                    airportList.add(airport);
                    AIRPORTS_BY_COUNTRY.put(airport.getIso_country(), airportList);
                } else {
                    AIRPORTS_BY_COUNTRY.get(airport.getIso_country()).add(airport);
                }
            }

            airports.close();
            logger.debug(" :: END fillAirports :: ");
        } catch (FileNotFoundException e) {
            logger.error(e, e);
            throw new ExceptionHandler(ExceptionHandler.FILE_NOT_FOUND_EXCEPTION);
        } catch (IOException e) {
            logger.error(e, e);
            throw new ExceptionHandler(ExceptionHandler.READING_FILE_EXCEPTION);
        }
    }

    private void fillRunways() throws ExceptionHandler {
        logger.debug(" :: START fillRunways :: ");
        try {
            Runway runway;
            String filePath = "F:/temp/runways.csv";
            CSVParser runways = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : runways) {
                runway = new Runway();
                runway.setId(Long.parseLong(record.get("id")));
                runway.setAirport_ref(Long.parseLong(record.get("airport_ref")));
                runway.setSurface(record.get("surface"));
                if (RUNWAYS_BY_AIRPORT.get(runway.getAirport_ref()) == null) {
                    List<Runway> runwayList = new ArrayList<>();
                    runwayList.add(runway);
                    RUNWAYS_BY_AIRPORT.put(runway.getAirport_ref(), runwayList);
                } else {
                    RUNWAYS_BY_AIRPORT.get(runway.getAirport_ref()).add(runway);
                }
            }

            runways.close();
            logger.debug(" :: END fillRunways :: ");

        } catch (FileNotFoundException e) {
            logger.error(e, e);
            throw new ExceptionHandler(ExceptionHandler.FILE_NOT_FOUND_EXCEPTION);
        } catch (IOException e) {
            logger.error(e, e);
            throw new ExceptionHandler(ExceptionHandler.READING_FILE_EXCEPTION);
        }
    }

    public String getCountryNameByCode(String countryCode) {
        for (String cName : getCOUNTRY_CODE_BY_NAME().keySet()) {
            if (COUNTRY_CODE_BY_NAME.get(cName).equals(countryCode))
                return cName;
        }
        return "";
    }

    public Map<String, List<Airport>> getAIRPORTS_BY_COUNTRY() {
        return AIRPORTS_BY_COUNTRY;
    }

    public Map<Long, List<Runway>> getRUNWAYS_BY_AIRPORT() {
        return RUNWAYS_BY_AIRPORT;
    }

    public Map<String, String> getCOUNTRY_CODE_BY_NAME() {
        return COUNTRY_CODE_BY_NAME;
    }
}
