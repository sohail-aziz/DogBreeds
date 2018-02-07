package aziz.sohail.mvpsample.presentation.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aziz.sohail.mvpsample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreedFragment extends Fragment {


    public BreedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breed, container, false);
    }

}
