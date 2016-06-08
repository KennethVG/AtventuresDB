package atventures.be.atventuresdb.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by kvangijsel on 3/06/2016.
 */
public class DenkOpdracht implements Serializable {

    private String title;
    private String text;
    private Drawable drawable;

    public DenkOpdracht() {

    }

    public DenkOpdracht(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public DenkOpdracht(String title, String text, Drawable drawable) {
        this.title = title;
        this.text = text;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }


    @Override
    public String toString() {
        return "DenkOpdracht{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", drawable=" + drawable +
                '}';
    }
}
