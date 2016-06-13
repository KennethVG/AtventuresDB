package atventures.be.atventuresdb.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import atventures.be.atventuresdb.dao.BaseModelDao;

/**
 * Created by kvangijsel on 28/02/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DATABASEHELPER";

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = BaseModelDao.DB_NAME;
    private static final String DB_PATH =  "/data/data/atventures.be.atventuresdb/databases/";

    private Context context;
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public void createDatabase() {
        //If database not exists copy it from the assets
        boolean dataBaseExist = checkDataBase();
        if (!dataBaseExist) {
            this.getWritableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase: database created");
            } catch (IOException mIOException) {
                throw new RuntimeException("Error copying database");
            }
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        String dbPath = DB_PATH + DB_NAME;
        OutputStream output = new FileOutputStream(dbPath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }

    //Open the database, so we can query it
    public SQLiteDatabase open() {
        String path = DB_PATH + DB_NAME;
        Log.i("Path: ", path);
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
