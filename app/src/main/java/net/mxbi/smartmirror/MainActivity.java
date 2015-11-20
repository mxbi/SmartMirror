package net.mxbi.smartmirror;

import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.mxbi.smartmirror.model.Location;
import net.mxbi.smartmirror.model.Weather;

import android.os.AsyncTask;




public class MainActivity extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Weird code that gets rid of action bar, but I don't know why
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Code to run every 5 seconds
        Timer fiveSecondTimer = new Timer();
        fiveSecondTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimeDate();
            }
        }, 0, 5000);

        // UNFINISHED WEATHER CODE

        String city = "London, UK";
        JSONWeatherTask task = new JSONWeatherTask();
        //task.execute(new String[]{city});

    }

    public void updateTimeDate() {
        // Get time string
        Calendar c = Calendar.getInstance();
        String minute = Integer.toString(c.get(Calendar.MINUTE));
        String hour = Integer.toString(c.get(Calendar.HOUR));
        // If minute is a single digit, add a 0 to the start
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        String time = hour + ":" + minute;

        // Get date string
        String dayOfMonth = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        int dayOfWeekValue = c.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = "failed";
        if (dayOfWeekValue == Calendar.SUNDAY) {
            dayOfWeek = "Sunday";
        }
        if (dayOfWeekValue == Calendar.MONDAY) {
            dayOfWeek = "Monday";
        }
        if (dayOfWeekValue == Calendar.TUESDAY) {
            dayOfWeek = "Tuesday";
        }
        if (dayOfWeekValue == Calendar.WEDNESDAY) {
            dayOfWeek = "Wednesday";
        }
        if (dayOfWeekValue == Calendar.THURSDAY) {
            dayOfWeek = "Thursday";
        }
        if (dayOfWeekValue == Calendar.FRIDAY) {
            dayOfWeek = "Friday";
        }
        if (dayOfWeekValue == Calendar.SATURDAY) {
            dayOfWeek = "Saturday";
        }
        int monthValue = c.get(Calendar.MONTH);

        // Get month string
        String month = "failed";
        if (monthValue == Calendar.JANUARY) {
            month = "January";
        }
        if (monthValue == Calendar.FEBRUARY) {
            month = "February";
        }
        if (monthValue == Calendar.MARCH) {
            month = "March";
        }
        if (monthValue == Calendar.APRIL) {
            month = "April";
        }
        if (monthValue == Calendar.MAY) {
            month = "May";
        }
        if (monthValue == Calendar.JUNE) {
            month = "June";
        }
        if (monthValue == Calendar.JULY) {
            month = "July";
        }
        if (monthValue == Calendar.AUGUST) {
            month = "August";
        }
        if (monthValue == Calendar.SEPTEMBER) {
            month = "September";
        }
        if (monthValue == Calendar.OCTOBER) {
            month = "October";
        }
        if (monthValue == Calendar.NOVEMBER) {
            month = "November";
        }
        if (monthValue == Calendar.DECEMBER) {
            month = "December";
        }
        String date = dayOfWeek + ", " + dayOfMonth + " " + month;

        //Display
        displayTimeDate(time, date);
    }

    public void displayTimeDate(String time, String date) {
        final String timeFinal = time;
        final String dateFinal = date;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView timeDisplay = (TextView) findViewById(R.id.timeView);
                TextView dateDisplay = (TextView) findViewById(R.id.dateView);

                timeDisplay.setText(timeFinal);
                dateDisplay.setText(dateFinal);
            }
        });

    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = (new WeatherHttpClient().getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
                weather.iconData = ((new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

    }

}
