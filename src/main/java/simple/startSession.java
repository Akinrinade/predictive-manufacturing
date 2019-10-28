package simple;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class startSession {
    public static HashMap<Conveyors, Double> newread = new HashMap<>();

    public static void startSession(HashMap<Conveyors, conveyorThresholds> average_time_A) {

        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");


            // Load facts
            while (true) {
                if  (!newread.isEmpty()){

                    ListIterator new_read= (ListIterator) newread.values().iterator();
                    Double newAverage = 34.0;
                    conveyorThresholds t_time = new conveyorThresholds();
                    t_time.setTtime(newAverage);
                    kSession.insert(t_time);
                    kSession.fireAllRules();
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }
}
