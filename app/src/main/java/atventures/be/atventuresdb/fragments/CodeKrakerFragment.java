package atventures.be.atventuresdb.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.dao.impl.CodeKrakerDaoImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeKrakerFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lvCodekrakers;
    private ArrayAdapter<String> questions;
    private BaseModelDao dao;

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
        String [] temp = new String[ids.length];

        for (int i = 0; i<ids.length;i++){
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

    }
}
