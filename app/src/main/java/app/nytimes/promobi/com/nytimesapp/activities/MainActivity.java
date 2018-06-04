package app.nytimes.promobi.com.nytimesapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import app.nytimes.promobi.com.nytimesapp.R;
import app.nytimes.promobi.com.nytimesapp.application.NyTimesApplication;
import app.nytimes.promobi.com.nytimesapp.fragments.MainActivityFragment;
import retrofit2.Retrofit;


public class MainActivity extends Activity {

    @Inject
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((NyTimesApplication) getApplication()) .getNetComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MainActivityFragment)getFragmentManager().findFragmentById(R.id.fragment)).performCall(retrofit);
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
            ((MainActivityFragment)getFragmentManager().findFragmentById(R.id.fragment)).performCall(retrofit);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
