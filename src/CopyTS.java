import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CopyTS {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File("D:\\Panchev\\_Projects\\HGM-NIVA\\Input");
        File destFolder = new File("D:\\Panchev\\_Projects\\HGM-NIVA\\GFE_51\\TS");
        File listTS = new File("D:\\Panchev\\_Projects\\HGM-NIVA\\ListTS.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(listTS), StandardCharsets.UTF_8));
        String TSName = "";
        while ((TSName = reader.readLine()) != null) {
            System.out.println(TSName);
            findFile(TSName, sourceFile, destFolder);
        }
    }

    private static void findFile(String TSName, File sourceFile, File destFolder) {
        String simplePartName = TSName.replaceAll("[^\\d]", "");
        File[] listFiles = sourceFile.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                String simpleName = file.getName().replaceAll("[^\\d]", "");
                if (file.isDirectory()) findFile(TSName, file, destFolder);
                else {
                    if (simpleName.contains(simplePartName)) {
//                        System.out.println(destFolder.toPath().resolve(file.getName()));
                        try {
                            Files.copy(file.toPath(), destFolder.toPath().resolve(file.getName()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
