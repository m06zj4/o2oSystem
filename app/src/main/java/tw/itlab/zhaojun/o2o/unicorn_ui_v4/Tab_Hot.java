package tw.itlab.zhaojun.o2o.unicorn_ui_v4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhaojun on 15/12/29.
 */
public class Tab_Hot extends Fragment {
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView textView;

    private int[] store_img_list = {
            R.drawable.crown, R.drawable.book, R.drawable.humb,
            R.drawable.hankyu, R.drawable.mondiland};
    private String[] store_name_list = {
            "金矿咖啡", "诚品书局", "寒舍艾丽酒店",
            "统一阪急百货", "麦迪莱登"};
    private String[] store_content_list = {
            "买任一小蛋糕 可现折5元优惠", "《蛋黄哥立体公仔实用手册》特价599！",
            "花火绽放2016」跨年住房专案介绍", "元旦愿景打气站，招待您喝咖啡","男装百搭舒适商务休年大衣"};
    private String[] store_star_list = {
            "评价 4.8", "评价 4.3", "评价 4.5",
            "评价 3.8", "评价 3.6"};
    private String[] store_distace_list = {
            "<200m", "<234m", "<273m",
            "<300m", "<410m"};
    private ListView show;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_hot, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                store_name_list);

        textView = (AutoCompleteTextView) getView().findViewById(R.id.auto_complete_text);
        textView.setThreshold(1);
        textView.setAdapter(adapter);

        show = (ListView) getView().findViewById(R.id.list);
        show.canScrollVertically(0);
        List<HashMap<String, Object>> list = new ArrayList<>();
        //使用List存入HashMap，用來顯示ListView上面的文字。

        for (int i = 0; i < store_name_list.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("img", store_img_list[i]);
            hashMap.put("shop_name", store_name_list[i]);
            hashMap.put("content", store_content_list[i]);
            hashMap.put("star", store_star_list[i]);
            hashMap.put("distance", store_distace_list[i]);
            //把title , text存入HashMap之中
            list.add(hashMap);
            //把HashMap存入list之中
        }
        SimpleAdapter listAdapter = new SimpleAdapter(
                getActivity(),
                list,
                R.layout.list_view,
                new String[]{"img", "shop_name", "content", "star", "distance"},
                new int[]{R.id.img, R.id.shop_name, R.id.content, R.id.star, R.id.distance});
        // 5個參數 : context , List , layout , key1 & key2 , text1 & text2

        show.setAdapter(listAdapter);
        setListViewHeightBasedOnChildren(show);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void week(View view) {
    }

    public void everyday(View view) {
    }

    public void todaylow(View view) {
    }

    public void todayhot(View view) {
    }

}
