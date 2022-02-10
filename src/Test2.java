/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author charl
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        List<String> arr=new ArrayList<String>();
        List<String>obras=new ArrayList<String>();
        String lineas= Files.lines(Paths.get("Shakespeare.txt")).collect(Collectors.joining());
        String[] split = lineas.split("@");
        System.out.println(split.length);
       
               
        
        
    }
    
}
