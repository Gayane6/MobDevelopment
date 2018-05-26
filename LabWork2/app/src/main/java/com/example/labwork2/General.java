package com.example.labwork2;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;

import static android.content.Context.MODE_PRIVATE;


public class General extends Fragment {

    Button okButton, cancelButton;
    ListView listView;
    RadioGroup radioGroup;
    TextView result;

    Spinner spinner;
    public String message = "Користувач обрав: \n",message1="",message2="";
    String[] phone = {"Смартфон ", "Смартфон с 2-мя сим-картами ", "Смартфон с fingerprint "};

    private OnFragmentInteractionListener mListener;

    public General() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okButton = view.findViewById(R.id.btnOk);
        //cancelButton = view.findViewById(R.id.btnCancel);
        result = view.findViewById(R.id.textViewRes);
        radioGroup = view.findViewById(R.id.radioGroup);
        spinner = view.findViewById(R.id.spinner);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, phone);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                message=item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                message+="Фірма: ";
                String message1;
                switch (checkedId) {
                    case -1:
                        message1="не обрано \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonSamsung:
                        message1="Samsung \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonXiaomi:
                        message1="Xiaomi \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonHuawei:
                        message1="Huawei \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonApple:
                        message1="Apple \n";
                        message+=message1;
                        break;
                    case R.id.radioButtonMeizu:
                        message1="Meizu \n";
                        message+=message1;
                        break;
                    default:
                        break;
                }
            }
        });

        okButton.setOnClickListener(v ->mListener.OnClickButtonOK(message));


        //cancelButton.setOnClickListener(v -> mListener.OnClickButtonCancel());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void hideChange(){
        result.setText("");
    }

    public interface OnFragmentInteractionListener {
        void OnClickButtonOK(String message);
        //void OnClickButtonCancel();
    }
}