package streetChase.utils;

public class TimeUtils {

    public static String formatTimeInterval(long seconds) {
        StringBuilder s = new StringBuilder();
        long hours = seconds/3600;
        seconds %= 3600;
        long minutes = seconds/60;
        seconds %= 60;

        if (hours < 10)
            s.append("0");
        s.append(hours);
        s.append(":");
        if (minutes < 10)
            s.append("0");
        s.append(minutes);
        s.append(":");
        if (seconds < 10)
            s.append("0");
        s.append(seconds);

        return s.toString();
    }
}
