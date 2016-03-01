package atventures.be.atventuresdb.dao;

/**
 * Created by kvangijsel on 28/02/2016.
 */
public interface BaseModelDao {

    // CONSTANTEN:
    // Naam van de database:
    String DB_NAME = "Teambuilding.db";

    // Kolomnamen in elke tabel:
    String KEY_ROWID = "_id";
    String KEY_ANSWER = "antwoord";
    String KEY_TIP = "tip";
    String KEY_GRADE ="graad";

    // Tabellen in de database:
    String DB_TABLE_CODEKRAKER = "Codekraker";
    String DB_TABLE_CRYPTOGRAM = "Cryptogram";
    String DB_TABLE_DROEDEL = "Droedel";
    String DB_TABLE_MASTERMIND = "Mastermind";
    String DB_TABLE_SUDOKU = "Sudoku";
    String DB_TABLE_WOORDZOEKER = "Woordzoeker";

    // Tabelnamen:
    String [] DB_TABLES = {DB_TABLE_CODEKRAKER, DB_TABLE_CRYPTOGRAM, DB_TABLE_DROEDEL, DB_TABLE_MASTERMIND, DB_TABLE_SUDOKU, DB_TABLE_WOORDZOEKER};

    // METHODS:
    String getAnswerFromDB(int _id);
    String getTipFromDB(int _id);
    int getGradeFromDB(int _id);
    int addQuestionToDB(int _id, String answer, String tip, int grade);
    Integer[] getquestions();
}
