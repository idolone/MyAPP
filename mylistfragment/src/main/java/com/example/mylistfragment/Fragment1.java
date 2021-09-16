package com.example.mylistfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mylistfragment.Util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View fragment1_View;
    private EditText editText;

    public interface OnNewItemAdd{
        public void newItemAdded(String content);
    }

    private OnNewItemAdd  onNewItemAdd;

    public Fragment1() {
        // Required empty public constructor
    }

//    @Override
//    public void onAttach(@NonNull Activity activity) {
//        super.onAttach(activity);
//        onNewItemAdd = (OnNewItemAdd)activity;
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onNewItemAdd = (OnNewItemAdd)context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(fragment1_View == null) {
            fragment1_View = inflater.inflate(R.layout.fragment_1, container, false);
            editText = fragment1_View.findViewById(R.id.et_1);
          }

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                LogUtil.debug("KeyCode:"+keyCode);
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_ENTER){

                        String content = editText.getText().toString();
                        LogUtil.debug("回车键按下---"+content);
                        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
                        onNewItemAdd.newItemAdded(content);
                        editText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });


        return fragment1_View;
    }

}