package com.rushabh.todolistapp;

import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rushabh.todolistapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fbAdd;
    private ListView Ltlist;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    private TextView tvListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemDialog();
            }
        });
    }

    public void bindID() {
        fbAdd = (FloatingActionButton) findViewById(R.id.fbAdd);
        Ltlist = (ListView) findViewById(R.id.Ltlist);
        tvListView = (TextView) findViewById(R.id.tvListView);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.raw_data_list,R.id.tvListView, arrayList);
        Ltlist.setAdapter(adapter);
    }


    public void addItemDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.add_item_layout, null);
        bottomSheetDialog.setContentView(view);
        EditText edEditTask = (EditText) view.findViewById(R.id.edEdtTask);
        Button btCancel = (Button) view.findViewById(R.id.btCancel);
        Button btAdd = (Button) view.findViewById(R.id.btAdd);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edItem = edEditTask.getText().toString();
                if (!edItem.isEmpty()) {
                    arrayList.add(edItem);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    //dismiss dialog once item is added successfully
                    bottomSheetDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), " Please Select", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        bottomSheetDialog.show();
    }
}
