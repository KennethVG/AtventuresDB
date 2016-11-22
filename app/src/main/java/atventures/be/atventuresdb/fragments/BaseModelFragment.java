package atventures.be.atventuresdb.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.MainActivity;
import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.dao.impl.BaseModelDaoImpl;

public class BaseModelFragment extends Fragment implements AdapterView.OnItemClickListener {

    private BaseModelDao dao;

    private EditText eTxtAnswer;
    private EditText eTxtPassword;
    private TextView txtvResult;
    private TextView txtvInfo;
    private Button btnValidate, btnTerug;

    private String answer;
    private int enveloppe;
    private int code;

    private String answerFromDB;
    private String tipFromDB;

    private AlertDialog inputDialog;

    public BaseModelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get tablename (naam denkopdracht) from AntwoordenFragment.
        dao = new BaseModelDaoImpl(getActivity(), getArguments().getString("table"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basemodel, container, false);
        ListView lvDenkopdrachten = (ListView) view.findViewById(R.id.lv_basemodel);

        // Haal de denkopdrachten van de database en plaats ze in een array
        Integer[] ids = dao.getquestions();
        String[] temp = new String[ids.length];

        for (int i = 0; i < ids.length; i++) {
            temp[i] = BaseModelDao.RIDDLE + ids[i];
        }

        // Toon de gegevens van de array in de Listview:
        ArrayAdapter<String> questions = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, temp);
        lvDenkopdrachten.setAdapter(questions);
        lvDenkopdrachten.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int _id = position + 1;
        answerFromDB = dao.getAnswerFromDB(_id).trim();
        enveloppe = dao.getEnveloppeFromDB(_id);
        code = dao.getCodeFromDB(_id);
        tipFromDB = dao.getTipFromDB(_id).trim();
        showInputDialog();
    }

    // Helper methode om dialoog te tonen zodat de gebruiker antwoord kan ingeven:
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Geef je antwoord op deze vraag: ");
        builder.setIcon(R.drawable.help);
        builder.setView(getViewForInputDialog());
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                answer = eTxtAnswer.getText().toString().trim();
                if (answerFromDB.equalsIgnoreCase(answer)) {
                    showCodeToUnlockSlotDialog();
                } else {
                    Toast.makeText(getActivity(), "Dit was het fout antwoord, probeer het later nog een keer!", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNeutralButton("Ik wil een tip!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showPassWordForTipDialog();
            }
        });
        builder.setNegativeButton("Toon de oplossing!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showPassWordForSolutionDialog();
            }
        });

        inputDialog = builder.create();
        inputDialog.show();
    }

    // Helper methode om info te tonen
    private void showCodeToUnlockSlotDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Proficiat! Hierbij de code om het slot te bemachtigen: ");
        builder.setIcon(R.drawable.solution);
        builder.setMessage(Html.fromHtml(getString(R.string.dialog_oplossing, code, enveloppe)));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                returnToHomescreen();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Helper methode om tip dialoog te tonen
    private void showPassWordForTipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("TIP ");
        builder.setIcon(R.drawable.light);
        builder.setView(getViewForPasswordDialog());
        txtvInfo.setText(R.string.dialog_info_tip);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eTxtPassword.getText().toString().equalsIgnoreCase(String.valueOf(BaseModelDao.PASSWORD))) {
                    txtvResult.setText("Tip: " + tipFromDB);
                } else {
                    txtvResult.setText("Foute paswoord!");
                }
            }
        });
        btnTerug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToHomescreen();
            }
        });
    }

    // Helper methode om tip dialoog te tonen
    private void showPassWordForSolutionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Oplossing ");
        builder.setIcon(R.drawable.solution);
        builder.setView(getViewForPasswordDialog());
        txtvInfo.setText(R.string.dialog_info_oplossing);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eTxtPassword.getText().toString().equalsIgnoreCase(String.valueOf(BaseModelDao.PASSWORD))) {
                    txtvResult.setText("Oplossing: " + Html.fromHtml(getString(R.string.dialog_oplossing, code, enveloppe)));
                } else {
                    txtvResult.setText("Foute paswoord!");
                }
            }
        });
        btnTerug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToHomescreen();
            }
        });
    }

    //  Specifieke view plaatsen in de input dialoog:
    private View getViewForInputDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_answer, null);
        eTxtAnswer = (EditText) view.findViewById(R.id.etxt_answer);
        //eTxtAnswer.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        return view;
    }

    //  Specifieke view plaatsen in de input dialoog:
    private View getViewForPasswordDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_tip, null);
        eTxtPassword = (EditText) view.findViewById(R.id.etxt_password);
        btnTerug = (Button) view.findViewById(R.id.btn_terug);
        btnValidate = (Button) view.findViewById(R.id.btn_valideer);
        txtvResult = (TextView) view.findViewById(R.id.txt_result);
        txtvInfo = (TextView) view.findViewById(R.id.txtv_info);
        return view;
    }

    private void returnToHomescreen() {
        // Return to homescreen
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
    }

}
