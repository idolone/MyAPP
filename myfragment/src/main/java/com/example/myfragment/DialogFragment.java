package com.example.myfragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragment extends androidx.fragment.app.DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogFragment newInstance(String param1, String param2) {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static DialogFragment getInstance(int type){
        DialogFragment dialogFragment = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Dialog_Type",type);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        int dialog_type = getArguments().getInt("Dialog_Type");
        switch (dialog_type){
            case MainActivity.DIALOG_TYPE_ALERT:
                return new AlertDialog.Builder(getActivity())
                        .setIcon(R.drawable.ic_launcher_background)
                        .setTitle(getTag())
                        .setPositiveButton("yes",new DialogInterface.OnClickListener(){


                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getActivity(),"你点击了yes",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("cancle",new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(),"你点击了cancel",Toast.LENGTH_SHORT).show();
                            }
                        }).create();

            case MainActivity.DIALOG_TYPE_DATE:
                Calendar c1 = Calendar.getInstance();
                int year = c1.get(Calendar.YEAR);
                int monthOfYear = c1.get(Calendar.MONTH);
                int dayOfMonth = c1.get(Calendar.DAY_OF_MONTH);

                return  new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   Toast.makeText(getActivity(),"日期:"+year+"-"+month+"-"+dayOfMonth,
                        Toast.LENGTH_SHORT).show();
                    }
                },year,monthOfYear,dayOfMonth);

            case MainActivity.DIALOG_TYPE_TIME:
                Calendar c2 = Calendar.getInstance();
                int hourofday = c2.get(Calendar.HOUR_OF_DAY);
                int minute = c2.get(Calendar.MINUTE);
                boolean is24HourView = true;
                return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getActivity(),"时间:"+hourOfDay+":"+minute,
                                Toast.LENGTH_SHORT).show();
                    }
                },hourofday,minute,is24HourView);

        }
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }
}