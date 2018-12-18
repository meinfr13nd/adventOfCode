import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;


public class day4Part1 extends day4 {

public day4Part1(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        Collections.sort(dates);
        HashMap<Integer, ArrayList<ArrayList<Date> > > guardToSleepTimes = new HashMap<Integer, ArrayList<ArrayList<Date> > >();
        HashMap<Integer, Integer> guardToSleepAmount = new HashMap<Integer, Integer>();
        int lastGuard = -1;
        Date fellAsleep = null;
        for (Date d: dates) {
                if (isInt(timeToMessage.get(d))) {
                        lastGuard = Integer.parseInt(timeToMessage.get(d));
                } else if (fellAsleep == null) {
                        fellAsleep = d;
                } else {
                        if (guardToSleepAmount.containsKey(lastGuard)) {
                                guardToSleepAmount.put(lastGuard, guardToSleepAmount.get(lastGuard) + (int)(Math.abs((d.getTime()-fellAsleep.getTime()))));
                        } else {
                                guardToSleepAmount.put(lastGuard, (int)(Math.abs((d.getTime()-fellAsleep.getTime()))));
                        }
                        ArrayList<Date> fellThenWake = new ArrayList<Date>();
                        fellThenWake.add(fellAsleep);
                        fellThenWake.add(d);
                        if (guardToSleepTimes.containsKey(lastGuard)) {
                                guardToSleepTimes.get(lastGuard).add(fellThenWake);
                        } else {
                                ArrayList<ArrayList<Date> > listOfFellThenWake = new ArrayList<ArrayList<Date> >();
                                listOfFellThenWake.add(fellThenWake);
                                guardToSleepTimes.put(lastGuard, listOfFellThenWake);
                        }
                        fellAsleep = null;
                }
        }
        int sleptMost = -1;
        int timeSlept = -1;
        for (Integer g : guardToSleepAmount.keySet()) {
                if (guardToSleepAmount.get(g) > timeSlept) {
                        timeSlept = guardToSleepAmount.get(g);
                        sleptMost = g;
                }
        }
        HashMap<Integer, Integer> minSleepingToTimes = new HashMap<Integer, Integer>();
        for (ArrayList<Date> sleepThenWake: guardToSleepTimes.get(sleptMost)) {
                int sleep = sleepThenWake.get(0).getMinutes();
                int wake = sleepThenWake.get(1).getMinutes();
                for (int i = sleep; i < wake; i++) {
                        if (minSleepingToTimes.containsKey(i)) {
                                minSleepingToTimes.put(i, minSleepingToTimes.get(i)+1);
                        } else {
                                minSleepingToTimes.put(i,1);
                        }
                }
        }
        int m = -1;
        int t = 0;
        for (int i : minSleepingToTimes.keySet()) {
                if (minSleepingToTimes.get(i) > m) {
                        m = minSleepingToTimes.get(i);
                        t = i;
                }
        }
        return ((Integer)(t*sleptMost)).toString();
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
