package simple;


import java.util.HashMap;

public class conveyorThresholds {

    public Double getUpper() {
        return upper;
    }

    public void setUpper(Double upper) {
        upper = upper;
    }

    public Double getLower() {
        return lower;
    }

    public void setLower(Double lower) {
    }

    //private String conveyor_name;
    private static Double  upper;
    private static Double lower;

    public void setvalues(HashMap values){
        lower= (Double)values.get("lower");
        upper= (Double)values.get("upper");

    }

}


