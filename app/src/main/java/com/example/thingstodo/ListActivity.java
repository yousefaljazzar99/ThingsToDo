package com.example.thingstodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private EditText editText;
    private Button submit;
    private TextView logout;
    private List<Item> itemList;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerView);
        Button createList = findViewById(R.id.btn_add);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(this, itemList);
        recyclerView.setAdapter(recyclerViewAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        createList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog();
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ListActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ListActivity.this, screen3.class);
                startActivity(i);
            }
        });


    }

    private void popUpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.pop_up, null);
        editText = view.findViewById(R.id.edit_text);
        submit = view.findViewById(R.id.submit_list);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().isEmpty()){

                    Item item = new Item();
                    item.setListName(editText.getText().toString());

                    itemList.add(item);

                    recyclerViewAdapter.notifyDataSetChanged();
                    alertDialog.dismiss();

                }
                else{
                    Snackbar.make(v, "Pleas Enter List name ...", Snackbar.LENGTH_SHORT).show();
                }



            }
        });
    }

    Item deletedItem = null;
    Item newItem = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
            | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(itemList, fromPosition, toPosition);

            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedItem = itemList.get(position);
                    itemList.remove(deletedItem);
                    recyclerViewAdapter.notifyItemRemoved(position);
                    String nameList = deletedItem.getListName();
                    Snackbar.make(recyclerView, nameList + " DELETED", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    itemList.add(position, deletedItem);
                                    recyclerViewAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;

                case ItemTouchHelper.RIGHT:
                    newItem = itemList.get(position);

                    builder = new AlertDialog.Builder(ListActivity.this);
                    final View view = getLayoutInflater().inflate(R.layout.pop_up, null);

                    TextView title = view.findViewById(R.id.typing_name);

                    editText = view.findViewById(R.id.edit_text);
                    submit = view.findViewById(R.id.submit_list);

                    title.setText("Edit Item : ");
                    editText.setText(newItem.getListName());
                    submit.setText("Update");

                    builder.setView(view);
                    alertDialog = builder.create();
                    alertDialog.show();


                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //update our item
                            newItem.setListName(editText.getText().toString());

                            if (!editText.getText().toString().isEmpty()){

                                itemList.remove(newItem);
                                recyclerViewAdapter.notifyItemRemoved(position);

                                itemList.add(position, newItem);
                                recyclerViewAdapter.notifyItemInserted(position);
                                alertDialog.dismiss();

                                Snackbar.make(view,"Updated done",Snackbar.LENGTH_SHORT).show();
                            }else {
                                Snackbar.make(view,"Field Empty",Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });

                    break;
            }
        }
    };
}