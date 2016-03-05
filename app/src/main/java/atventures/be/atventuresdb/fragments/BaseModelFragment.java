package atventures.be.atventuresdb.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.dao.BaseModelDao;

public class BaseModelFragment extends Fragment implements AdapterView.OnItemClickListener {

    private BaseModelDao dao;

    private EditText eTxtAnswer;
    private String answer;
    private int enveloppe;
    private int code;
    private String answerFromDB;

    public BaseModelFragment() {
        // Required empty public constructor
        //dao = new BaseModelDaoImpl(getActivity());

    }

    public BaseModelFragment(BaseModelDao dao){
        this.dao = dao;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code_kraker, container, false);
        ListView lvCodekrakers = (ListView) view.findViewById(R.id.lv_codekrakers);

        Integer[] ids = dao.getquestions();
        String[] temp = new String[ids.length];

        for (int i = 0; i < ids.length; i++) {
            temp[i] = "Raadsel " + ids[i];
        }

        ArrayAdapter<String> questions = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, temp);
        lvCodekrakers.setAdapter(questions);
        lvCodekrakers.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int _id = position + 1;
        answerFromDB = dao.getAnswerFromDB(_id).trim();
        enveloppe = dao.getEnveloppeFromDB(_id);
        code= dao.getCodeFromDB(_id);
        showInputDialog();


    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Geef je antwoord op deze vraag: ");
        builder.setIcon(R.drawable.question);
        builder.setView(getViewForDialog());
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                answer = eTxtAnswer.getText().toString();
                if (answerFromDB.equalsIgnoreCase(answer)) {
                    showCodeToUnlockSlotDialog();
                } else {
                    Toast.makeText(getActivity(), "Dit was het fout antwoord, probeer het later nog een keer!", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showCodeToUnlockSlotDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Proficiat! Hierbij de code om het slot te bemachtigen: ");
        builder.setIcon(R.drawable.key);
        builder.setMessage(Html.fromHtml(getString(R.string.dialog_message, code , enveloppe)));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private View getViewForDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_answer, null);
        eTxtAnswer = (EditText) view.findViewById(R.id.etxt_answer);
        //eTxtAnswer.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        return view;
    }
}
