package com.pickpamphlet.easydeals.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pickpamphlet.easydeals.R;
import com.pickpamphlet.easydeals.forms.Form1;
import com.pickpamphlet.easydeals.forms.Form1a;
import com.pickpamphlet.easydeals.forms.Form1ad;
import com.pickpamphlet.easydeals.forms.Form1b;
import com.pickpamphlet.easydeals.forms.Form1bd;
import com.pickpamphlet.easydeals.forms.Form1c;
import com.pickpamphlet.easydeals.forms.Form1cd;
import com.pickpamphlet.easydeals.forms.Form1d;
import com.pickpamphlet.easydeals.forms.Form2;
import com.pickpamphlet.easydeals.forms.Form2a;
import com.pickpamphlet.easydeals.forms.Form2ad;
import com.pickpamphlet.easydeals.forms.Form2b;
import com.pickpamphlet.easydeals.forms.Form2bd;
import com.pickpamphlet.easydeals.forms.Form2c;
import com.pickpamphlet.easydeals.forms.Form2cd;
import com.pickpamphlet.easydeals.forms.Form2d;

/**
 * Created by panshul on 23/9/17.
 */

public class AddActivity extends AppCompatActivity {

    RadioGroup rg1, rg2, rg3, rg4;
    Button submit;
    String str1, str2, str3, str4;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();
        init();
    }


   public void init() {

       rg1 = (RadioGroup) findViewById(R.id.radiogroup1);
       rg2 = (RadioGroup) findViewById(R.id.radiogroup2);
       rg3 = (RadioGroup) findViewById(R.id.radiogroup3);
       rg4 = (RadioGroup) findViewById(R.id.radiogroup4);


       submit = (Button) findViewById(R.id.btn_add);

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.residential
                       && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form1.class);
                   Bundle extras = new Bundle();
                   extras.putString("Sale",str1);
                   extras.putString("Residential",str2);
                   extras.putString("Owner", str3);
                   extras.putString("Direct", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
                //   startActivity(new Intent(AddActivity.this, Form1.class));
               } else if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.residential
                               && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                           RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                           RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                           RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                           RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                           str1 = button.getText().toString();
                           str2 = button2.getText().toString();
                           str3 = button3.getText().toString();
                           str4 = button4.getText().toString();
                           Intent intent = new Intent(AddActivity.this,Form1d.class);
                           Bundle extras = new Bundle();
                           extras.putString("Sale",str1);
                           extras.putString("Residential",str2);
                           extras.putString("Owner", str3);
                           extras.putString("Dealer", str4);
                           intent.putExtras(extras);
                           startActivity(intent);
                           finish();
                           //   startActivity(new Intent(AddActivity.this, Form1.class));
                   }
               else if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form1a.class);
                   Bundle extras1 = new Bundle();
                   extras1.putString("Sale",str1);
                   extras1.putString("Commercial",str2);
                   extras1.putString("Owner",str3);
                   extras1.putString("Direct", str4);
                   intent.putExtras(extras1);
                   startActivity(intent);
                   finish();
            //       Toast.makeText(AddActivity.this, " " +str1 +str2 +str3, Toast.LENGTH_LONG).show();
                //   startActivity(new Intent(AddActivity.this, Form1.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this, Form1ad.class);
                   Bundle extras1 = new Bundle();
                   extras1.putString("Sale", str1);
                   extras1.putString("Commercial", str2);
                   extras1.putString("Owner",str3);
                   extras1.putString("Dealer", str4);
                   intent.putExtras(extras1);
                   startActivity(intent);
                   finish();
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.residential
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form2.class);
                   Bundle extras = new Bundle();
                   extras.putString("Sale",str1);
                   extras.putString("Residential",str2);
                   extras.putString("Party", str3);
                   extras.putString("Direct", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
              //     startActivity(new Intent(AddActivity.this, Form2.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.residential
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this, Form2d.class);
                   Bundle extras = new Bundle();
                   extras.putString("Sale", str1);
                   extras.putString("Residential", str2);
                   extras.putString("Party", str3);
                   extras.putString("Dealer", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form2a.class);
                   Bundle extras = new Bundle();
                   extras.putString("Sale",str1);
                   extras.putString("Commercial",str2);
                   extras.putString("Party", str3);
                   extras.putString("Direct", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
              //     startActivity(new Intent(AddActivity.this, Form2.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.sale && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form2ad.class);
                   Bundle extras = new Bundle();
                   extras.putString("Sale",str1);
                   extras.putString("Commercial",str2);
                   extras.putString("Party", str3);
                   extras.putString("Dealer", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
                   //     startActivity(new Intent(AddActivity.this, Form2.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.residential
                       && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form1b.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent",str1);
                   extras.putString("Residential",str2);
                   extras.putString("Owner", str3);
                   extras.putString("Direct", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
            //       Toast.makeText(AddActivity.this, " " +str1 +str2 +str3, Toast.LENGTH_LONG).show();
            //       startActivity(new Intent(AddActivity.this, Form1.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.residential
                       && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this, Form1bd.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent", str1);
                   extras.putString("Residential", str2);
                   extras.putString("Owner", str3);
                   extras.putString("Dealer", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form1c.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent",str1);
                   extras.putString("Commercial",str2);
                   extras.putString("Owner", str3);
                   extras.putString("Direct", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
            //       startActivity(new Intent(AddActivity.this, Form1.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.owner && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this, Form1cd.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent", str1);
                   extras.putString("Commercial", str2);
                   extras.putString("Owner", str3);
                   extras.putString("Dealer", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.residential
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form2b.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent",str1);
                   extras.putString("Residential",str2);
                   extras.putString("Party", str3);
                   extras.putString("Direct", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
            //       startActivity(new Intent(AddActivity.this, Form2.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.residential
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this, Form2bd.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent", str1);
                   extras.putString("Residential", str2);
                   extras.putString("Party", str3);
                   extras.putString("Dealer", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.direct) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this,Form2c.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent",str1);
                   extras.putString("Commercial",str2);
                   extras.putString("Party", str3);
                   extras.putString("Direct", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
             //      startActivity(new Intent(AddActivity.this, Form2.class));
               }
               else if (rg1.getCheckedRadioButtonId() == R.id.rent && rg2.getCheckedRadioButtonId() == R.id.commercial
                       && rg3.getCheckedRadioButtonId() == R.id.party && rg4.getCheckedRadioButtonId() == R.id.dealer) {
                   RadioButton button = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
                   RadioButton button2 = (RadioButton) rg2.findViewById(rg2.getCheckedRadioButtonId());
                   RadioButton button3 = (RadioButton) rg3.findViewById(rg3.getCheckedRadioButtonId());
                   RadioButton button4 = (RadioButton) rg4.findViewById(rg4.getCheckedRadioButtonId());
                   str1 = button.getText().toString();
                   str2 = button2.getText().toString();
                   str3 = button3.getText().toString();
                   str4 = button4.getText().toString();
                   Intent intent = new Intent(AddActivity.this, Form2cd.class);
                   Bundle extras = new Bundle();
                   extras.putString("Rent", str1);
                   extras.putString("Commercial", str2);
                   extras.putString("Party", str3);
                   extras.putString("Dealer", str4);
                   intent.putExtras(extras);
                   startActivity(intent);
                   finish();
               }
           }
       });
   }

}

