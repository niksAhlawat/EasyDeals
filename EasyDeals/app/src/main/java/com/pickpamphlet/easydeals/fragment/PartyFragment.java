package com.pickpamphlet.easydeals.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pickpamphlet.easydeals.R;
import com.pickpamphlet.easydeals.activities.ShowList2;
import com.pickpamphlet.easydeals.database.DatabaseAccess;
import com.pickpamphlet.easydeals.utilis.Model_Form1;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.SEARCH_SERVICE;

public class PartyFragment extends Fragment {

    private ListView listView;
    private DatabaseAccess databaseAccess;
    private List<Model_Form1> model_form;
    private DataAdapter dataAdapter;
    int value;

    public PartyFragment() {
    }

    public void beginSearch(String query) {
        Log.e("PartyFragment", query);
        dataAdapter.getFilter().filter(query);
   //     listView.setAdapter(dataAdapter);
    }

    public void cancelSearch(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
            dataAdapter.getFilter().filter("");
        } else {
            dataAdapter.getFilter().filter(newText);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        databaseAccess.open();
        model_form = databaseAccess.getAllModel_Form2();
        databaseAccess.close();
        dataAdapter = new DataAdapter(getContext(), model_form);
        listView.setAdapter(dataAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_view2, container, false);

        databaseAccess = DatabaseAccess.getInstance(getContext());
        listView = (ListView) rootView.findViewById(R.id.listview1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                Model_Form1 model_form1 = model_form.get(position);

                Intent intent = new Intent(getContext(), ShowList2.class);
                intent.putExtra("MODEL_FORM1", model_form1);
                startActivity(intent);
            }
        });
        return rootView;
    }




    public static class DataAdapter extends BaseAdapter implements Filterable {

        List<Model_Form1> model_form;
        private Context context;
        List<Model_Form1> mStringFilterList;
        ValueFilter valueFilter;
        LayoutInflater layoutInflater;

        public DataAdapter(Context context, List<Model_Form1> model_form) {
            this.context = context;
            this.model_form = model_form;
            mStringFilterList = model_form;
            getFilter();
        }

        class SecondViewHolder {
            TextView name, locality, area, desc, budget, gp1, gp2, sd, dealer, date;
            CardView cardView;

            public SecondViewHolder(View convertView) {
                date = (TextView) convertView.findViewById(R.id.txtDate);
                gp1 = (TextView) convertView.findViewById(R.id.gp1);
                gp2 = (TextView) convertView.findViewById(R.id.gp2);
                sd = (TextView) convertView.findViewById(R.id.sd);
                dealer = (TextView) convertView.findViewById(R.id.tv_dealer);
                name = (TextView) convertView.findViewById(R.id.tv_name);
                locality = (TextView) convertView.findViewById(R.id.tv_locality);
                budget = (TextView) convertView.findViewById(R.id.tv_budget);
                area = (TextView) convertView.findViewById(R.id.tv_area);
                desc = (TextView) convertView.findViewById(R.id.tv_desc);
       //         this.cardView = (CardView) convertView.findViewById(R.id.card_view);

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
            SecondViewHolder holder = null;

            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.list_form2, parent, false);
                holder = new SecondViewHolder(convertView);
                convertView.setTag(holder);
            }
            else holder = (SecondViewHolder)convertView.getTag();

            final Model_Form1 form2 = model_form.get(position);
            holder.dealer.setText(form2.getDealer());
            holder.date.setText(form2.getDate());
            holder.name.setText(form2.getName1());
            holder.locality.setText(form2.getLocality1());
            holder.budget.setText(form2.getBudget1());
            holder.area.setText(form2.getArea1());
            holder.desc.setText(form2.getDesc1());
            holder.gp1.setText(form2.getGp11());
            holder.gp2.setText(form2.getGp12());
        //    holder.sd.setText(form2.getSd());
            if (form2.getSd().equals("Dealer")) {
                holder.sd.setText(form2.getSd());
                holder.sd.setTextColor(Color.parseColor("#FF0000"));
            } else {
                holder.sd.setText(form2.getSd());
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

                String str = constraint.toString().toUpperCase();
                Log.e("constraint2", str);
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    ArrayList<Model_Form1> filterList = new ArrayList<Model_Form1>();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).getName1().toUpperCase())
                                .contains(constraint.toString().toUpperCase()) ||
                                (mStringFilterList.get(i).getLocality1().toUpperCase())
                                        .contains(constraint.toString().toUpperCase()) ||
                                (mStringFilterList.get(i).getBudget1().toUpperCase())
                                        .contains(constraint.toString().toUpperCase()) ||
                                (mStringFilterList.get(i).getArea1().toUpperCase())
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
}