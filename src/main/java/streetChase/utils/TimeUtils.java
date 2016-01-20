package streetChase.utils;

public class TimeUtils {

    public static String formatTimeInterval(long seconds) {
        StringBuilder s = new StringBuilder();
        long hours = seconds/3600;
        seconds %= 3600;
        long minutes = seconds/60;
        seconds %= 60;

        s.append(hours);
        s.append(":");
        s.append(minutes);
        s.append(":");
        s.append(seconds);

        return s.toString();
    }
}
