package simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.FileInputStream;
import java.util.*;


public class Simple {

    private static HashMap thresholds;
    private conveyorThresholds t_time;

    private Double lower;

    public static final void main(String[] args) {

            try {
                // load up the knowledge base

                // Load facts


                HashMap newread = new HashMap();
                newread.put("time", 1522345354.0);
                newread.put("t_time", 2000.0);
                newread.put("conveyor_name", "ConveyorA");
//                newread.put("time", senttime);
//                newread.put("t_time", t_time);
//                newread.put("conveyor_name", conveyor_name);
//                String ttime= t_time.toString();
//                String sent_time = senttime.toString();
                TransportTime newtransport = new TransportTime();
                newtransport.setvalues(newread);
                String response = makepost.main("1522345354", "2000", "ConveyorA");
//                String response = makepost.main(sent_time, ttime, conveyor_name);
                System.out.print("Response from webservice call ="+response+"");
                thresholds = new ObjectMapper().readValue(response, HashMap.class);
                conveyorThresholds newthresholds = new conveyorThresholds();
                newthresholds.setvalues(thresholds);
//                KieServices ks = KieServices.Factory.get();
                KieServices kieServices = KieServices.Factory.get();
                KieFileSystem kfs = kieServices.newKieFileSystem();

                // for each DRL file, referenced by a plain old path name:
                FileInputStream fis = new FileInputStream( "/home/pi/IdeaProjects/predictive-manufacturing/src/main/resources/com/sample/rules/ConveyorA.drl" );
                kfs.write( "src/main/resources/simple.drl",
                        kieServices.getResources().newInputStreamResource( fis ) );

                KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
                Results results = kieBuilder.getResults();
                if( results.hasMessages( Message.Level.ERROR ) ){
                    System.out.println( results.getMessages() );
                    throw new IllegalStateException( "### errors ###" );
                }

                KieContainer kieContainer =
                        kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );

                KieBase kieBase = kieContainer.getKieBase();
                KieSession kSession = kieContainer.newKieSession();


//                KieContainer kContainer = ks.getKieClasspathContainer();
//                KieSession kSession = kContainer.newKieSession("ksession-rules");


                System.out.print("Response from webservice call ="+response+"");
                System.out.print("\n Result Hashmap object ="+thresholds+"");
                System.out.print("\n Result Hashmap object ="+newthresholds+"");
                kSession.insert(newtransport);
                kSession.fireAllRules();

                creatrules.Drl_Creator.create_drl(thresholds);


            } catch (Throwable t) {
                t.printStackTrace();
            }

    }
    public static HashMap getThresholds(){
        return thresholds;
    }

}

