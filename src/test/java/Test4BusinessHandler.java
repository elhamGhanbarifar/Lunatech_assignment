package test.java;

import main.java.controller.BusinessHandler;
import main.java.controller.ExceptionHandler;
import main.java.model.ProjectDB;
import main.java.valueobject.AirportVO;
import main.java.valueobject.CountryAirportsVO;
import main.java.valueobject.SearchInfo;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test4BusinessHandler {
    public static BusinessHandler bs;

    @BeforeClass
    public static void getInstanceTest() throws ExceptionHandler {
        ProjectDB.getInstance();
        bs = new BusinessHandler();
    }

    @Test
    public void testGetCountryAirportsVO() throws ExceptionHandler {
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setCountryCode("AD");
        CountryAirportsVO countryAirportsVO = bs.getCountryAirportsVO(searchInfo);
        assertEquals("testGetCountryAirportsVO : ", countryAirportsVO.getCountryCode(), "AD");
        assertEquals("testGetCountryAirportsVO : ", countryAirportsVO.getCountryName(), "Andorra");
        boolean allSame = true;
        for (AirportVO airportVO : countryAirportsVO.getAirportVOList()) {
            if(airportVO.getAirportName().equals("Cami Heliport"))
                if(!airportVO.getRunwayList().isEmpty())
                    allSame = false;
        }
        assertTrue("testGetCountryAirportsVO : ", allSame);

        searchInfo = new SearchInfo();
        searchInfo.setCountryName("Australia");
        countryAirportsVO = bs.getCountryAirportsVO(searchInfo);
        assertEquals("testGetCountryAirportsVO : ", countryAirportsVO.getCountryCode(), "AU");
        assertEquals("testGetCountryAirportsVO : ", countryAirportsVO.getCountryName(), "Australia");
        allSame = true;
        for (AirportVO airportVO : countryAirportsVO.getAirportVOList()) {
            if(airportVO.getAirportName().equals("Harrismith Airport"))
                if(airportVO.getRunwayList().size() != 2)
                    allSame = false;
        }
        assertTrue("testGetCountryAirportsVO : ", allSame);
    }

    @Test
    public void testGetAirportVOListToDisplay() throws ExceptionHandler {
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setCountryCode("AD");
        CountryAirportsVO countryAirportsVO = bs.getCountryAirportsVO(searchInfo);
        List<AirportVO> airportVOList = BusinessHandler.getAirportVOListToDisplay(countryAirportsVO.getAirportVOList(), 0, 2);
        assertTrue("testGetAirportVOListToDisplay : ", airportVOList.size() == 2);

        searchInfo = new SearchInfo();
        searchInfo.setCountryCode("AU");
        countryAirportsVO = bs.getCountryAirportsVO(searchInfo);
        airportVOList = BusinessHandler.getAirportVOListToDisplay(countryAirportsVO.getAirportVOList(), 1900, 20);
        assertTrue("testGetAirportVOListToDisplay : ", airportVOList.size() <= 20);
    }
}
