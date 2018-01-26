package cn.org.findu;


import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by obaby on 2017/4/20.
 */

public class GPSCountryLocation {


    public static GPSCountryLocation gpsCountryLocation = null;

    private Rectangle[] regionRectangle;

    private Rectangle[] excludeRectangle;


    private void initArray(){
        regionRectangle = new Rectangle[]{
                new Rectangle().initrectangleWithCoord(49.220400,079.446200,42.889900,096.330000),
                new Rectangle().initrectangleWithCoord(54.141500,109.687200,39.374200,135.000200),
                new Rectangle().initrectangleWithCoord(42.889900,073.124600,29.529700,124.143255),
                new Rectangle().initrectangleWithCoord(29.529700,082.968400,26.718600 ,097.035200),
                new Rectangle().initrectangleWithCoord(29.529700 ,097.025300,20.414096,124.367395),
                new Rectangle().initrectangleWithCoord(20.414096,107.975793,17.871542,111.744104)
        };
        excludeRectangle = new Rectangle[]{
                new Rectangle().initrectangleWithCoord(25.398623 ,119.921265,21.785006,122.497559),
                new Rectangle().initrectangleWithCoord(22.284000 ,101.865200,20.098800 ,106.665000),
                new Rectangle().initrectangleWithCoord(21.542200 ,106.452500 ,20.487800 ,108.051000),
                new Rectangle().initrectangleWithCoord(55.817500 ,109.032300 ,50.325700 ,119.127000),
                new Rectangle().initrectangleWithCoord(55.817500 ,127.456800 ,49.557400 ,137.022700),
                new Rectangle().initrectangleWithCoord(44.892200 ,131.266200 ,42.569200 ,137.022700)
        };
    }

    private boolean isInRectangle(Rectangle rectangle, double lat, double longt){
        return rectangle.West <= longt && rectangle.East >= longt && rectangle.North >=lat && rectangle.South <= lat;
    }

    public boolean isInChina(double lat, double longt){
        for (Rectangle rectangle:regionRectangle){
            if (isInRectangle(rectangle,lat,longt)){
                for (Rectangle exRectangle:excludeRectangle){
                    if (isInRectangle(exRectangle,lat,longt)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static GPSCountryLocation shareInstance(){
        if (gpsCountryLocation == null){

            gpsCountryLocation = new GPSCountryLocation();
            gpsCountryLocation.initArray();

        }
        return gpsCountryLocation;
    }

    public class Rectangle{
        public double West;
        public double North;
        public double East;
        public double South;

        public Rectangle rectangle = null;
        public Rectangle initrectangleWithCoord(double lat1, double long1, double lat2, double long2){
            if (rectangle == null){
                rectangle = new Rectangle();
            }
            rectangle.West = min(long1, long2);
            rectangle.North = max(lat1, lat2);
            rectangle.East = max(long1, long2);
            rectangle.South = min(lat1, lat2);
            return rectangle;
        }
        public Rectangle(){
            super();
        }
    }
}
