import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public abstract class day4 extends AdventOfCode {
public day4(String fileName) {
        super(fileName);
}
protected static boolean isInt(String str) {
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


protected HashMap<Date, String> timeToMessage = new HashMap<Date, String>();
protected ArrayList<Date> dates = new ArrayList<Date>();
protected SimpleDateFormat parser = new SimpleDateFormat("[yyyy-MM-dd HH:mm]");
}
