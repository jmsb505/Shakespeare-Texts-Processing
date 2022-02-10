package src;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        //Path p= Paths.get("src/Shakespeare.txt");
        //String[] s= Files.readString(p).split("@");
        String wholetxt= Files.lines(Paths.get("Shakespeare.txt")).collect(Collectors.joining());
        String[] obras = wholetxt.split("@");
        Texto t= new Texto(obras[0]);
        Texto t2= new Texto(obras[1]);
        int count=0;
        ExecutorService executorService = Executors.newFixedThreadPool(38);
        Long tpara=System.currentTimeMillis();
        for(int i=0;i<obras.length;i++)
        {
          executorService.execute(new Texto(obras[i]));
        }
        System.out.println("Existen paralelamente "+Thread.activeCount()+" threads (incluyendo el main)");
        executorService.shutdown();
        try {
            executorService.awaitTermination(10000L, TimeUnit.MILLISECONDS );
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

       

       
        
        //Secuencial
        if(executorService.isTerminated())
        {
        System.out.println("Existen secuencialmente "+Thread.activeCount()+" threads (incluyendo el main)");
        Texto textfinal=new Texto(obras);
        Long tfinal=System.currentTimeMillis();
        textfinal.setInputTextos(obras);
        System.out.println("Tiempo secuencial "+ (System.currentTimeMillis()-tfinal)+ " ms");
        System.out.println(textfinal.getMap().toString());
        }







    }

}

