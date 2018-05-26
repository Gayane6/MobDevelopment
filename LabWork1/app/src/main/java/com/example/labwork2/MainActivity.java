package com.example.labwork2;


        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;

        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.TextView;
        import android.widget.RadioGroup;
        import android.widget.RadioButton;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {


    String[] phone = {"Смартфон", "Смартфон с 2-мя сим-картами", "Смартфон с fingerprint"};
    //TextView selection;
    public String message1 = "User chosen: \n";
    public String message;
    TextView selection;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = (TextView) findViewById(R.id.selection);

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
                //String item = (String) parent.getItemAtPosition(position);
                //selection.setText(item);
                //String mes = phone[position];
                //message+=mes;
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
                message += " Company: ";
                String message1;
                switch (checkedId) {
                    case -1:
                        message1 = "not chose \n";
                        message += message1;
                        break;
                    case R.id.radioButtonSamsung:
                        message1 = "Samsung J5 \n";
                        message += message1;
                        break;
                    case R.id.radioButtonXiaomi:
                        message1 = "Xiaomi 4A\n";
                        message += message1;
                        break;
                    case R.id.radioButtonHuawei:
                        message1 = "Huawei X5\n";
                        message += message1;
                        break;
                    case R.id.radioButtonApple:
                        message1 = "iPhone 5s \n";
                        message += message1;
                        break;
                    case R.id.radioButtonMeizu:
                        message1 = "Meizu M6\n";
                        message += message1;
                        break;
                    default:
                        break;
                }
            }
        });

    }

    public void OnClickButtonOK(View view) {
        TextView result = (TextView) findViewById(R.id.textViewRes);
        result.setText(message1+message);
    }

    public void OnClickButtonCancel(View view) {
        TextView result = (TextView)findViewById(R.id.textViewRes);
        result.setText("");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        message="User chosen: \n";
    }
}

