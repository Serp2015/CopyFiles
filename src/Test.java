//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Test {
//    public static void main(String[] args) {
//        String copyPath = "21230-2906010-01-0.CATPart";
//        Pattern p = Pattern.compile("(?i)(\\.CATPart)|(\\.CATProduct)|(\\.tif)|(\\.jpe?g)|(\\.pdf)");
//        Matcher m = p.matcher(copyPath);
//        if (m.find()) {
//            copyPath = copyPath.replaceAll(m.group(), "_copy" + m.group());
//            System.out.println(copyPath);
//        }
//    }
//}
