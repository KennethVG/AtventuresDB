package atventures.be.atventuresdb.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.dao.impl.BaseModelDaoImpl;
import atventures.be.atventuresdb.model.DenkOpdracht;

public class DenkopdrachtenLijstFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<String> lijstDenkopdrachten;
    private DenkOpdracht denkopdracht;

    private BaseModelDao dao;
    private Random random;

    private int[] ids;

    public DenkopdrachtenLijstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        random = new Random();
        ids = new int[9];

        // Sorteer de lijst:
        Arrays.sort(BaseModelDao.DB_TABLES);
//        lijstDenkopdrachten = new ArrayList<>();
//
//        for (int i = 0; i < BaseModelDao.DB_TABLES.length; i++) {
//            lijstDenkopdrachten.add(BaseModelDao.DB_TABLES[i]);
//        }

        // Toon 9 random denkopdrachten:
        lijstDenkopdrachten = new ArrayList<>();
        if (lijstDenkopdrachten.size() <= 1) {
            String[] temp = BaseModelDao.DB_TABLES;
            for (int i = 0; i < 9; ) {
                int r = random.nextInt(temp.length);
                if (!checkForDuplicates(temp[r])) {
                    lijstDenkopdrachten.add(temp[r]);
                    ids[i] = r;
                    i++;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_denkopdrachten_lijst, container, false);
        ListView mLvlijstDenkopdrachten = (ListView) v.findViewById(R.id.lvLijstdenkopdrachten);

//        lijstDenkopdrachten = Arrays.asList(BaseModelDao.DB_TABLES);
        ArrayAdapter<String> questions = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lijstDenkopdrachten);
        mLvlijstDenkopdrachten.setAdapter(questions);
        mLvlijstDenkopdrachten.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        dao = new BaseModelDaoImpl(getActivity(), BaseModelDao.DB_TABLES[ids[position]]);
//        dao = new BaseModelDaoImpl(getActivity(), BaseModelDao.DB_TABLES[position]);
        denkopdracht = new DenkOpdracht();
        String name = lijstDenkopdrachten.get(position+1);
        denkopdracht.setTitle(name);

        // Toon een random raadsel van de gekozen denkopdracht.
        Integer[] aantalRaadsels = dao.getquestions();
        int randomGetal = random.nextInt(aantalRaadsels.length)+1;
        denkopdracht.setText(dao.getInfoFromDB(randomGetal));

        // Load image
        Bitmap bitmap = dao.getImage(position+1);
        if(bitmap != null){
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            denkopdracht.setDrawable(drawable);
        }
        else{
            denkopdracht.setDrawable(getResources().getDrawable(R.drawable.question));
        }
        DenkopdrachtenFragment fragment = new DenkopdrachtenFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DO", denkopdracht);
        fragment.setArguments(bundle);

        System.out.println(denkopdracht);

        getFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    private boolean checkForDuplicates(String item) {
        if (lijstDenkopdrachten.contains(item))
            return true;
        return false;
    }
}
