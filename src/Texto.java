import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Texto {

    public Texto(String[] s) throws IOException {
        setInputTextos(s);
    }

    public Texto(String s) throws IOException {
        setInputTexto(s);
    }

    Map<String,Long> map= new TreeMap<>();

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

    public Map<String, Long> getMap() {
        return map;
    }
}
