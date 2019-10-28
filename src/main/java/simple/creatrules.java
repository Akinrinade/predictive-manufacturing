package simple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.descr.PackageDescr;


public class creatrules {

    @SuppressWarnings("restriction")
    public static class Drl_Creator {

        public static void create_drl(){
            PackageDescr pkg = DescrFactory.newPackage()
                    .name("simple")
                    .newRule().name("Test1")
                    .attribute("ruleflow-group","Check Input")
                    .lhs()
                    .and()
                    .pattern("IndividualConveyor").id( "$conveyor", false ).constraint("name==ConveyorA").constraint("time>10").end()
                    .not().pattern("Bar").constraint("a+b==c").end().end()
                    .end()
                    .end()
                    .rhs( "System.out.println();" ).end()
                    .getDescr();
            DrlDumper dumper=new DrlDumper();
            String drl=dumper.dump(pkg);
            System.out.print(drl);
            try{
                // create new file
                File file = new File("C:\\Users\\abisola.akinrinade\\Documents\\workspace\\simpleimplementation\\src\\main\\resources\\com\\sample\\rules\\Sample22.drl");
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
