package com.droidbyme.recyclerviewselection.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.adapter.CardAdapter;
import com.droidbyme.recyclerviewselection.adapter.SingleAdapter;
import com.droidbyme.recyclerviewselection.model.Employee;
import com.droidbyme.recyclerviewselection.model.Planet;

import java.util.ArrayList;


public class SubjectsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private android.support.v7.widget.RecyclerView recyclerView;
    private android.support.v7.widget.RecyclerView recyclerView2;
    FrameLayout frameLayout;
    private CardAdapter adapter;
    private ArrayList<Planet> planetArrayList;
    private android.support.v7.widget.AppCompatButton btnGetSelected;

    // TODO: Rename and change types of parameters
    private String mParam1;


    public SubjectsFragment() {
        // Required empty public constructor
    }


    public static SubjectsFragment newInstance(String param1) {
        SubjectsFragment fragment = new SubjectsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView2 = getActivity().findViewById(R.id.recyclerView);
        frameLayout = getActivity().findViewById(R.id.fragment_placeholder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        planetArrayList = new ArrayList<>();
        adapter = new CardAdapter(getActivity(), planetArrayList);
        recyclerView.setAdapter(adapter);

        createListData();

        return view;
    }

    private void createListData() {
        Planet planet = new Planet("Earth", 150, 10, 12750);
        planetArrayList.add(planet);
        planet = new Planet("Jupiter", 778, 26, 143000);
        planetArrayList.add(planet);
        planet = new Planet("Mars", 228, 4, 6800);
        planetArrayList.add(planet);
        planet = new Planet("Pluto", 5900, 1, 2320);
        planetArrayList.add(planet);
        planet = new Planet("Venus", 108, 9, 12750);
        planetArrayList.add(planet);
        planet = new Planet("Saturn", 1429, 11, 120000);
        planetArrayList.add(planet);
        planet = new Planet("Mercury", 58, 4, 4900);
        planetArrayList.add(planet);
        planet = new Planet("Neptune", 4500, 12, 50500);
        planetArrayList.add(planet);
        planet = new Planet("Uranus", 2870, 9, 52400);
        planetArrayList.add(planet);
        adapter.notifyDataSetChanged();
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        recyclerView2.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);
    }
}
