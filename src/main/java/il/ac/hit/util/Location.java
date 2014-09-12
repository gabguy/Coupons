package il.ac.hit.util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * A utility class for calculating distances and ranges between two given
 * geographic coordinates
 * 
 * @author AGCoupons
 *
 */
public class Location {
	
	private static Logger log = LogManager.getRootLogger();
	
	/**
	 * A default constructor
	 */
	public Location()
	{
		BasicConfigurator.configure();
	}

	/**
	 * Implementation of Haversine formula
	 * @author AGCoupons
	 * http://en.wikipedia.org/wiki/Haversine_formula
	 *     
	 * @param firstLatitude - The latitude value of the first coordinate
     * @param firstLongitude - The longitude value of the first coordinate
     * @param secondLatitude - The latitude value of the second coordinate
     * @param secondLongitude - The longitude value of the second coordinate
	 * @return The distance between the two coordinates
	 */
    private static double calculateDistanceBetweenCoordinates(double firstLatitude, double firstLongitude, double secondLatitude, double secondLongitude)
    {
    	final int radius = 6371; // Radius of the earth   
        double distanceRoundUp = 0;
        
        //Calculation of distance
        double latitudeDistance = doubleToRadiansConvertor(secondLatitude-firstLatitude);
        double longitudeDistance = doubleToRadiansConvertor(secondLongitude-firstLongitude);
        
        //Using Haversine Formula
        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2) +
                   Math.cos(doubleToRadiansConvertor(firstLatitude)) * Math.cos(doubleToRadiansConvertor(secondLatitude)) * 
                   Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = radius * c;
         
        distanceRoundUp = Math.round(distance * 100.0) / 100.0;
        log.info("The distance between the first coordinate and the second coordinate is: " + distanceRoundUp + " Km \n");

        return distanceRoundUp;
	 }
	    
    /**
     * A method that converts double values to radians.
     * @param value to be converted to Radians
     * @return Converted value
     */
    private static double doubleToRadiansConvertor(Double value)
    {
        return value * Math.PI / 180;
    }
    
    /**
     * a method that checks whether the first 
     * and the second coordinates are in range.
     * @param firstLatitude - The latitude value of the first coordinate
     * @param firstLongitude - The longitude value of the first coordinate
     * @param secondLatitude - The latitude value of the second coordinate
     * @param secondLongitude - The longitude value of the second coordinate
     * @param range - The range to evaluate whether the coordinates are in range of each other
     * @return A boolean value that determines if the coordinates are in range
     */
    public static boolean isCoordinateInRange(double firstLatitude, double firstLongitude, double secondLatitude, double secondLongitude, double range)
    {
    	
        double distance = calculateDistanceBetweenCoordinates(firstLatitude, firstLongitude, secondLatitude, secondLongitude);
        
        if(range >= distance)
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }
}

