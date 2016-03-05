package atventures.be.atventuresdb.fragments;

import android.app.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import atventures.be.atventuresdb.R;

public class MainActivityFragment extends Fragment {

    private Button btnVragen, btnAntwoorden;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Reference to views
        btnVragen = (Button) view.findViewById(R.id.btn_vragen);
        btnAntwoorden = (Button) view.findViewById(R.id.btn_antwoorden);

        // Change fragment
        btnVragen.setOnClickListener(changeFragment);
        btnAntwoorden.setOnClickListener(changeFragment);
        return view;
    }

    private View.OnClickListener changeFragment = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            VragenFragment vf = new VragenFragment();
            AntwoordenFragment af = new AntwoordenFragment();

            if (v == btnVragen) {
                getFragmentManager().beginTransaction().replace(R.id.container, vf).addToBackStack(null).commit();
            } else if (v == btnAntwoorden) {
                getFragmentManager().beginTransaction().replace(R.id.container, af).addToBackStack(null).commit();
            }
        }
    };


}
