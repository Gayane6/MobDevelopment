package com.example.labwork2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements General.OnFragmentInteractionListener {

    Result resultFragment;
    General mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultFragment = (Result) getSupportFragmentManager().findFragmentById(R.id.result_fragment);
        mainFragment = (General) getSupportFragmentManager().findFragmentById(R.id.general_fragment);
    }

    @Override
    public void OnClickButtonOK(String message) {


        resultFragment.showResult(message);
    }

   // @Override
    //public void OnClickButtonCancel() {
      //  mainFragment.hideChange();
        //resultFragment.hideResult();
    //}
}
