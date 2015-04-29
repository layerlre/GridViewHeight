package com.layer.android.gridviewheight;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    int rowHeight = 250;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView  = (GridView)findViewById(R.id.girdview);
        CustomAdapter mAdapter = new CustomAdapter();
        gridView.setAdapter(mAdapter);
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (gridView.getColumnWidth()>0) {
                    seteGridviewHeight(gridView, 16, gridView.getNumColumns());
                    gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

            }
        });
    }

    private void seteGridviewHeight(GridView gridView, int itemCount,float numColumn) {
        int row = (int) Math.ceil(((float)itemCount + 1f) / numColumn);
        Log.d("debug", "-------row = " + row);
        int height = (rowHeight * row);  // imageHeight = ความสูงของแต่ละแถวหรือความสูงของรูป
        height += (row - 1) * gridView.getVerticalSpacing();
        gridView.getLayoutParams().height = height;
    }


    public class CustomAdapter extends BaseAdapter {
        private Context mContext;


        public int getCount() {
            return 16;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new GridView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, rowHeight));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(R.mipmap.ic_launcher);
            return imageView;
        }
    }
}
