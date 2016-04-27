package com.louis.louisexpandablelistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/15.
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    List<Map<String, Object>> parentMapList;
    List<List<Map<String, Object>>> childMapList_list;
    Context context;
    public MyBaseExpandableListAdapter(  Context context, List<Map<String, Object>> parentMapList,List<List<Map<String, Object>>> childMapList_list) {
        this.parentMapList=parentMapList;
        this.childMapList_list=childMapList_list;
        this.context=context;
    }


    //获取当前父item的数据数量
    @Override
    public int getGroupCount() {
        return parentMapList.size();
    }
    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childMapList_list.get(groupPosition).size();
    }
    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return parentMapList.get(groupPosition);
    }
    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMapList_list.get(groupPosition).get(childPosition);
    }
    //得到父item的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        //return false;
        return true;
    }
    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.parent_layout, null);
        }
        TextView tv_title_parent = (TextView) convertView
                .findViewById(R.id.tv_title_parent);
        String parentName =parentMapList.get(groupPosition).get("parentName").toString();
        tv_title_parent.setText(parentName);

        ImageView iv_img_parent=(ImageView)convertView.findViewById(R.id.iv_img_parent);
        int parentIcon = Integer.parseInt(parentMapList.get(groupPosition).get("parentIcon").toString());
        iv_img_parent.setImageResource(parentIcon);

        ImageView iv_img_parent_right=(ImageView)convertView.findViewById(R.id.iv_img_parent_right);
        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if(isExpanded){
            iv_img_parent_right.setImageResource(R.mipmap.channel_expandablelistview_top_icon);
        }else{
            iv_img_parent_right.setImageResource(R.mipmap.channel_expandablelistview_bottom_icon);
        }
        return convertView;
    }
    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_layout, null);
        }
        TextView tv_items_child = (TextView) convertView
                .findViewById(R.id.tv_items_child);
        String childName =childMapList_list.get(groupPosition).get(childPosition).get("childName").toString();
        tv_items_child.setText(childName);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
       // return false;
        return true;
    }
}
