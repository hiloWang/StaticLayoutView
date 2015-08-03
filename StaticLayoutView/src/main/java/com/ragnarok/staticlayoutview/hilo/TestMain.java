package com.ragnarok.staticlayoutview.hilo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ragnarok.staticlayouttest.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hilo on 15/8/3.
 * <p/>
 * Drscription:
 */
public class TestMain extends ActionBarActivity {

    private ListView mListView;

    private List<String> testViewLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmain);
        mListView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < 100; i++) {
            testViewLists.add(i + "");
        }
        StaticTextViewManager.getInstance().initLayout(this, testViewLists, testViewLists.size());
        mListView.setAdapter(new TestAdapter(testViewLists));
    }

    private class TestAdapter extends BaseAdapter {

        private List<String> adapterLists;

        public TestAdapter(List<String> testViewLists) {
            this.adapterLists = testViewLists;
        }

        @Override
        public int getCount() {
            return adapterLists.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(TestMain.this).inflate(R.layout.test_item, parent, false);
                holder.staticTextView = (StaticTextView) convertView.findViewById(R.id.staticTestView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.staticTextView.setLayout(StaticTextViewManager.getInstance().getLayout(position));
            holder.staticTextView.invalidate();

            return convertView;
        }

        private class ViewHolder {
            StaticTextView staticTextView;
        }
    }
}
