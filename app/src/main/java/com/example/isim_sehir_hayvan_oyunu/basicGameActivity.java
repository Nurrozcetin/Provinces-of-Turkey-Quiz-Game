package com.example.isim_sehir_hayvan_oyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class basicGameActivity extends AppCompatActivity {
    private TextView txtCityInfo, txtCityLetters, txtSumPoint;
    private EditText editTxtGuess;
    public String[] cities = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya",
            "Ankara","Antalya","Ardahan","Artvin","Aydın","Balıkesir","Bartın","Batman","Bayburt",
            "Bilecik","Bingöl","Bitlis","Bolu","Burdur","Bursa","Çanakkale","Çankırı","Çorum" ,
            "Denizli","Diyarbakır","Düzce","Edirne","Elazığ","Erzincan","Erzurum","Eskişehir",
            "Gaziantep","Giresun","Gümüşhane","Hakkari","Hatay","Iğdır","Isparta","İstanbul",
            "İzmir","Kahramanmaraş","Karabük","Karaman","Kars","Kastamonu","Kayseri","Kilis",
            "Kırıkkale","Kırklareli","Kırşehir","Kocaeli","Konya","Kütahya","Malatya","Manisa",
            "Mardin","Mersin","Muğla","Muş","Nevşehir","Niğde","Ordu","Osmaniye","Rize","Sakarya",
            "Samsun","Şanlıurfa","Siirt","Sinop","Şırnak","Sivas","Tekirdağ","Tokat","Trabzon",
            "Tunceli","Uşak","Van","Yalova","Yozgat","Zonguldak" };

    private Random randCity, randLetter;
    private int randCityNumber, randLetterNumber, beginLetterNumber;
    private String city, citySize, comingGuess;
    private ArrayList<Character> cityLetters;
    private float maxPoint = 100.0f, reducePoint, sumPoint = 0, remainingPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_game);

        txtCityInfo = (TextView) findViewById(R.id.txtBInfo);
        txtCityLetters = (TextView) findViewById(R.id.txtBSpace);
        txtSumPoint = (TextView)findViewById(R.id.txtBSumPoint);
        editTxtGuess = (EditText)findViewById(R.id.editTxtBGuess);

        randLetter = new Random();
        setRandomValues();
    }
    public void btnGetLetter(View v){
        if(cityLetters.size() > 0){
            getRandomLetter();
            sumPoint = sumPoint - reducePoint;
            Toast.makeText(getApplicationContext(), "Sum Point: "+sumPoint, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "No letters left", Toast.LENGTH_LONG).show();
        }
    }
    public void btnGuess(View v) {
        comingGuess = editTxtGuess.getText().toString();
        if(!TextUtils.isEmpty(comingGuess)) {
            if(comingGuess.equals(city)){
                remainingPoint +=sumPoint;
                Toast.makeText(getApplicationContext(), "Congrats! Your guess is true", Toast.LENGTH_LONG).show();
                txtSumPoint.setText("Your Score: "+remainingPoint);

                editTxtGuess.setText("");
                setRandomValues();
            }
            else{
                Toast.makeText(getApplicationContext(), "Your guess is false", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Your guess was not be empty", Toast.LENGTH_LONG).show();
        }
    }

    private void setRandomValues(){
        citySize = "";
        randCity = new Random();
        randCityNumber = randCity.nextInt(cities.length);
        city = cities[randCityNumber];
        System.out.println(city);

        txtCityInfo.setText("The city with "+city.length()+" letters");

        if(city.length()>5 && city.length()<7) {
            beginLetterNumber = 1;
        }
        else if(city.length()>8 && city.length()<10) {
            beginLetterNumber = 2;
        }
        else if(city.length() >=10) {
            beginLetterNumber = 3;
        }
        else{
            beginLetterNumber = 0;
        }

        for(int i = 0; i<city.length(); i++){
            if(i < city.length() - 1) {
                citySize += "_ ";
            }
            else{
                citySize += "_";
            }
        }

        txtCityLetters.setText(citySize);
        cityLetters = new ArrayList<>();

        for(char c: city.toCharArray()){
            cityLetters.add(c);
        }

        for(int c = 0; c<beginLetterNumber; c++){
            getRandomLetter();
        }

        reducePoint = maxPoint / cityLetters.size();
        sumPoint = maxPoint;
    }

    private void getRandomLetter() {
        randLetterNumber = randLetter.nextInt(cityLetters.size());
        String[] txtLetters = txtCityLetters.getText().toString().split(" ");
        char[] cityLetter = city.toCharArray();

        for(int i = 0; i<city.length(); i++){
            if(txtLetters[i].equals("_") && cityLetter[i]==cityLetters.get(randLetterNumber)){
                txtLetters[i] = String.valueOf(cityLetters.get(randLetterNumber));
                citySize = "";

                for(int j = 0; j < city.length(); j++){
                    if(j==i){
                        citySize += txtLetters[j] + " ";
                    }
                    else if(j<city.length()-1){
                        citySize += txtLetters[j] + " ";
                    }
                    else{
                        citySize += txtLetters[j];
                    }
                }
                break;
            }
        }

        txtCityLetters.setText(citySize);
        cityLetters.remove(randLetterNumber);
    }

}