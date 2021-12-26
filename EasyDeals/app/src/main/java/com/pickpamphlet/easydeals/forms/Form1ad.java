package com.pickpamphlet.easydeals.forms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.pickpamphlet.easydeals.R;
import com.pickpamphlet.easydeals.database.DatabaseAccess;
import com.pickpamphlet.easydeals.utilis.Model_Form1;
import com.pickpamphlet.easydeals.utilis.PlaceArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by panshul on 29/9/17.
 */

public class Form1ad extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    private Model_Form1 model_form1;
    private EditText name, address, demand, area, desc, dealer;
    private Button add;
    String str1, str2, str3, str4, date;
    Bundle extras;
    AutoCompleteTextView locality;
    private static final String TAG = "Form1ad";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS= new LatLngBounds(
            new LatLng(23.63936, 68.14712), new LatLng(28.20453, 97.34466));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form1d);
        mGoogleApiClient = new GoogleApiClient.Builder(Form1ad.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();

        try {
            extras = getIntent().getExtras();
            str1 = extras.getString("Sale");
            str2 = extras.getString("Commercial");
            str3 = extras.getString("Owner");
            str4 = extras.getString("Dealer");
        } catch ( Exception e) {
            System.out.print(e);
        }

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        date = df.format(c.getTime());
        System.out.println("Date " + date);

        dealer = (EditText) findViewById(R.id.ed_dealer_name);
        name = (EditText) findViewById(R.id.ed_name);
        address = (EditText) findViewById(R.id.ed_address);
        locality = (AutoCompleteTextView) findViewById(R.id.ed_location);
        demand = (EditText) findViewById(R.id.ed_demand);
        area = (EditText) findViewById(R.id.ed_area);
        desc = (EditText) findViewById(R.id.ed_desc);
        desc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onSaveClicked();
                    handled = true;
                }
                return handled;
            }
        });

        add = (Button) findViewById(R.id.btn_add);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            model_form1 = (Model_Form1) bundle.get("MODEL_FORM1");
            if (model_form1 != null) {
                dealer.setText(model_form1.getDealer());
                name.setText(model_form1.getName());
                address.setText(model_form1.getAddress());
                locality.setText(model_form1.getLocality());
                demand.setText(model_form1.getDemand());
                area.setText(model_form1.getArea());
                desc.setText(model_form1.getDesc());
            }
        }

        locality.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS, null);
        locality.setAdapter(mPlaceArrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
    }

    public void onSaveClicked() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Model_Form1 temp = new Model_Form1();
        temp.setDate(date);
        temp.setDealer(dealer.getText().toString());
        temp.setName(name.getText().toString());
        temp.setAddress(address.getText().toString());
        temp.setLocality(locality.getText().toString());
        temp.setDemand(demand.getText().toString());
        temp.setArea(area.getText().toString());
        temp.setDesc(desc.getText().toString());

        try {
            temp.setGp1(str1);
            temp.setSd(str4);
            temp.setGp2(str2);
            //   temp.setGp2(str5);
            temp.setGp3(str3);
        } catch ( Exception e) {

        }
        databaseAccess.save(temp);
        databaseAccess.close();
        this.finish();
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(TAG, "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i(TAG, "Fetching details for ID: " + item.placeId);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();

            locality.setText(Html.fromHtml(place.getAddress() + ""));
            locality.dismissDropDown();
        }
    };

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(TAG, "Google Places API connected.");

    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(TAG, "Google Places API connection suspended.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.e(TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();

    }

}
