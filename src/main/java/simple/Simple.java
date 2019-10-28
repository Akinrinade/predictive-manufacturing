package simple;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;


public class Simple {
	static String filepath = "C:\\Users\\abisola.akinrinade\\Documents\\workspace\\simpleimplementation\\src\\main\\resources\\timestamps.csv";

	public static final void main(String[] args) {

            try {
                // load up the knowledge base
                KieServices ks = KieServices.Factory.get();
                KieContainer kContainer = ks.getKieClasspathContainer();
                KieSession kSession = kContainer.newKieSession("ksession-rules");

                // Load facts

                List<ConveyorReads> conveyors = CreateReadsFromFile.readConveyorsFromCSV(filepath);
                ListIterator<ConveyorReads> conveyoriterator = conveyors.listIterator();

               // while (conveyoriterator.hasNext()) {
              //      kSession.insert(conveyoriterator.next());

//
               // }

                preprocessing.preprocessing();
                // get conveyorA
                ArrayList<ConveyorReads> conveyor_a = preprocessing.Conveyor_A;
                //System.out.print("\n CreateReadsFromFile A is here as "+conveyor_a+"");
                //get mean and std for conveyor
                DescriptiveStatistics con_A_transport_Time = preprocessing.evaluate_TransportTime(conveyor_a);
                //System.out.print("\n the transport time for A is here as "+con_A_transport_Time+"");

                Double average_time_A = preprocessing.avarage_t_time(con_A_transport_Time);
                System.out.print(""+average_time_A+"");
                creatrules.Drl_Creator.create_drl();
                //conveyor A analysis


                // go !

                //Message message = new Message();
                //message.setMessage("Hello World");
                //message.setStatus(Message.HELLO);
                //kSession.insert(message);
                //kSession.fireAllRules();



                conveyorThresholds t_time = new conveyorThresholds();
                t_time.setTtime(average_time_A);
                kSession.insert(t_time);
                kSession.fireAllRules();


            } catch (Throwable t) {
                t.printStackTrace();
            }

    }
	public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}

