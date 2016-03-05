package atventures.be.atventuresdb.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.persistence.DBHelper;

/**
 * Created by kvangijsel on 28/02/2016.
 */
public class BaseModelDaoImpl implements BaseModelDao {

    private SQLiteDatabase db;
    private Cursor cursor;
    private String tableName;

    public BaseModelDaoImpl(Context context, String tableName) {
        DBHelper dbHelper = new DBHelper(context);
        this.tableName = tableName;
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
    public int getCodeFromDB(int _id) {
        String[] columns = {KEY_CODE};
        cursor = queryTeambuildingDB(_id, columns);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return 0000;
        }
    }

    @Override
    public int getEnveloppeFromDB(int _id) {
        String[] columns = {KEY_ENVELOPPE};
        cursor = queryTeambuildingDB(_id, columns);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return 0;
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
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        List<Integer> ids = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ids.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }

        Integer[] temp = ids.toArray(new Integer[ids.size()]);
        cursor.close();
        return temp;
    }

    @Override
    public int addQuestionToDB(int _id, String answer, String tip, int grade) {
        return 0;
    }

    private Cursor queryTeambuildingDB(int _id, String[] columns) {
        String[] whereArgs = {String.valueOf(_id)};
        String whereClause = KEY_ROWID + "= ?";
        return db.query(tableName, columns, whereClause, whereArgs, null, null, null);
    }
}
