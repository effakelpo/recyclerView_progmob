package com.bobot.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        preferences = this.getSharedPreferences("My_Pref", MODE_PRIVATE);

        getMyList();
    }

    private void getMyList(){
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Batavia Dana");
        m.setDescription("Saham");
        m.setImg(R.drawable.batavia);
        models.add(m);

        m = new Model();
        m.setTitle("BNI AM");
        m.setDescription("Saham");
        m.setImg(R.drawable.bni);
        models.add(m);

        m = new Model();
        m.setTitle("Danareksa");
        m.setDescription("Saham");
        m.setImg(R.drawable.danareksa);
        models.add(m);

        m= new Model();
        m.setTitle("Majoris");
        m.setDescription("Saham");
        m.setImg(R.drawable.majoris);
        models.add(m);

        m= new Model();
        m.setTitle("Mandiri Investa");
        m.setDescription("Saham");
        m.setImg(R.drawable.mandiri);
        models.add(m);

        m= new Model();
        m.setTitle("Manulife Dana");
        m.setDescription("Saham");
        m.setImg(R.drawable.manulife);
        models.add(m);

        m= new Model();
        m.setTitle("Principal");
        m.setDescription("Saham");
        m.setImg(R.drawable.principal);
        models.add(m);

        m= new Model();
        m.setTitle("Schroder");
        m.setDescription("Saham");
        m.setImg(R.drawable.schroder);
        models.add(m);

        m= new Model();
        m.setTitle("Simas Unggul");
        m.setDescription("Saham");
        m.setImg(R.drawable.simas);
        models.add(m);

        m= new Model();
        m.setTitle("Sucorinvest");
        m.setDescription("Saham");
        m.setImg(R.drawable.sucor);
        models.add(m);

        m= new Model();
        m.setTitle("Trim Sharia");
        m.setDescription("Saham");
        m.setImg(R.drawable.trim);
        models.add(m);

        String mSortSetting = preferences.getString("Sort", "ascending");

        if (mSortSetting.equals("ascending")){
            Collections.sort(models, Model.BY_TITLE_ASCENDING);
        }
        else if(mSortSetting.equals("descending")){
            Collections.sort(models, Model.BY_TITLE_DESCENDING);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, models);
        mRecyclerView.setAdapter(myAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.sorting){
            sortDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortDialog() {
        String[] option = {"Ascending", "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_action_sort);

        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "ascending");
                    editor.apply();
                    getMyList();
                }

                if(which == 1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "descending");
                    editor.apply();
                    getMyList();
                }
            }
        });

        builder.create().show();
    }
}