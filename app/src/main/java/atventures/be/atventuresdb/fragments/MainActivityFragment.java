package atventures.be.atventuresdb.fragments;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import atventures.be.atventuresdb.R;

public class MainActivityFragment extends Fragment {

    private Button mBtnDenkopdrachten, mBtnAntwoorden;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Reference to views
        mBtnDenkopdrachten = (Button) view.findViewById(R.id.btn_denkopdrachten);
        mBtnAntwoorden = (Button) view.findViewById(R.id.btn_antwoorden);

        // Change fragment
        mBtnDenkopdrachten.setOnClickListener(changeFragment);
        mBtnAntwoorden.setOnClickListener(changeFragment);
        return view;
    }

    private View.OnClickListener changeFragment = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DenkopdrachtenLijstFragment df = new DenkopdrachtenLijstFragment();
            AntwoordenFragment af = new AntwoordenFragment();

            if (v == mBtnDenkopdrachten) {
                getFragmentManager().beginTransaction().replace(R.id.container, df).addToBackStack(null).commit();
            } else if (v == mBtnAntwoorden) {
                getFragmentManager().beginTransaction().replace(R.id.container, af).addToBackStack(null).commit();
            }
        }
    };


}
