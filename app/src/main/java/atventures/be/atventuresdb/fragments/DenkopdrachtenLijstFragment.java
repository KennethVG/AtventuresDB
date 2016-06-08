package atventures.be.atventuresdb.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.model.DenkOpdracht;

public class DenkopdrachtenLijstFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<String> lijstDenkopdrachten;
    private ListView mLvlijstDenkopdrachten;
    private DenkOpdracht denkopdracht = new DenkOpdracht();

    public DenkopdrachtenLijstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_denkopdrachten_lijst, container, false);
        mLvlijstDenkopdrachten = (ListView) v.findViewById(R.id.lvLijstdenkopdrachten);

        Random random = new Random();
        lijstDenkopdrachten = new ArrayList<>();
        if (lijstDenkopdrachten.size() <= 1) {
            String[] temp = BaseModelDao.DB_TABLES;
            for (int i = 0; i < 9; ) {
                int r = random.nextInt(temp.length);
                if (!checkForDuplicates(temp[r])) {
                    lijstDenkopdrachten.add(temp[r]);
                    i++;
                }
            }
        }
        ArrayAdapter<String> questions = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lijstDenkopdrachten);
        mLvlijstDenkopdrachten.setAdapter(questions);
        mLvlijstDenkopdrachten.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String name = lijstDenkopdrachten.get(position);
        denkopdracht.setTitle(name);
        denkopdracht.setText("TEST");
        denkopdracht.setDrawable(getResources().getDrawable(R.drawable.lingo1));
        System.out.println("onItemClick: " + denkopdracht);

        DenkopdrachtenFragment fragment = new DenkopdrachtenFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DO", denkopdracht);
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    private boolean checkForDuplicates(String item) {
        if (lijstDenkopdrachten.contains(item))
            return true;
        return false;
    }
}
