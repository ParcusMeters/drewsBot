
import java.io.File;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    int currentDay = -1;
    Calendar date = Calendar.getInstance(TimeZone.getTimeZone("GMT + 10"));
    Calendar tomorrow = Calendar.getInstance(TimeZone.getTimeZone("GMT + 10"));

    public Main() {
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);

    }

    public void updateMenus() {
        int day = date.get(Calendar.DAY_OF_MONTH);
        if (currentDay != day) {
            System.out.println("deez");
            currentDay = day;
            downloadMenus();
            ;
        }
    }

    public void downloadMenus() {
        File todayOut = new File(
                "C:\\Users\\jacko\\OneDrive\\Random Unsorted Shit\\Documents\\Code\\Bot\\Todays Feed.pdf");

        File tomorrowOut = new File(
                "C:\\Users\\jacko\\OneDrive\\Random Unsorted Shit\\Documents\\Code\\Bot\\Tomorrows Feed.pdf");

        // getting calendar values
        int year = date.get(Calendar.YEAR);
        int monthInt = date.get(Calendar.MONTH) + 1;
        String month = String.format("%02d", monthInt);

        // getting tomorrows values
        int yearTomorrow = tomorrow.get(Calendar.YEAR);
        int monthIntTomorrow = tomorrow.get(Calendar.MONTH) + 1;
        String monthTomorrow = String.format("%02d", monthIntTomorrow);
        int dayTomorrow = tomorrow.get(Calendar.DAY_OF_MONTH);

        // if current day has changed, download new menus
        String linkToday = "https://students.standrewscollege.edu.au/wp-content/uploads/" + year + "/" + month + "/"
                + currentDay + month + ".pdf";
        new Thread(new Download(linkToday, todayOut)).start();

        String linkTomorrow = "https://students.standrewscollege.edu.au/wp-content/uploads/" + yearTomorrow + "/"
                + monthTomorrow + "/" + dayTomorrow + monthTomorrow + ".pdf";
        new Thread(new Download(linkTomorrow, tomorrowOut)).start();

    }

    public static void main(String[] args) {
        Main main = new Main();

        main.updateMenus();
        main.updateMenus();

        System.out.println("Working");

    }
}
