
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        //Sumar los maps de los threads y secuencial
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
        String Header=" ,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z\n";
        StringBuilder content= new StringBuilder();
        content.append(Header);
            AtomicInteger count= new AtomicInteger(1);
        Maps.forEach(e->{
            content.append("Texto "+count+",");
            e.forEach((key,value)->{
                content.append(value+",");
            });
            content.append("\n");
            count.getAndIncrement();
        });
        content.append("Suma,");
        suma.forEach((key,value)->content.append(value+","));
        content.append("\nSecuencial,");
        textfinal.getMap()
                .forEach((key,value)->content.append(value+","));
            try(FileWriter fw=new FileWriter("Frecuencias.csv")) {
                fw.write(content.toString());
            } catch (FileNotFoundException exception){
                System.out.println(exception.getMessage());
            }
}






    }

}

