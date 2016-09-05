package main.java.valueobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CountryAirportsVO implements Serializable {
    private String countryCode;
    private String countryName;
    private List<AirportVO> airportVOList;

    public CountryAirportsVO() {
        airportVOList = new ArrayList<>();
    }

    public CountryAirportsVO(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        airportVOList = new ArrayList<>();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<AirportVO> getAirportVOList() {
        return airportVOList;
    }

    public void setAirportVOList(List<AirportVO> airportVOList) {
        this.airportVOList = airportVOList;
    }
}
