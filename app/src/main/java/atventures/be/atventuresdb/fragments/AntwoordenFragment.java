package atventures.be.atventuresdb.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.dao.impl.BaseModelDaoImpl;

public class AntwoordenFragment extends Fragment implements AdapterView.OnItemClickListener {

    public AntwoordenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_antwoorden, container, false);
        ListView lvTableNames = (ListView) view.findViewById(R.id.lv_tablenames);
        Arrays.sort(BaseModelDao.DB_TABLES);
        ArrayAdapter<String> items = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, BaseModelDao.DB_TABLES);
        lvTableNames.setAdapter(items);
        lvTableNames.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BaseModelFragment cf = new BaseModelFragment();
        Bundle bundle = new Bundle();
        bundle.putString("table", BaseModelDao.DB_TABLES[position]);
        cf.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.container, cf).addToBackStack(null).commit();
    }
}
