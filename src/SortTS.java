import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

//удаление одинаковых строк
public class SortTS {
    public static void main(String[] args) throws IOException {
        File fileTS = new File("D:\\Panchev\\_Projects\\HGM-NIVA\\ListTS.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileTS), StandardCharsets.UTF_8));
        String stroke = "";
        Set<String> treeSet = new TreeSet<>();
        Set<String> set = new HashSet<>();
        while ((stroke = reader.readLine()) != null) {
            stroke = stroke.replaceAll("( Yes)|( No)", "");
//            System.out.println(stroke);
            if (!stroke.equals("")) treeSet.add(stroke);
        }
        for (String s : treeSet) {
            System.out.println(s);
        }
    }
}
