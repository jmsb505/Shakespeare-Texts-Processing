
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws IOException {
        String wholetxt= Files.lines(Paths.get("Shakespeare.txt")).collect(Collectors.joining());
        String[] obras = wholetxt.split("@");
        List<Texto> conjunto=new ArrayList<Texto>();
        ExecutorService executorService = Executors.newFixedThreadPool(38);
        Long tpara=System.currentTimeMillis();
        for(int i=0;i<obras.length;i++)
        {
          Texto txtx =new Texto(obras[i]);
          conjunto.add(txtx);
        }
        for(int i=0;i<conjunto.size();i++)
        {
          executorService.execute(conjunto.get(i));
        }
        System.out.println("Existen paralelamente "+Thread.activeCount()+" threads (incluyendo el main)");//added
        executorService.shutdown();
        try {
            executorService.awaitTermination(10000L, TimeUnit.MILLISECONDS );
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

       

       
        
        //Secuencial
        if(executorService.isTerminated())
        {
        ArrayList<Map<String,Long>> Maps=new ArrayList<>();
        conjunto.forEach(e->Maps.add(e.getMap()));
        Map<String,Long> suma= new TreeMap<>();
        suma.putAll(Maps.get(0));
        suma.entrySet().forEach(e-> e.setValue(0L));
        Maps.forEach(e->{
            e.forEach((key, value) -> suma.put(key, suma.get(key)+value));
        });

        System.out.println("Existen secuencialmente "+Thread.activeCount()+" threads (incluyendo el main)\n\n");
        Texto textfinal=new Texto(obras);
        Long tfinal=System.currentTimeMillis();
        textfinal.setInputTextos(obras);
        System.out.println("Tiempo secuencial "+ (System.currentTimeMillis()-tfinal)+ " ms");
        System.out.println("Mapa de secuencial: "+textfinal.getMap().toString());
        System.out.println("Mapa suma de threads: "+suma);
        System.out.println("Tiempo de Thread "+conjunto.get(0).getTime());
        
        }
        







    }

}

