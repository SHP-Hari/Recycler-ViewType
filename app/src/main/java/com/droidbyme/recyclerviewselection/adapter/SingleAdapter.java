package com.droidbyme.recyclerviewselection.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.activity.MultipleFragments;
import com.droidbyme.recyclerviewselection.activity.SubjectsFragment;
import com.droidbyme.recyclerviewselection.model.Employee;

import java.util.ArrayList;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.SingleViewHolder> {

    private Context context;
    private ArrayList<Employee> employees;
    private FragmentManager fragmentManager;
    RecyclerView recyclerView;
    FrameLayout frameLayout;

    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = 0;

    public SingleAdapter(Context context, ArrayList<Employee> employees, FragmentManager fm) {
        this.context = context;
        this.employees = employees;
        this.fragmentManager = fm;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = new ArrayList<>();
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, viewGroup, false);
        recyclerView = ((MultipleFragments)context).findViewById(R.id.recyclerView);
        frameLayout = ((MultipleFragments)context).findViewById(R.id.fragment_placeholder);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int position) {
        singleViewHolder.bind(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void bind(final Employee employee) {
            if (checkedPosition == -1) {
                imageView.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    imageView.setVisibility(View.VISIBLE);

//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
            textView.setText(employee.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                    loadFragment(new SubjectsFragment(), "SUBJECT");
                }
            });
        }
    }

    public Employee getSelected() {
        if (checkedPosition != -1) {
            return employees.get(checkedPosition);
        }
        return null;
    }

    private void loadFragment(Fragment fragment, String fragmentTag){
        recyclerView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, fragment, fragmentTag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //invalidateOptionsMenu();
    }
}