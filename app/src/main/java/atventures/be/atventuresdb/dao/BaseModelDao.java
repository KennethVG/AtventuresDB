package atventures.be.atventuresdb.dao;

/**
 * Created by kvangijsel on 28/02/2016.
 */
public interface BaseModelDao {

    // CONSTANTEN:
    // Naam van de database:
    public static final String DB_NAME = "Teambuilding.db";

    // Kolomnamen in elke tabel:
    public static final String KEY_ROWID = "_id";
    public static final String KEY_ANSWER = "antwoord";
    public static final String KEY_TIP = "tip";
    public static final String KEY_GRADE ="graad";

    // Tabellen in de database:
    public static final String DB_TABLE_CODEKRAKER = "Codekraker";
    public static final String DB_TABLE_CRYPTOGRAM = "Cryptogram";
    public static final String DB_TABLE_DROEDEL = "Droedel";
    public static final String DB_TABLE_MASTERMIND = "Mastermind";
    public static final String DB_TABLE_SUDOKU = "Sudoku";
    public static final String DB_TABLE_WOORDZOEKER = "Woordzoeker";

    // Tabelnamen:
    public static final String [] DB_TABLES = {DB_TABLE_CODEKRAKER, DB_TABLE_CRYPTOGRAM, DB_TABLE_DROEDEL, DB_TABLE_MASTERMIND, DB_TABLE_SUDOKU, DB_TABLE_WOORDZOEKER};

    // METHODS:
    String getAnswerFromDB(int _id);
    String getTipFromDB(int _id);
    int getGradeFromDB(int _id);
    int addQuestionToDB(int _id, String answer, String tip, int grade);
    Integer[] getquestions();
}
