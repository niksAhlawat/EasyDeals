package com.pickpamphlet.easydeals.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.pickpamphlet.easydeals.R;
import com.pickpamphlet.easydeals.database.DatabaseAccess;
import com.pickpamphlet.easydeals.forms.Form1;
import com.pickpamphlet.easydeals.forms.Form1d;
import com.pickpamphlet.easydeals.utilis.Model_Form1;

/**
 * Created by panshul on 23/9/17.
 */

public class ShowList1 extends AppCompatActivity {
    private Model_Form1 model_form1;
    private DatabaseAccess databaseAccess;
    private TextView name, address, locality, demand, area, desc, gp1, gp2, dealer, sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list1);
        databaseAccess = DatabaseAccess.getInstance(this);

        dealer = (TextView) findViewById(R.id.tv_dealer_name);
        name = (TextView) findViewById(R.id.tv_name);
        address = (TextView) findViewById(R.id.tv_address);
        locality = (TextView) findViewById(R.id.tv_locality);
        demand = (TextView) findViewById(R.id.tv_demand);
        area = (TextView) findViewById(R.id.tv_area);
        desc = (TextView) findViewById(R.id.tv_desc);
        gp1 = (TextView) findViewById(R.id.tv_gp1);
        gp2 = (TextView) findViewById(R.id.tv_gp2);
        sd = (TextView) findViewById(R.id.tv_sd);

        Intent i = getIntent();
        model_form1 = (Model_Form1) i.getSerializableExtra("MODEL_FORM1");
            name.setText(model_form1.getName());
            dealer.setText(model_form1.getDealer());
            address.setText(model_form1.getAddress());
            locality.setText(model_form1.getLocality());
            demand.setText(model_form1.getDemand());
            area.setText(model_form1.getArea());
            desc.setText(model_form1.getDesc());
            gp1.setText(model_form1.getGp1());
            gp2.setText(model_form1.getGp2());
            sd.setText(model_form1.getSd());
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layoutlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_delete:
                onDeleteClicked(model_form1);
                return true;
            case R.id.action_update:
                onEditClicked(model_form1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onDeleteClicked(final Model_Form1 model_form1) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Do you want to delete this?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      //  dialog.cancel();
                        databaseAccess.open();
                        databaseAccess.delete(model_form1);
                        databaseAccess.close();
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void onEditClicked(Model_Form1 model_form1) {
        Intent intent = new Intent(ShowList1.this, Form1d.class);
        intent.putExtra("MODEL_FORM1", model_form1);
        startActivity(intent);
        finish();
    }

}