package com.vikramj.tempcalc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.vikramj.tempcalc.R.id;


public class MainActivity extends ActionBarActivity {

    RadioButton kelvinRb, celciusRb, fahrenheitRb;
	RadioGroup tempRadioGroup;
    EditText temperatureEdit;
    Button convertButton;
    String celResultStr, kelResultStr, farResultStr, resultString = "State Changed ...";
    double inputTemperature, kelResult,farResult,celResult;

    //final int KELVIN_RB_ID = 0, FAHRENHEIT_RB_ID = 1, CELSIUS_RB_ID = 2;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Linking View Objects
        kelvinRb = (RadioButton)findViewById(R.id.radioKelvin);
        celciusRb = (RadioButton)findViewById(R.id.radioCelcius);
        fahrenheitRb = (RadioButton)findViewById(id.radioFarenheight);

        tempRadioGroup = (RadioGroup)findViewById(id.radioGroupTemp);

        temperatureEdit = (EditText)findViewById(id.tempratureInput);

        convertButton = (Button)findViewById(id.convert_btn);



        // Setting up listeners
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
                Toast.makeText( getApplicationContext(),
                                resultString,
                                Toast.LENGTH_SHORT).show();

            }
        });

        tempRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                /*calculateResult();
                Toast.makeText( getApplicationContext(),
                                resultString,
                                Toast.LENGTH_SHORT).show(); */
            }
        });


        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Selection Logic
    private void calculateResult() {

        RadioButton selectedRb;
        selectedRb = (RadioButton)tempRadioGroup.findViewById(tempRadioGroup.getCheckedRadioButtonId());
        inputTemperature = Double.parseDouble(temperatureEdit.getText().toString());
        switch(selectedRb.getId()) {
            case id.radioCelcius:
                convertFromCelsius();
                break;
            case id.radioFarenheight:
                convertFromFahrenheit();
                break;
            case id.radioKelvin:
                convertFromKelvin();
                break;
        }
        setResultString();              // Sets the result message
    }


    // Calculation Logic
    private void convertFromCelsius() {

        celResult = inputTemperature;
        kelResult = celResult + 273.15 ;
        farResult = (celResult * 1.8) + 32 ;

    }
    private void convertFromFahrenheit() {

        farResult = inputTemperature;
        celResult = (farResult - 32) / 1.8 ;
        kelResult = celResult + 273.15 ;

    }
    private void convertFromKelvin() {

        kelResult = inputTemperature;
        celResult = kelResult - 273.15;
        farResult = (celResult * 1.8) + 32;

    }


    // setting the result message
    private void setResultString() {

        celResultStr = Double.toString(Math.round(celResult));
        kelResultStr = Double.toString(kelResult);
        farResultStr = Double.toString(farResult);

        resultString =  "Celsius\t\t:\t" + celResultStr + "\n" +
                        "Kelvin\t\t:\t" + kelResultStr + "\n" +
                        "Fahrenheit\t\t:\t" + farResultStr ;

    }

}
