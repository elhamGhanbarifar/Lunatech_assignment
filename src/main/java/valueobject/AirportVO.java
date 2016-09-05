package main.java.valueobject;

import main.java.DBEntity.Runway;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AirportVO implements Serializable {
    private String airportName;
    private List<Runway> runwayList;
    private int index;

    public AirportVO() {
        this.runwayList = new ArrayList<>();
    }

    public AirportVO(String airportName) {
        this.runwayList = new ArrayList<>();
        this.airportName = airportName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public List<Runway> getRunwayList() {
        return runwayList;
    }

    public void setRunwayList(List<Runway> runwayList) {
        this.runwayList = runwayList;
    }
}
