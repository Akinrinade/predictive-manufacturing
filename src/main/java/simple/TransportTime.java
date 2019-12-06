package simple;

import java.util.HashMap;

public class TransportTime {
    private static String conveyor_name;
    private static Double time;

    public Double getT_time() {
        return t_time;
    }

    public void setT_time(Double t_time) { t_time = t_time;
    }

    public String getConveyor_name() {
        return conveyor_name;
    }

    public void setConveyor_name(String conveyor_name) {
        conveyor_name = conveyor_name;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        time = time;
    }

    private static Double t_time;

    public static void setvalues(HashMap newread){
        conveyor_name= (String) newread.get("conveyor_name");
        t_time= (Double) newread.get("t_time");
        time = (Double) newread.get("time");


    }
}
