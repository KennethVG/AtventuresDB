package atventures.be.atventuresdb.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.dao.BaseModelDao;

public class AntwoordenFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lvTableNames;
    private ArrayAdapter<String> items;

    public AntwoordenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_antwoorden, container, false);
        lvTableNames = (ListView) view.findViewById(R.id.lv_tablenames);
        items = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, BaseModelDao.DB_TABLES);
        lvTableNames.setAdapter(items);
        lvTableNames.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(items.getItem(position).equalsIgnoreCase(BaseModelDao.DB_TABLE_CODEKRAKER)){
            getFragmentManager().beginTransaction().replace(R.id.container, new CodeKrakerFragment()).commit();
        }

        Toast.makeText(getActivity(), items.getItem(position), Toast.LENGTH_LONG).show();
    }
}
