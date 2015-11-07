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

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

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

        // Get display textviews
        TextView timeDisplay = (TextView) findViewById(R.id.timeView);
        TextView dateDisplay = (TextView) findViewById(R.id.dateView);

        // Get time string
        Calendar c = Calendar.getInstance();
        String minute = Integer.toString(c.get(Calendar.MINUTE));
        String hour = Integer.toString(c.get(Calendar.HOUR));
        String time = hour + ":" + minute;

        // Get date string
        String dayOfMonth = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        int dayOfWeekValue = c.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = "failed";
        if(dayOfWeekValue == Calendar.SUNDAY){dayOfWeek = "Sunday";}
        if(dayOfWeekValue == Calendar.MONDAY){dayOfWeek = "Monday";}
        if(dayOfWeekValue == Calendar.TUESDAY){dayOfWeek = "Tuesday";}
        if(dayOfWeekValue == Calendar.WEDNESDAY){dayOfWeek = "Wednesday";}
        if(dayOfWeekValue == Calendar.THURSDAY){dayOfWeek = "Thursday";}
        if(dayOfWeekValue == Calendar.FRIDAY){dayOfWeek = "Friday";}
        if(dayOfWeekValue == Calendar.SATURDAY){dayOfWeek = "Saturday";}
        int monthValue = c.get(Calendar.MONTH);
        String month = "failed";
        if(monthValue == Calendar.JANUARY){month = "January";}
        if(monthValue == Calendar.FEBRUARY){month = "February";}
        if(monthValue == Calendar.MARCH){month = "March";}
        if(monthValue == Calendar.APRIL){month = "April";}
        if(monthValue == Calendar.MAY){month = "May";}
        if(monthValue == Calendar.JUNE){month = "June";}
        if(monthValue == Calendar.JULY){month = "July";}
        if(monthValue == Calendar.AUGUST){month = "August";}
        if(monthValue == Calendar.SEPTEMBER){month = "September";}
        if(monthValue == Calendar.OCTOBER){month = "October";}
        if(monthValue == Calendar.NOVEMBER){month = "November";}
        if(monthValue == Calendar.DECEMBER){month = "December";}
        String date = dayOfWeek + ", " + dayOfMonth + " " + month;

        //Toast.makeText(getApplicationContext(), dayOfWeek, Toast.LENGTH_SHORT).show(); //Debugging toast to see time

        // Set time display to time
        timeDisplay.setText(time);
        dateDisplay.setText(date);

        // Sleep for 1 second
        //SystemClock.sleep(1000);
        // Need a better sleep that doesn't crash UI thread lol



    }

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
}
