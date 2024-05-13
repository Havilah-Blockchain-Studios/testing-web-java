import java.nio.charset.StandardCharsets;

public class StandardCharsetsError {
    public static void main(String[] args) {
        String s = getPropertyValue();
        - byte[] b = s.getBytes("UTF-8");
        + byte[] b = s.getBytes(StandardCharsets.UTF_8);
    }

    private static String getPropertyValue() {
        // This method should return a String value
        return "Your property value here";
    }
}
