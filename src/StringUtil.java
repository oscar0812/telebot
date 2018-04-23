public class StringUtil {
    static String charToStr(char[] arr) {

        StringBuilder all = new StringBuilder();
        for (char anArr : arr) {
            all.append(anArr);
        }

        return all.toString();
    }

    public static String removeCall(String lower) {
        if (lower.contains("@"+ Constants.BOT_USERNAME)) {
            int indexBefore = lower.indexOf("@"+Constants.BOT_USERNAME) - 1;

            if (indexBefore >= 0) {
                if (lower.charAt(indexBefore) != ' ') {
                    lower = lower.replaceAll("@"+Constants.BOT_USERNAME, "");
                }
            }

        }
        return lower;
    }
}
