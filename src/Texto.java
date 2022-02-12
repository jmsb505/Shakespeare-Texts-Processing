
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Texto implements Runnable{

    private String individual;
    private Map<String,Long> map= new TreeMap<>();
    private Long tiempo;
    private static ArrayList<Long> Timelog=new ArrayList<>();

    public Texto(String s) throws IOException {
        this.individual=s;
    }

        public void setInputTexto2(String str) throws IOException {
        Pattern pattern1 = Pattern.compile("[A-Z]");
        map= Arrays.stream(str.split(""))
                .map(String::toUpperCase)
                .filter(pattern1.asPredicate())
                .collect(Collectors.groupingBy(String::toUpperCase, TreeMap::new, Collectors.counting()));
        
    }

    public Map<String, Long> getMap() {
        return map;
    }
    public Long getTime()
    {
        return this.tiempo;
    }

    public static Long getMaxTime(){
        Timelog.set(Timelog.size()-1,0L);
        return Timelog.stream().max(Long::compareTo).get();

    }


    @Override
    public void run() {
        try {
            Long t1=System.currentTimeMillis();
            setInputTexto2(individual);
            tiempo=System.currentTimeMillis()-t1;
            System.out.println("Thread"+Thread.currentThread().getName()+" se demoro "+ tiempo);
            Timelog.add(tiempo);
            
        } catch (IOException ex) {
            Logger.getLogger(Texto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
}
