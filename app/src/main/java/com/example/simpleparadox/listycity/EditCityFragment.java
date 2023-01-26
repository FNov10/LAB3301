package com.example.simpleparadox.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    private EditText cityName;
    private EditText provinceName;
    private City cityy;

    public EditCityFragment(City cityy) {
        this.cityy = cityy;

    }


    public EditCityFragment() {
    }

    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener{
        void onOkkPressed(City newCity,String newwCity, String newProvince);
    }

    @Override public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;

        }else{
            throw new RuntimeException(context.toString()
                    + "Must implement onfragmentinteractionlistener");
        }

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_city_fragment_layout,null);
        cityName = view.findViewById(R.id.city_name_editText);
        provinceName = view.findViewById(R.id.province_editText);

        //Ensuring City object thats already there appears in alertdialog
        cityName.setText(cityy.getCity());
        provinceName.setText(cityy.getProvince());


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel",null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String city = cityName.getText().toString();
                        String province = provinceName.getText().toString();
                        listener.onOkkPressed(cityy,city,province);
                    }
                }).create();



    }
}
