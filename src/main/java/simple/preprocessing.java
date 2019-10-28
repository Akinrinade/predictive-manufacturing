package simple;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.csv.*;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;


public class preprocessing {

    public static List<ConveyorReads> allconveyors = ConveyorReads.conveyors;
    static ArrayList<ConveyorReads> Conveyor_A = new ArrayList<ConveyorReads>();
    static ArrayList<ConveyorReads> Conveyor_B = new ArrayList<ConveyorReads>();
    static ArrayList<ConveyorReads> Conveyor_C = new ArrayList<ConveyorReads>();
    static ArrayList<ConveyorReads> Conveyor_D = new ArrayList<ConveyorReads>();
    static ArrayList<ConveyorReads> Conveyor_E = new ArrayList<ConveyorReads>();
    static List<ConveyorReads> Conveyor_F = new ArrayList<ConveyorReads>();

    //static DescriptiveStatistics transport_time_A = (DescriptiveStatistics) evaluate_TransportTime(Conveyor_A);
    //static DescriptiveStatistics transport_time_B = (DescriptiveStatistics) evaluate_TransportTime(Conveyor_B);
    DescriptiveStatistics transport_time_C = (DescriptiveStatistics) evaluate_TransportTime(Conveyor_C);
    DescriptiveStatistics transport_time_D = (DescriptiveStatistics) evaluate_TransportTime(Conveyor_D);
    DescriptiveStatistics transport_time_E = (DescriptiveStatistics) evaluate_TransportTime(Conveyor_E);
    DescriptiveStatistics transport_time_F = (DescriptiveStatistics) evaluate_TransportTime(Conveyor_F);


   // public static DescriptiveStatistics transport_time_A (ArrayList<ConveyorReads> conveyorobjectlist){
   //     return (DescriptiveStatistics) evaluate_TransportTime(conveyorobjectlist);
   // }

    public static Double avarage_t_time(DescriptiveStatistics timelist){
        //System.out.print(""+ transport_time_B.getMean()+"");
        System.out.print("our time list is "+ timelist +"");
        Double time_list = timelist.getMean();
        WeightedEvaluation dfg;

        return time_list;
    }




    public static void preprocessing(){
        for (ConveyorReads conveyor : allconveyors) {

            //System.out.print("all conveyors list  = "+allconveyors+"");
            String conveyorname = conveyor.getName();
            switch (conveyorname) {
                case "ConveyorA":
                    //System.out.print("added A");
                    Conveyor_A.add(conveyor);
                case "ConveyorB":
                    Conveyor_B.add(conveyor);
                case "ConveyorC":
                    Conveyor_C.add(conveyor);
                case "ConveyorD":
                    Conveyor_D.add(conveyor);
                case "ConveyorE":
                    Conveyor_E.add(conveyor);
                case "ConveyorF":
                    Conveyor_F.add(conveyor);
            }
        }
    }

    public static DescriptiveStatistics evaluate_TransportTime (List<ConveyorReads> conveyortype) {
        //preprocessing();
        ListIterator conveyoriterator = conveyortype.listIterator();
        System.out.print("\n starting");
        List<Long> conveyor_t = new ArrayList<Long>();
        DescriptiveStatistics conveyor_stat = new DescriptiveStatistics();
        Long sent_time = null;
        Long received_time = null;
        for (ListIterator it = conveyoriterator; it.hasNext(); ) {
            ConveyorReads nxt = (ConveyorReads) it.next();
            System.out.print("\n the next individual conveyor is "+nxt+"");
            System.out.print("\n the action is "+nxt.getAction()+"");
            String action = nxt.getAction();

            if (action.equals("Sent")) {
                System.out.print("\n ############the next individual was a sent");
                sent_time = nxt.getTime();
            }
            else if (action.equals("Received")){
                if (sent_time != null) {

                    received_time = nxt.getTime();
                    Long transport_time = received_time - sent_time;
                    System.out.print("transport time  = " + transport_time + "");
                    //conveyor_t = Arrays.asList((Long[])
                    //conveyor_t.add(transport_time);
                    conveyor_stat.addValue(transport_time);
                }
            }
        }
        return conveyor_stat;
    }



}


