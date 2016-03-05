package atventures.be.atventuresdb.model;

/**
 * Created by kvangijsel on 28/02/2016.
 */
public class BaseModel {

    private int _id;
    private String answer;
    private String tip;
    private int grade;
    private int code;
    private int enveloppe;

    public BaseModel(){

    }

    public BaseModel(int _id, String answer, String tip, int grade, int code, int enveloppe) {
        this._id = _id;
        this.answer = answer;
        this.tip = tip;
        this.grade = grade;
        this.code = code;
        this.enveloppe = enveloppe;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getEnveloppe() {
        return enveloppe;
    }

    public void setEnveloppe(int enveloppe) {
        this.enveloppe = enveloppe;
    }
}
