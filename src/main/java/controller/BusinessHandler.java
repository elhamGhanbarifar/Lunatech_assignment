package main.java.controller;
import main.java.DBEntity.Airport;
import main.java.model.ProjectDB;
import main.java.valueobject.AirportVO;
import main.java.valueobject.CountryAirportsVO;
import main.java.valueobject.SearchInfo;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BusinessHandler implements Serializable {
    final static Logger logger = Logger.getLogger(BusinessHandler.class);

    public BusinessHandler() {
    }

    public CountryAirportsVO getCountryAirportsVO(SearchInfo searchInfo) throws ExceptionHandler {
        if ((searchInfo.getCountryCode() == null || searchInfo.getCountryCode().trim().equals("")) &&
                (searchInfo.getCountryName() == null || searchInfo.getCountryName().trim().equals(""))) {
            throw new ExceptionHandler(ExceptionHandler.INVALID_INPUT);
        }

        if (searchInfo.getCountryCode() != null) {
            if (searchInfo.getCountryCode().trim().equals(""))
                throw new ExceptionHandler(ExceptionHandler.INVALID_INPUT);

            return getCountryInfoByCode(searchInfo.getCountryCode());
        } else {
            if (searchInfo.getCountryName().trim().equals(""))
                throw new ExceptionHandler(ExceptionHandler.INVALID_INPUT);

            for (String cName : ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().keySet()) {
                if (cName.equalsIgnoreCase(searchInfo.getCountryName())) {
                    return getCountryInfoByCode(ProjectDB.getInstance().getCOUNTRY_CODE_BY_NAME().get(cName));
                }
            }
            throw new ExceptionHandler(ExceptionHandler.NOT_FOUND_COUNTRY_NAME);
        }
    }

    private CountryAirportsVO getCountryInfoByCode(String countryCode) throws ExceptionHandler {
        logger.debug(":: START getCountryInfoByCode() ::");
        CountryAirportsVO countryAirportsVO = new CountryAirportsVO();
        List<Airport> airportList;
        AirportVO airportVO;

        airportList = ProjectDB.getInstance().getAIRPORTS_BY_COUNTRY().get(countryCode);
        if (airportList == null || airportList.isEmpty()) {
            throw new ExceptionHandler(ExceptionHandler.NOT_FOUND_COUNTRY_CODE);
        }
        countryAirportsVO.setCountryCode(countryCode);
        countryAirportsVO.setCountryName(ProjectDB.getInstance().getCountryNameByCode(countryCode));
        for (Airport airport : airportList) {
            airportVO = new AirportVO();
            airportVO.setAirportName(airport.getName());
            airportVO.setIndex(airportList.indexOf(airport) + 1);
            if (ProjectDB.getInstance().getRUNWAYS_BY_AIRPORT().get(airport.getId()) != null)
                airportVO.setRunwayList(ProjectDB.getInstance().getRUNWAYS_BY_AIRPORT().get(airport.getId()));
            countryAirportsVO.getAirportVOList().add(airportVO);
        }

//        Collections.sort(countryAirportsVO.getAirportVOList(), new Comparator<AirportVO>() {
//            @Override
//            public int compare(AirportVO o1, AirportVO o2) {
//                String o1Name = o1.getAirportName().toUpperCase();
//                String o2Name = o2.getAirportName().toUpperCase();
//
//                return o1Name.compareTo(o2Name);
//            }
//        });
        logger.debug(":: END getCountryInfoByCode() ::");
        return countryAirportsVO;
    }

    public static List<AirportVO> getAirportVOListToDisplay(List<AirportVO> list, int offset, int noOfRecords) {
        List<AirportVO> airportVOList = new ArrayList<>();
        int last = offset + noOfRecords;
        if (list.size() < last)
            last = list.size();
        for (int i = offset; i < last; i++) {
            airportVOList.add(list.get(i));
        }
        return airportVOList;
    }
}
