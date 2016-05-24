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

    private ArrayAdapter<String> items;

    public AntwoordenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_antwoorden, container, false);
        ListView lvTableNames = (ListView) view.findViewById(R.id.lv_tablenames);
        Arrays.sort(BaseModelDao.DB_TABLES);
        items = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, BaseModelDao.DB_TABLES);
        lvTableNames.setAdapter(items);
        lvTableNames.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        BaseModelFragment cf = new BaseModelFragment(new BaseModelDaoImpl(getActivity(),BaseModelDao.DB_TABLES[position]));

       // if(items.getItem(position).equalsIgnoreCase(BaseModelDao.DB_TABLE_CODEKRAKER)){
            getFragmentManager().beginTransaction().replace(R.id.container, cf).addToBackStack(null).commit();
        //}

//        String tableName= items.getItem(position);
//        for (String table : BaseModelDao.DB_TABLES){
//            if(tableName.equalsIgnoreCase(table)){
//                Fragment fragment = Fragment.instantiate(getActivity(),table + "Fragment");
//                getFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
//            }
//        }


    }
}
