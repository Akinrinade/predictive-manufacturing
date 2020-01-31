package simple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Time;
import java.util.HashMap;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.descr.*;


public class creatrules {

    @SuppressWarnings("restriction")
    public static class Drl_Creator {

        public static void create_drl(HashMap thresholds) throws ClassNotFoundException {
            String conveyorName = (String) thresholds.get("conveyor_name");
            String conveyorFile = conveyorName+".drl";
            
            String fpath = "/home/pi/IdeaProjects/predictive-manufacturing/src/main/resources/com/sample/rules/"+conveyorFile+"";


            PackageDescr pkg = DescrFactory.newPackage()


                    .name("simple")

                    .newRule().name("Test1")

                    .lhs()
                    .and()
                    .pattern("TransportTime").id( "$conveyor", false ).constraint("t_time<="+thresholds.get("upper")+"").constraint("t_time<="+thresholds.get("lower")+"").end()

                    .end()
                    .end()
                    .rhs("System.out.println('ConveyoA nothing to do');" ).end()
                    .getDescr();

            ImportDescr importEntry4= new ImportDescr();
            importEntry4.setTarget("simple.TransportTime");
            pkg.addImport(importEntry4);

//            PackageDescr pkg2=new PackageDescr();
//            pkg.setName("simple");
//
//// -------import section here-------
//            ImportDescr importEntry1= new ImportDescr();
//            importEntry1.setTarget("com.demo.model.Purchase");
//            pkg.addImport(importEntry1);
//            ImportDescr importEntry2= new ImportDescr();
//            importEntry2.setTarget("com.demo.model.PotentialCustomer");
//            pkg.addImport(importEntry2);
//
//            ImportDescr importEntry3= new ImportDescr();
//            importEntry3.setTarget("com.demo.model.PaymentMethod");
//            pkg.addImport(importEntry3);
//
////-------global section here-------
//            GlobalDescr globalEntry=new GlobalDescr();
//            globalEntry.setType("org.slf4j.Logger");
//            globalEntry.setIdentifier("logger");
//            pkg.addGlobal(globalEntry);
//
////------- rule section here
//            RuleDescr ruleEntry=new RuleDescr();
//            ruleEntry.setName("Test1");
//
//// ------- lhs starts here -------
//            AndDescr lhs=new AndDescr();
//
////-------  pattern starts here -------
//            PatternDescr patternEntry1=new PatternDescr();
//            patternEntry1.setIdentifier("$ttime");
//            patternEntry1.setObjectType("TransportTime");
//
////------- ExprConstraint starts here -------
//            ExprConstraintDescr ecd1=new ExprConstraintDescr();
//            ecd1.setExpression("paymentMethod");
//            ExprConstraintDescr ecd2=new ExprConstraintDescr();
//            ecd2.setExpression("PaymentMethod.CASH");
////-------  Added exprConstraint into relational expr-------
//            RelationalExprDescr red1=new RelationalExprDescr("==",false, null, ecd1, ecd2);
//
//            ExprConstraintDescr ecd3=new ExprConstraintDescr();
//            ecd3.setExpression("subTotal");
//            ExprConstraintDescr ecd4=new ExprConstraintDescr();
//            ecd4.setExpression("300");
//            RelationalExprDescr red2=new RelationalExprDescr(">",false, null, ecd3, ecd4);
//
//
//

            DrlDumper dumper=new DrlDumper();
            String drl=dumper.dump(pkg);
            System.out.print(drl);
            try{
                // create new file
                //File file = new File("C:\\Users\\abisola.akinrinade\\Documents\\workspace\\simpleimplementation\\src\\main\\resources\\com\\sample\\rules\\Sample22.drl");
                File file = new File(fpath);
                if (file.exists()){
                    file.delete();
                    System.out.println(file.getName() + " is deleted!");
                }
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(drl);
                // close connection
                bw.close();
                System.out.println("File Created Successfully");
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
