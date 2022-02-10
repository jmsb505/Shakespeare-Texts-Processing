package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Texto implements Runnable{
    private String[] libros;
    private String individual;
    private Map<String,Long> map= new TreeMap<>();
    private Long tiempo;

    public Texto(String[] s) throws IOException {
        this.libros=s;
        //setInputTextos(s);
    }

    public Texto(String s) throws IOException {
        this.individual=s;
        //setInputTexto(s);
    }

    

    public void setInputTextos(String[] str) throws IOException {
        Pattern pattern= Pattern.compile("");
        Pattern pattern1 = Pattern.compile("[A-Z]");
        map= Arrays.stream(str)
                .flatMap(pattern::splitAsStream)
                .map(String::toUpperCase)
                .filter(pattern1.asPredicate())
                .collect(Collectors.groupingBy(String::toUpperCase, TreeMap::new, Collectors.counting()));
    }
    public void setInputTexto(String str) throws IOException {
        Pattern pattern1 = Pattern.compile("[A-Z]");
        map= Arrays.stream(str.split(""))
                .map(String::toUpperCase)
                .filter(pattern1.asPredicate())
                .collect(Collectors.groupingBy(String::toUpperCase, TreeMap::new, Collectors.counting()));
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

    @Override
    public void run() {
        try {
            Long t1=System.currentTimeMillis();
            setInputTexto2(individual);
            tiempo=System.currentTimeMillis()-t1;
            System.out.println("Thread"+Thread.currentThread().getName()+" se demoro "+ tiempo);
            
        } catch (IOException ex) {
            Logger.getLogger(Texto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
}
