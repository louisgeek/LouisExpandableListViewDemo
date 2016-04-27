package com.louis.louisexpandablelistviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.louis.girdview.NextActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();

       Button btn_2_next_function= (Button) findViewById(R.id.btn_2_next_function);
        btn_2_next_function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NextActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });


        ExpandableListView expandableListView= (ExpandableListView) findViewById(R.id.id_elv);

        MyBaseExpandableListAdapter myBaseExpandableListAdapter=new MyBaseExpandableListAdapter(this,parentMapList,childMapList_list);
        expandableListView.setAdapter(myBaseExpandableListAdapter);


    /*    *//**
         * 第一个参数  应用程序接口 this
         * 第二个父列List<?extends Map<String,Object>>集合 为父列提供数据
         * 第三个参数  父列显示的组件资源文件
         * 第四个参数  键值列表 父列Map字典的key
         * 第五个要显示的父列组件id
         * 第六个 子列的显示资源文件
         * 第七个参数 键值列表的子列Map字典的key
         * 第八个要显示子列的组件id
         *
         * 第五个参数groupTo - The group views that should display column in the "groupFrom" parameter. These should all be TextViews. The first N views in this list are given the values of the first N columns in the groupFrom parameter.
         *//*

        SimpleExpandableListAdapter simpleExpandableListAdapter = new SimpleExpandableListAdapter(
                this, parentMapList, R.layout.parent_layout, new String[] { "parentName"},
                new int[] { R.id.tv_title_parent}, childMapList_list, R.layout.child_layout,
                new String[] { "childName"}, new int[] { R.id.tv_items_child});
        expandableListView.setAdapter(simpleExpandableListAdapter);*/

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
