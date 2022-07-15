package com.rushabh.todolistapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fbAdd;
    private ListView Ltlist;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
        //Adding new todo item
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
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.raw_data_list, R.id.tvListView, arrayList);
        Ltlist.setAdapter(adapter);
        Ltlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = Ltlist.getChildAt(position);
                TextView ctv = (TextView) v.findViewById(R.id.tvListView);
                ImageView imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
                imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Delete selected item
                        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                        adb.setTitle("Delete?");
                        adb.setMessage("Are you sure you want to delete ? ");
                        adb.setNegativeButton("Cancel", null);
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                int count = adapter.getCount();
                                for (int i = 0; i < arrayList.size(); i++)
                                    if (i == position) {
                                        adapter.remove(adapter.getItem(position));
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(MainActivity.this, "Item is deleted successfully", Toast.LENGTH_SHORT).show();
                                    }

                            }
                        });
                        adb.show();
                    }
                });
            }
        });
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
                    Toast.makeText(MainActivity.this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
                    //dismiss dialog once item is added successfully
                    bottomSheetDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), " Please Add atleast one item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }
}
