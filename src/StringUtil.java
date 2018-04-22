public class StringUtil {
    static String charToStr(char[] arr) {

        StringBuilder all = new StringBuilder();
        for (char anArr : arr) {
            all.append(anArr);
        }

        return all.toString();
    }
}
