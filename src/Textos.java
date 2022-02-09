package src;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Textos {

    String[] texts;

    public String[] InputTextos(Path path) throws IOException {
     String[] s= Files.readString(path).split("@");
     texts=s;
     return s;
return s;


    }
    //private Map<String,Integer> countIndex(){
        //Pattern pattern= Pattern.compile("[A-Z]");




    //}
}
