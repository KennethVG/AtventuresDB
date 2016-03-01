package atventures.be.atventuresdb.fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
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
import atventures.be.atventuresdb.dao.impl.CodeKrakerDaoImpl;

public class CodeKrakerFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lvCodekrakers;
    private ArrayAdapter<String> questions;
    private BaseModelDao dao;

    private EditText eTxtAnswer;
    private String answer;
    private String answerFromDB;

    public CodeKrakerFragment() {
        // Required empty public constructor
        dao = new CodeKrakerDaoImpl(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code_kraker, container, false);
        lvCodekrakers = (ListView) view.findViewById(R.id.lv_codekrakers);

        Integer[] ids = dao.getquestions();
        String[] temp = new String[ids.length];

        for (int i = 0; i < ids.length; i++) {
            temp[i] = "Vraag " + ids[i];
        }

        questions = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, temp);
        lvCodekrakers.setAdapter(questions);
        lvCodekrakers.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int _id = position + 1;
        answerFromDB = dao.getAnswerFromDB(_id);
        showInputDialog();


    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Geef je antwoord op deze vraag: ");
        builder.setIcon(R.drawable.question);
        builder.setView(getViewForDialog());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                answer = eTxtAnswer.getText().toString();
                if (answerFromDB.equalsIgnoreCase(answer)) {
                    Toast.makeText(getActivity(), "GOED ZO!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Paljas is ni just he: " + answerFromDB + " was het goede antwoord!", Toast.LENGTH_LONG).show();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private View getViewForDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_answer, null);
        eTxtAnswer = (EditText) view.findViewById(R.id.etxt_answer);

        return view;
    }
}
