import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class day4Part2 extends day4 {

public day4Part2(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        Collections.sort(dates);
        HashMap<Integer, HashMap<Integer, Integer> > minuteToGuardToTimes = new HashMap<Integer, HashMap<Integer, Integer> >();
        int lastGuard = -1;
        Date fellAsleep = null;
        for (Date d: dates) {
                if (isInt(timeToMessage.get(d))) {
                        lastGuard = Integer.parseInt(timeToMessage.get(d));
                } else if (fellAsleep == null) {
                        fellAsleep = d;
                } else {
                        int sleep = fellAsleep.getMinutes();
                        int wake = d.getMinutes();
                        for (int i = sleep; i < wake; i++) {
                                if (minuteToGuardToTimes.containsKey(i)) {
                                        if (minuteToGuardToTimes.get(i).containsKey(lastGuard)) {
                                                minuteToGuardToTimes.get(i).put(lastGuard, minuteToGuardToTimes.get(i).get(lastGuard) + 1);
                                        } else {
                                                minuteToGuardToTimes.get(i).put(lastGuard, 1);
                                        }
                                } else {
                                        HashMap<Integer, Integer> guardToTimes = new HashMap<Integer, Integer>();
                                        guardToTimes.put(lastGuard, 1);
                                        minuteToGuardToTimes.put(i, guardToTimes);
                                }
                        }
                        fellAsleep = null;
                }
        }
        int guard = 0;
        int minute = 0;
        int maxTimes = 0;
        for (int m: minuteToGuardToTimes.keySet()) {
                for (int g :minuteToGuardToTimes.get(m).keySet()) {
                        if (minuteToGuardToTimes.get(m).get(g) > maxTimes) {
                                maxTimes = minuteToGuardToTimes.get(m).get(g);
                                minute = m;
                                guard = g;
                        }
                }
        }
        return ((Integer)(guard*minute)).toString();
}

protected Object processLine(String line) {

        String date = line.substring(0, 18);
        String message = line.substring(19);
        if (message.substring(0, 5).equals("Guard")) {
                message = message.split("\\s+")[1].substring(1);
        }
        Date dateObj = null;
        try {
                dateObj = parser.parse(date);
        } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
        }
        timeToMessage.put(dateObj, message);
        dates.add(dateObj);

        return null;
}

}
