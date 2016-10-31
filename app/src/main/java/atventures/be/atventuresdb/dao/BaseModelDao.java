package atventures.be.atventuresdb.dao;

import android.graphics.Bitmap;

public interface BaseModelDao {

    // CONSTANTEN:
    // Naam van de database:
    String DB_NAME = "Teambuilding.db";

    // Kolomnamen in elke tabel:
    String KEY_ROWID = "_id";
    String KEY_ANSWER = "antwoord";
    String KEY_TIP = "tip";
    String KEY_GRADE = "graad";
    String KEY_CODE = "cijferslot";
    String KEY_ENVELOPPE = "enveloppe";
    String KEY_INFO = "info";
    String KEY_IMAGE = "image";

    // Tabellen in de database:
    String DB_TABLE_CODEKRAKER = "Codekraker";
    String DB_TABLE_CRYPTOGRAM = "Cryptogram";
    String DB_TABLE_DROEDEL = "Droedel";
    String DB_TABLE_MASTERMIND = "Mastermind";
    String DB_TABLE_SUDOKU = "Sudoku";
    String DB_TABLE_WOORDZOEKER = "Woordzoeker";
    String DB_TABLE_MOBIELE_CODE = "Mobiele_Code";
    String DB_TABLE_LINGO = "Lingo";
    String DB_TABLE_PROEFOPDRACHT = "Proefopdracht";
    String DB_TABLE_PERSOONLIJKE_OPDRACHT = "Persoonlijke_opdracht";
    String DB_TABLE_PUZZEL = "Puzzel";
    String DB_TABLE_RAADSEL = "Raadsel";
    String DB_TABLE_TECTONIC = "Tectonic";
    String DB_TABLE_GELUID = "Geluid";
    String DB_TABLE_GETALLENREEKS = "Getallenreeks";
    String DB_TABLE_VARIA = "Varia";

    // Tabelnamen:
    String[] DB_TABLES = {DB_TABLE_CODEKRAKER, DB_TABLE_CRYPTOGRAM, DB_TABLE_DROEDEL,
            DB_TABLE_MASTERMIND, DB_TABLE_SUDOKU, DB_TABLE_WOORDZOEKER, DB_TABLE_MOBIELE_CODE,
            DB_TABLE_LINGO, DB_TABLE_PROEFOPDRACHT, DB_TABLE_GETALLENREEKS, DB_TABLE_GELUID,
            DB_TABLE_PERSOONLIJKE_OPDRACHT, DB_TABLE_TECTONIC, DB_TABLE_RAADSEL, DB_TABLE_PUZZEL, DB_TABLE_VARIA};


    // Andere:
    String RIDDLE = "Raadsel ";
    String DENKOPDRACHT = "Denkopdracht: ";
    String PASSWORD = "0413";

    // METHODS:
    String getAnswerFromDB(int _id);

    String getTipFromDB(int _id);

    String getInfoFromDB(int _id);

    int getGradeFromDB(int _id);

    int getCodeFromDB(int _id);

    int getEnveloppeFromDB(int _id);

    int addQuestionToDB(int _id, String answer, String tip, int grade);

    Integer[] getquestions();

    Bitmap getImage(int _id);
}
