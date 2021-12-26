package com.pickpamphlet.easydeals.activities;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pickpamphlet.easydeals.R;
import com.pickpamphlet.easydeals.database.DatabaseAccess;
import com.pickpamphlet.easydeals.fragment.OwnerFragment;
import com.pickpamphlet.easydeals.fragment.PartyFragment;
import com.pickpamphlet.easydeals.utilis.Apis;
import com.pickpamphlet.easydeals.utilis.CustomSeekBar;
import com.pickpamphlet.easydeals.utilis.Model_Form1;
import com.pickpamphlet.easydeals.utilis.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
@SuppressLint("SdCardPath")
public class MainActivity extends AppCompatActivity {

    private final static String TAG= MainActivity.class.getName().toString();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    FloatingActionButton fab;
    OwnerFragment ownerFragment = new OwnerFragment();
    PartyFragment partyFragment = new PartyFragment();
    private List<Model_Form1> model_form;
    private List<Model_Form1> model_form1;
    DatabaseAccess databaseAccess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this, AddActivity.class));
            }
        });

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }


   @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main, menu);

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
           SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
           SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
           if (null != searchView) {
               searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
               searchView.setIconifiedByDefault(false);
           }

           SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
               public boolean onQueryTextChange(String newText) {
                   // this is your adapter that will be filtered
                   Log.e("Text", newText);
                   ViewPagerAdapter pagerAdapter = (ViewPagerAdapter) viewPager.getAdapter();
                   for (int i = 0; i < pagerAdapter.getCount(); i++) {

                       Fragment viewPagerFragment = (Fragment) viewPager.getAdapter().instantiateItem(viewPager, i);
                       if (viewPagerFragment != null && viewPagerFragment.isAdded()) {

                           if (viewPagerFragment instanceof OwnerFragment) {
                               OwnerFragment ownerFragment = (OwnerFragment) viewPagerFragment;
                               if (ownerFragment != null) {
                                   ownerFragment.cancelSearch(newText); // Calling the method beginSearch of ChatFragment
                               }
                           } else if (viewPagerFragment instanceof PartyFragment) {
                               PartyFragment partyFragment = (PartyFragment) viewPagerFragment;
                               if (partyFragment != null) {
                                   partyFragment.cancelSearch(newText); // Calling the method beginSearch of GroupsFragment
                               }
                           }
                       }

                       //  ad_names.notifyDataSetChanged();
                       //  lv_listname.setFilterText(newText);
                       //  searchview.dismissSuggestions();
                   }
                   return true;
               }

               public boolean onQueryTextSubmit(String query) {
                   //Here u can get the value "query" which is entered in the search box.

                   Log.e("Query", query);
                   ViewPagerAdapter pagerAdapter = (ViewPagerAdapter) viewPager.getAdapter();
                   for (int i = 0; i < pagerAdapter.getCount(); i++) {

                       Fragment viewPagerFragment = (Fragment) viewPager.getAdapter().instantiateItem(viewPager, i);
                       if (viewPagerFragment != null && viewPagerFragment.isAdded()) {

                           if (viewPagerFragment instanceof OwnerFragment) {
                               OwnerFragment ownerFragment = (OwnerFragment) viewPagerFragment;
                               if (ownerFragment != null) {
                                   ownerFragment.beginSearch(query); // Calling the method beginSearch of ChatFragment
                               }
                           } else if (viewPagerFragment instanceof PartyFragment) {
                               PartyFragment partyFragment = (PartyFragment) viewPagerFragment;
                               if (partyFragment != null) {
                                   partyFragment.beginSearch(query); // Calling the method beginSearch of GroupsFragment
                               }
                           }
                       }
                   }

                   return true;

               }
           };
           searchView.setOnQueryTextListener(queryTextListener);
       }
       return super.onCreateOptionsMenu(menu);
   }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

/*
            case R.id.action_settings:
                Toast.makeText(this, "Home Settings Click", Toast.LENGTH_SHORT).show();
                return true;
  */

            case R.id.action_aboutus:
                startActivity(new Intent(MainActivity.this, AboutUs.class));
                Snackbar.make(findViewById(android.R.id.content), "About Us Clicked", Snackbar.LENGTH_LONG)
                        .setAction("Undo", null)
                        .setActionTextColor(Color.RED)
                        .show();
                return true;
            case R.id.action_terms:
                startActivity( new Intent( MainActivity.this, TermsnConditions.class));
                Snackbar.make(findViewById(android.R.id.content), "Terms & Conditions Clicked", Snackbar.LENGTH_LONG)
                        .setAction("Undo", null)
                        .setActionTextColor(Color.RED)
                        .show();
                return true;
            case R.id.action_changepassword:
                startActivity(new Intent(MainActivity.this, Change_Password.class));
                Snackbar.make(findViewById(android.R.id.content), "Change Password Click", Snackbar.LENGTH_LONG)
                        .setAction("Undo", null)
                        .setActionTextColor(Color.RED)
                        .show();
                return true;
            case R.id.action_logout:
                alertDialog(MainActivity.this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    //    ownerFragment =new OwnerFragment();
    //    partyFragment =new PartyFragment();
        adapter.addFragment(ownerFragment,"OWNER");
        adapter.addFragment(partyFragment,"PARTY");
        viewPager.setAdapter(adapter);
    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object){
            return super.getItemPosition(object);

        }
    }


    public static void alertDialog(final Context context) {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AppTheme_Dark_Dialog));
        alertdialog.setMessage("Are You Sure want to Logout?");
        alertdialog.setCancelable(true);
        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userLogout(context);
            }
        });
        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertdialog.show();
    }


    public static void userLogout(final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.Logout,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("log", "" + response);
                        try {
                            doCheck(response, context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("mobile", SaveSharedPreference.getMobile(context));
                Log.e("mapvalue", "" + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private static void doCheck(String response, Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        if (!jsonObject.getString("status").equals("1")) {
            SaveSharedPreference.setUserID(context, "");
            SaveSharedPreference.setCompanyName(context, "");
            SaveSharedPreference.setUserEMAIL(context, "");
            SaveSharedPreference.setMobile(context, "");
            context.startActivity(new Intent(context, LoginActivity.class));
        } else {
        }
    }



}