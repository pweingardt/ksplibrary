package de.ksp.library;

import de.ksp.library.exceptions.UnknownTimeFormatException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: paul
 * Date: 27.03.13
 * Time: 13:05
 */
public class KSPUtil {

    private final static Pattern timePattern = Pattern.compile("^(?:(\\d+)d)?(?:(\\d+)h)?(?:(\\d+)m)?(?:(\\d+(?:\\.\\d+))s)?$");

    public static String formatTime(double seconds, boolean shortForm, boolean useYears) {
        ArrayList<String> parts = new ArrayList<String>();

        if(useYears) {
            int y = (int)(seconds / (24.0 * 60.0 * 60.0 * 365.0));
            seconds = seconds % (24.0 * 60.0 * 60.0 * 365.0);

            if(!shortForm || y != 0) {
                parts.add(String.format("%02dy", y));
            }
        }

        int d = (int)(seconds / (24.0 * 60.0 * 60.0));
        seconds = seconds % (24.0 * 60.0 * 60.0);
        int h = (int)(seconds / (60.0 * 60.0));
        seconds = seconds % (60.0 * 60.0);
        int m = (int)(seconds / (60.0));
        seconds = seconds % (60.0);


        if(!shortForm || d != 0) {
            parts.add(String.format("%02dd", d));
        }

        if(!shortForm || h != 0) {
            parts.add(String.format("%02dh", h));
        }

        if(!shortForm || m != 0) {
            parts.add(String.format("%02dm", m));
        }

        if(!shortForm || seconds != 0) {
            parts.add(String.format("%02.2fs", seconds));
        }

        return StringUtils.join(parts, " ");
    }

    public static double parseTime(String time) throws UnknownTimeFormatException {
        Matcher matcher = timePattern.matcher(time);

        if(matcher.find()) {
            int days = matcher.group(1) == null ? 0 : Integer.parseInt(matcher.group(1));
            int hours = matcher.group(2) == null ? 0 : Integer.parseInt(matcher.group(2));
            int minutes = matcher.group(3) == null ? 0 : Integer.parseInt(matcher.group(3));
            double seconds = matcher.group(4) == null ? 0 : Double.parseDouble(matcher.group(4));

            return days * 24.0 * 60.0 * 60.0 + hours * 60.0 * 60.0 + minutes * 60.0 + seconds;
        } else {
            throw new UnknownTimeFormatException(time);
        }
    }

}
