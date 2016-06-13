package atventures.be.atventuresdb.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import atventures.be.atventuresdb.R;
import atventures.be.atventuresdb.dao.BaseModelDao;
import atventures.be.atventuresdb.dao.impl.BaseModelDaoImpl;
import atventures.be.atventuresdb.model.DenkOpdracht;

public class DenkopdrachtenFragment extends Fragment {

    private TextView mTxtvtitle;
    private TextView mTxtvOpdracht;
    private ImageView mImgOpdracht;
    private static boolean RETURN = false;

    private DenkOpdracht denkOpdracht;

    public DenkopdrachtenFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        denkOpdracht = (DenkOpdracht) bundle.getSerializable("DO");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_denkopdrachten, container, false);

        mTxtvOpdracht = (TextView) view.findViewById(R.id.txtvOpdracht);
        mTxtvtitle = (TextView) view.findViewById(R.id.txtvTitle);
        mImgOpdracht = (ImageView) view.findViewById(R.id.imgOpdracht);

        if (denkOpdracht != null) {
            mTxtvOpdracht.setText(denkOpdracht.getText());
            mTxtvtitle.setText(denkOpdracht.getTitle());
            mImgOpdracht.setImageDrawable(denkOpdracht.getDrawable());
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // TO DO: Reload same List

    }
}
