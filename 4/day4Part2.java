import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;



public class day4Part2 {

public static boolean isInt(String str)
{
        try
        {
                int i = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
                return false;
        }
        return true;
}

private static int processFile(String fileName) {
        HashMap<Date, String> timeToMessage = new HashMap<Date, String>();
        ArrayList<Date> dates = new ArrayList<Date>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                SimpleDateFormat parser = new SimpleDateFormat("[yyyy-MM-dd HH:mm]");
                for(String line; (line = br.readLine()) != null;) {
                        String date = line.substring(0, 18);
                        String message = line.substring(19);
                        if (message.substring(0, 5).equals("Guard")) {
                                message = message.split("\\s+")[1].substring(1);
                        }
                        Date dateObj = parser.parse(date);
                        timeToMessage.put(dateObj, message);
                        dates.add(dateObj);
                }
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
                return guard*minute;
        } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
        }
        return -1;
}



public static void main(String[] args) {
        System.out.println(processFile(args[0]));
}
}
