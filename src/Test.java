package src;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws IOException {
        //Path p= Paths.get("src/Shakespeare.txt");
        //String[] s= Files.readString(p).split("@");
        String wholetxt= Files.lines(Paths.get("Shakespeare.txt")).collect(Collectors.joining());
        String[] obras = wholetxt.split("@");
        Texto t= new Texto(obras[0]);
        System.out.println(t.getMap());







    }

}

