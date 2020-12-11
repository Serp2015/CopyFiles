import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File("D:\\Panchev\\_Projects\\HGM-NIVA\\Input");
        File listParts = new File("D:\\Panchev\\_Projects\\HGM-NIVA\\List2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(listParts));
        String partName = "";
        while ((partName = reader.readLine()) != null) {
            System.out.println(partName);
            findFile(partName, sourceFile);
        }
        reader.close();
    }

    public static void findFile(String partName, File sourceFile) throws IOException {
        String simplePartName = partName.replaceAll("[^\\d]", "");
        File[] listFiles = sourceFile.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                int i = 1;
                String simpleName = file.getName().replaceAll("[^\\d]", "");
                if (file.isDirectory()) {
                    if (simpleName.contains(simplePartName)) {
                        Files.copy(file.toPath(), Paths.get(choosePath(partName, file)));
                    } else findFile(partName, file);
                } else {
                    if (simpleName.contains(simplePartName)) {
                        try {
                            Files.copy(file.toPath(), Paths.get(choosePath(partName, file)));
                        } catch (FileAlreadyExistsException e) {
                            String copyPath = choosePath(partName, file);
                            Pattern p = Pattern.compile("(?i)(\\.CATPart)|(\\.CATProduct)|(\\.tif)|(\\.jpe?g)|(\\.pdf)");
                            Matcher m = p.matcher(copyPath);
                            while (m.find()) {
                                copyPath = copyPath.replaceAll(m.group(), "_copy" + i + m.group());
                            }
                            Files.copy(file.toPath(), Paths.get(copyPath));
                            i++;
                        }
                    }
                }
            }
        }
    }

    public static String choosePath(String partName, File file) {
        String name = file.getName();
        String path2D = "D:\\Panchev\\_Projects\\HGM-NIVA\\GFE_51\\2D\\";
        String path3D = "D:\\Panchev\\_Projects\\HGM-NIVA\\GFE_51\\3D\\";
        String pathAMA = "D:\\Panchev\\_Projects\\HGM-NIVA\\GFE_51\\Assembly-Machining Agreement\\";
        String pathIR = "D:\\Panchev\\_Projects\\HGM-NIVA\\GFE_51\\Inspection Report\\";
        String pathPVTR = "D:\\Panchev\\_Projects\\HGM-NIVA\\GFE_51\\Product Validation Tests Report\\";
        String newPath = null;
        String newPathFolder = null;
        if (name.matches("(.+\\.CATPart)|(.+\\.CATProduct)")) {
            newPath = path3D + partName + "\\" + name;
            newPathFolder = path3D + partName;
        }
        if (name.toLowerCase().matches("(.+\\.tif)|(.+\\.jpe?g)|(.+\\.pdf)")) {
            if (name.matches(".+N-\\d\\d\\d\\d.+")) {
                newPath = pathAMA + partName + "\\" + name;
                newPathFolder = pathAMA + partName;
            }
            else {
                newPath = path2D + partName + "\\" + name;
                newPathFolder = path2D + partName;
            }
        }
        try {
            Files.createDirectory(Paths.get(newPathFolder));
        } catch (Exception e) {
        }
        return newPath;
    }
}