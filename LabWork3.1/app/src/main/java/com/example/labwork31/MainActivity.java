package com.example.labwork31;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    String[] phone = {"Смартфон ", "Смартфон с 2-мя сим-картами ", "Смартфон с fingerprint "};
    private final static String FILE_NAME = "content.txt";

    public String message = "Выбор пользователя: \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.phone);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, phone);
        // Определяем разметку для использования при выборе элемента
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

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                message+="Фирма: ";
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

    }

    public void saveText(View view){

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(message.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openText(View view) {

        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.textViewRes);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            //text+=getFileStreamPath(FILE_NAME);
            textView.setText(text);
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OnClickButtonOK(View view) {
        //TextView result = (TextView)findViewById(R.id.textViewRes);
        //result.setText(message);

        saveText(view);
    }

    public void OnClickButtonCancel(View view) {
        deleteFile(FILE_NAME);
        TextView result = (TextView)findViewById(R.id.textViewRes);
        result.setText("");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        message="Выбор пользователя: \n";
    }

    public void OnClickButtonOpen(View view) {
        openText(view);
    }

}