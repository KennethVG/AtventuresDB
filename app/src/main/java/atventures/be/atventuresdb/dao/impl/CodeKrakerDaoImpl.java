package atventures.be.atventuresdb.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.persistence.DBHelper;

/**
 * Created by kvangijsel on 28/02/2016.
 */
public class CodeKrakerDaoImpl implements BaseModelDao {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public CodeKrakerDaoImpl(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.open();
    }

    @Override
    public String getAnswerFromDB(int _id) {
        String[] columns = {KEY_ANSWER};
        cursor = queryTeambuildingDB(_id, columns);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        } else {
            return "ID NOT FOUND IN DATABASE";
        }
    }

    @Override
    public String getTipFromDB(int _id) {
        String[] columns = {KEY_TIP};
        cursor = queryTeambuildingDB(_id, columns);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        } else {
            return "ID NOT FOUND IN DATABASE";
        }
    }

    @Override
    public int getGradeFromDB(int _id) {
        String[] columns = {KEY_GRADE};
        cursor = queryTeambuildingDB(_id, columns);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return 2;
        }
    }

    @Override
    public Integer[] getquestions() {
        String query = "SELECT _id FROM " + DB_TABLE_CODEKRAKER;
        Cursor cursor = db.rawQuery(query, null);
        List<Integer> ids = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ids.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }

        Integer[] temp = ids.toArray(new Integer[ids.size()]);

        return temp;
    }

    @Override
    public int addQuestionToDB(int _id, String answer, String tip, int grade) {
        return 0;
    }

    private Cursor queryTeambuildingDB(int _id, String[] columns) {
        String tablename = DB_TABLE_CODEKRAKER;
        String[] whereArgs = {String.valueOf(_id)};
        String whereClause = KEY_ROWID + "= ?";
        return db.query(tablename, columns, whereClause, whereArgs, null, null, null);
    }
}
