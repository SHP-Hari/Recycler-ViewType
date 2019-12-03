package com.droidbyme.recyclerviewselection.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.adapter.SingleAdapter;
import com.droidbyme.recyclerviewselection.model.Employee;

import java.util.ArrayList;

public class MultipleFragments extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView recyclerView;
    private ArrayList<Employee> employees = new ArrayList<>();
    private SingleAdapter adapter;
    private android.support.v7.widget.AppCompatButton btnGetSelected;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_fragments);
        this.btnGetSelected = (AppCompatButton) findViewById(R.id.btnGetSelected);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.frameLayout = (FrameLayout) findViewById(R.id.fragment_placeholder);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Single Selection");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        FragmentManager fm = getFragmentManager();
        adapter = new SingleAdapter(this, employees, fm);
        recyclerView.setAdapter(adapter);

        createList();

        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getSelected() != null) {
                    showToast(adapter.getSelected().getName());
                    loadFragment(new SubjectsFragment(), "SUBJECT");
                } else {
                    showToast("No Selection");
                }
            }
        });
    }

    private void loadFragment(Fragment fragment, String fragmentTag){
        recyclerView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, fragment, fragmentTag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        invalidateOptionsMenu();
    }

    private void createList() {
        employees = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Employee employee = new Employee();
            employee.setName("Employee " + (i + 1));
            employees.add(employee);
        }
        adapter.setEmployees(employees);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
