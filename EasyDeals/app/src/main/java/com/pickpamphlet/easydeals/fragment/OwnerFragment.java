package com.pickpamphlet.easydeals.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pickpamphlet.easydeals.R;
import com.pickpamphlet.easydeals.activities.ShowList1;
import com.pickpamphlet.easydeals.database.DatabaseAccess;
import com.pickpamphlet.easydeals.utilis.CustomSeekBar;
import com.pickpamphlet.easydeals.utilis.Model_Form1;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.SEARCH_SERVICE;

/**
 * Created by panshul on 21/9/17.
 */

public class OwnerFragment extends Fragment {
    private ListView listView;
    private DatabaseAccess databaseAccess;
    private static List<Model_Form1> model_form;
    private ListDataAdapter mListDataAdapter;
    int value;



    public OwnerFragment() {
    }

    public void beginSearch(String query) {
        Log.e("OwnerFragment", query);
        mListDataAdapter.getFilter().filter(query);
     //   listView.setAdapter(mListDataAdapter);
    }
    public void cancelSearch(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
            mListDataAdapter.getFilter().filter("");
        } else {
            mListDataAdapter.getFilter().filter(newText);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_view, container, false);

        databaseAccess = DatabaseAccess.getInstance(getContext());
        listView = (ListView) rootView.findViewById(R.id.listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                Model_Form1 model_form1 = model_form.get(position);

                Intent intent = new Intent(getContext(), ShowList1.class);
                intent.putExtra("MODEL_FORM1", model_form1);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        databaseAccess.open();
        model_form = databaseAccess.getAllModel_Form1();
        databaseAccess.close();
        mListDataAdapter = new ListDataAdapter(getContext(), model_form);
        listView.setAdapter(mListDataAdapter);
    }


    public static class ListDataAdapter extends BaseAdapter implements Filterable {

        List<Model_Form1> model_form;
        private Context context;
        List<Model_Form1> mStringFilterList;
        ValueFilter valueFilter;
        LayoutInflater layoutInflater;

        private static final int OWNER = 0;
        private static final int PARTY = 1;
        private static final int TYPE_MAX_COUNT = PARTY + 1;

        public ListDataAdapter(Context context, List<Model_Form1> model_form) {
            this.context = context;
            this.model_form = model_form;
            mStringFilterList = model_form;
            getFilter();
        }


        @Override
        public int getViewTypeCount() {
            return TYPE_MAX_COUNT;
        }

        @Override
        public int getItemViewType(int position) {
            return (position == 0) ? OWNER : PARTY;
        }

        static class FirstViewHolder {
            TextView name, date;
            TextView dealer;
            TextView address;
            TextView locality;
            TextView area;
            TextView desc;
            TextView demand;
            TextView gp1;
            TextView gp2;
            TextView sd;
            CardView cardView;

            public FirstViewHolder(View convertView) {
                gp1 = (TextView) convertView.findViewById(R.id.gp1);
                gp2 = (TextView) convertView.findViewById(R.id.gp2);
                sd = (TextView) convertView.findViewById(R.id.sd);
                name = (TextView) convertView.findViewById(R.id.tv_name);
                address = (TextView) convertView.findViewById(R.id.tv_address);
                locality = (TextView) convertView.findViewById(R.id.tv_locality);
                demand = (TextView) convertView.findViewById(R.id.tv_demand);
                area = (TextView) convertView.findViewById(R.id.tv_area);
                desc = (TextView) convertView.findViewById(R.id.tv_desc);
                date = (TextView) convertView.findViewById(R.id.txtDate);
                dealer = (TextView) convertView.findViewById(R.id.tv_dealer);
         //       this.cardView = (CardView) convertView.findViewById(R.id.card_view);

            }
        }

        @Override
        public int getCount() {
            return model_form.size();
        }

        @Override
        public Object getItem(int i) {
            return model_form.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (layoutInflater == null)
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

   FirstViewHolder firstViewHolder = null;
   if (convertView == null) {
       convertView = layoutInflater.inflate(R.layout.list_form1, parent, false);
       firstViewHolder = new FirstViewHolder(convertView);
       convertView.setTag(firstViewHolder);
   }
   else firstViewHolder = (FirstViewHolder)convertView.getTag();


            final Model_Form1 form1 = model_form.get(position);
            firstViewHolder.date.setText(form1.getDate());
            firstViewHolder.name.setText(form1.getName());
            firstViewHolder.dealer.setText(form1.getDealer());
            firstViewHolder.address.setText(form1.getAddress());
            firstViewHolder.locality.setText(form1.getLocality());
            firstViewHolder.demand.setText(form1.getDemand());
            firstViewHolder.area.setText(form1.getArea());
            firstViewHolder.desc.setText(form1.getDesc());
            firstViewHolder.gp1.setText(form1.getGp1());
            firstViewHolder.gp2.setText(form1.getGp2());
       //     firstViewHolder.sd.setText(form1.getSd());
            if (form1.getSd().equals("Dealer")) {
                firstViewHolder.sd.setText(form1.getSd());
                firstViewHolder.sd.setTextColor(Color.parseColor("#FF0000"));
            } else {
                firstViewHolder.sd.setText(form1.getSd());
            }

            return convertView;
        }

        @Override
        public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new ValueFilter();
            }
            return valueFilter;
        }

        private class ValueFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String str = constraint.toString();
                Log.e("constraint1", str);
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    ArrayList<Model_Form1> filterList = new ArrayList<Model_Form1>();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).getName().toUpperCase())
                                .contains(constraint.toString().toUpperCase()) ||
                                (mStringFilterList.get(i).getLocality().toUpperCase())
                                        .contains(constraint.toString().toUpperCase()) ||
                                (mStringFilterList.get(i).getArea().toUpperCase())
                                        .contains(constraint.toString().toUpperCase()) ||
                                (mStringFilterList.get(i).getDemand().toUpperCase())
                                        .contains(constraint.toString().toUpperCase())) {
                            Model_Form1 bean_contacts = mStringFilterList.get(i);
                            filterList.add(bean_contacts);
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = mStringFilterList.size();
                    results.values = mStringFilterList;
                }
                return results;

            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                model_form = (ArrayList<Model_Form1>) results.values;
                notifyDataSetChanged();
            }

        }
    }


    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }


}