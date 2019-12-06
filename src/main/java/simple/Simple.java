package simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;


public class Simple {
	static String filepath = "C:\\Users\\abisola.akinrinade\\Documents\\workspace\\simpleimplementation\\src\\main\\resources\\timestamps.csv";
    private static HashMap thresholds;
    private conveyorThresholds t_time;

    private Double lower;

    public static final void main(String[] args) {

            try {
                // load up the knowledge base

                // Load facts
                Double t_time = 2000.00;

                HashMap newread = new HashMap();
                newread.put("time", 1522345354.0);
                newread.put("t_time", 2000.0);
                newread.put("conveyor_name", "ConveyorA");
                TransportTime newtransport = new TransportTime();
                newtransport.setvalues(newread);
                String response = makepost.main("1522345354", "2000", "ConveyorA");

                thresholds = new ObjectMapper().readValue(response, HashMap.class);
                conveyorThresholds newthresholds = new conveyorThresholds();
                newthresholds.setvalues(thresholds);
                KieServices ks = KieServices.Factory.get();
                KieContainer kContainer = ks.getKieClasspathContainer();
                KieSession kSession = kContainer.newKieSession("ksession-rules");


                System.out.print("Response from webservice call ="+response+"");
                System.out.print("\n Result Hashmap object ="+thresholds+"");
;
                kSession.insert(newtransport);
                kSession.fireAllRules();

                creatrules.Drl_Creator.create_drl(thresholds);


            } catch (Throwable t) {
                t.printStackTrace();
            }

    }

}

