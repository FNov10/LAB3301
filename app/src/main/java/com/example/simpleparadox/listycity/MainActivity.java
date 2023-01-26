package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener,EditCityFragment.OnFragmentInteractionListener {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;
    ListView item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlertDialog.Builder builder;
        cityList = findViewById(R.id.city_list);
        builder = new AlertDialog.Builder(this);

        String []cities ={"Edmonton", "Vancouver"};
        String []provinces = {"AB","BC"};

        dataList = new ArrayList<>();

        for(int i =0;i<cities.length; i++){
            dataList.add(new City(cities[i],provinces[i]));
        }



        cityAdapter = new CustomList(this,dataList);


        cityList.setAdapter(cityAdapter);




        final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCityFragment().show(getSupportFragmentManager(),"AdDD_CITY");
            }

            });


        final ListView pp = findViewById(R.id.city_list);


        //Listener setup To Delete the city
        pp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                builder.setMessage("Do you want to Delete "+ cityAdapter.getItem(i).getCity() + "?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                City removal = cityAdapter.getItem(i);
                                DeleteCity(removal);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Delete Confirmation");
                alert.show();

                return true;
            }
        });

        //Listener setup to edit the city
        pp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index = i;
                City selectedCity = cityAdapter.getItem(i);
                new EditCityFragment(selectedCity).show(getSupportFragmentManager(),"AdDD_CITY");
            }
        });






    }

    Toast t;

    private void makeToast(String s){
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        t.show();

    };
    @Override
    public void onOkPressed(City newCity){
        cityAdapter.add(newCity);


    }
    @Override
    //This takes in the city with new parameters from the editText and sets the current citys attributes to the new parameters
    public void onOkkPressed(City newCity,String newwCity,String newProvince){
        if(newwCity!= null && newProvince!=null){
            newCity.setCity(newwCity);
            newCity.setProvince(newProvince);
        }

        else{
            makeToast("Can't be empty!!");
        }

        //cityAdapter.add(newCity);


    }

    public void DeleteCity(City city){
        cityAdapter.remove(city);
    }





}
