package com.louis.girdview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.louis.louisexpandablelistviewdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NextActivity extends AppCompatActivity {
    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        initData();

        ExpandableListView expandableListView= (ExpandableListView) findViewById(R.id.id_elv);
        MyBaseExpandableListWithGridView_Adapter myBaseExpandableListWithGridView_adapter
                =new MyBaseExpandableListWithGridView_Adapter(this,parentMapList,childMapList_list);
        expandableListView.setAdapter(myBaseExpandableListWithGridView_adapter);

    }
    private void initData() {


        for (int i = 0; i < 15; i++) {
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();
            parentMap.put("parentName", "parentName"+i);
            if (i%2==0) {
                parentMap.put("parentIcon", R.mipmap.ic_launcher);
            }else
            {
                parentMap.put("parentIcon", R.mipmap.louisgeek);
            }
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            for (int j = 0; j < 10; j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();
                childMap.put("childName", "parentName"+i+"下面的childName"+j);
                childMapList.add(childMap);
            }
            childMapList_list.add(childMapList);
        }

    }

}
