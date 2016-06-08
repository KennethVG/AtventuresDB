package atventures.be.atventuresdb;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.fragments.AntwoordenFragment;
import atventures.be.atventuresdb.fragments.MainActivityFragment;
import atventures.be.atventuresdb.persistence.DBHelper;

public class MainActivity extends Activity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load MainActivityFragment
        getFragmentManager().beginTransaction().add(R.id.container, new MainActivityFragment()).commit();

        // Create database if not exists
        db = new DBHelper(this);
        db.createDatabase();
    }

    // Load menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Action on menu-items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPause() {
        super.onPause();
        db.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        db.open();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
