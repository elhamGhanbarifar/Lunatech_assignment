package test.java;

import main.java.DBEntity.Airport;
import main.java.DBEntity.Runway;
import main.java.controller.ExceptionHandler;
import main.java.model.ProjectDB;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class Test4ProjectDB {

    @BeforeClass
    public static void getInstanceTest() throws ExceptionHandler {
        ProjectDB.getInstance();
    }

    @Test
    public void testGetCountryNameByCode() throws ExceptionHandler {
        assertTrue("testGetCountryNameByCode : ", ProjectDB.getInstance().getCountryNameByCode("").equals(""));
        assertTrue("testGetCountryNameByCode : ", ProjectDB.getInstance().getCountryNameByCode("CA").equals("Canada"));
        assertNotSame("testGetCountryNameByCode : ", ProjectDB.getInstance().getCountryNameByCode("CA"), ProjectDB.getInstance().getCountryNameByCode("FR"));
    }

    @Test
    public void testCountries() throws ExceptionHandler {
        assertNotNull("testCountries : ", ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME());
        assertEquals("testCountries : ", ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().entrySet().size(), 247);
        assertTrue("testCountries : ", ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().get("Canada").equals("CA"));
        assertTrue("testCountries : ", ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().get("China").equals("CN"));
        assertTrue("testCountries : ", ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().get("France").equals("FR"));
        assertNotSame("testCountries : ", ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().get("France"), ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().get("China"));
    }

    @Test
    public void testAirportsByCountryMap() throws ExceptionHandler {
        assertNotNull("testAirportsByCountryMap : ", ProjectDB.getInstance().getAIRPORTS_BY_COUNTRY());
        assertTrue("testAirportsByCountryMap : ", ProjectDB.getInstance().getAIRPORTS_BY_COUNTRY().get("US").size() == 21579);
        assertTrue("testAirportsByCountryMap : ", ProjectDB.getInstance().getAIRPORTS_BY_COUNTRY().get("GB").size() == 683);
        assertTrue("testAirportsByCountryMap : ", ProjectDB.getInstance().getAIRPORTS_BY_COUNTRY().get("SD").size() == 35);
        boolean allSame = true;
        for (Airport airport : ProjectDB.getInstance().getAIRPORTS_BY_COUNTRY().get("AD")) {
            if(!airport.getIso_country().equals("AD"))
                allSame = false;
        }
        assertTrue("testAirportsByCountryMap : ", allSame);
    }

    @Test
    public void testRunwaysByAirportMap() throws ExceptionHandler {
        assertNotNull("testRunwaysByAirportMap : ", ProjectDB.getInstance().getRUNWAYS_BY_AIRPORT());
        assertTrue("testRunwaysByAirportMap : ", ProjectDB.getInstance().getRUNWAYS_BY_AIRPORT().get(29114l).size() == 4);
        assertTrue("testRunwaysByAirportMap : ", ProjectDB.getInstance().getRUNWAYS_BY_AIRPORT().get(4398l).size() == 3);
        assertTrue("testRunwaysByAirportMap : ", ProjectDB.getInstance().getRUNWAYS_BY_AIRPORT().get(30035l).size() == 2);
        boolean allSame = true;
        for (Runway runway : ProjectDB.getInstance().getRUNWAYS_BY_AIRPORT().get(30035l)) {
            if(!runway.getAirport_ref().equals(30035l))
                allSame = false;
        }
        assertTrue("testRunwaysByAirportMap : ", allSame);
    }
}
