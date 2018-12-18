import java.io.File;

public class AdventOfCodeDriver {

public static void main(String[] args) {
        if (args.length != 3) {
                System.out.println("Correct usage:\njava AdventOfCodeDriver DayNumber PartNumber InputFile");
                System.exit(1);
        }
        String fileName = args[2];
        File f = new File(fileName);
        if(!(f.exists() && !f.isDirectory())) {
                System.out.println("Specify a valid file name");
                System.exit(1);
        }
        int day = Integer.parseInt(args[0]);
        int part = Integer.parseInt(args[1]);
        AdventOfCode code = null;
        switch (day) {
        case 1:
                switch (part) {
                case 1:
                        code = new day1Part1(fileName);
                        break;
                case 2:
                        code = new day1Part2(fileName);
                        break;
                }
                break;
        case 2:
                switch (part) {
                case 1:
                        code = new day2Part1(fileName);
                        break;
                case 2:
                        code = new day2Part2(fileName);
                        break;
                }
                break;
        case 3:
                switch (part) {
                case 1:
                        code = new day3Part1(fileName);
                        break;
                case 2:
                        code = new day3Part2(fileName);
                        break;
                }
                break;
        case 4:
                switch (part) {
                case 1:
                        code = new day4Part1(fileName);
                        break;
                case 2:
                        code = new day4Part2(fileName);
                        break;
                }
                break;
        // case 5: switch (part) {
        //         case 1:
        //                 code = new day5Part1(fileName);
        //                 break;
        //         case 2:
        //                 code = new day5Part2(fileName);
        //                 break;
        // }
        //         break;
        // case 6:  switch (part) {
        //         case 1:
        //                 code = new day6Part1(fileName);
        //                 break;
        //         case 2:
        //                 code = new day6Part2(fileName);
        //                 break;
        // }
        //         break;
        default: code = null;
                break;
        }
        if (code == null) {
                System.out.println("Correct usage:\njava AdventOfCodeDriver DayNumber PartNumber InputFile");
                System.exit(1);
        }
        code.printAnswer();
}
}
