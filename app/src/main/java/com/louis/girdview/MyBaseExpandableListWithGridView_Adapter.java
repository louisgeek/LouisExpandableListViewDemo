package com.louis.girdview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.louis.louisexpandablelistviewdemo.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/16.
 */
public class MyBaseExpandableListWithGridView_Adapter extends BaseExpandableListAdapter {

    List<Map<String, Object>> parentMapList;
    List<List<Map<String, Object>>> childMapList_list;
    Context context;
    List<Map<String,Object>> nowChildMapList;
    public MyBaseExpandableListWithGridView_Adapter(  Context context, List<Map<String, Object>> parentMapList,List<List<Map<String, Object>>> childMapList_list) {
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
        //子列表项的数量
       // return childMapList_list.get(groupPosition).size();
        //子列表项的数量本来是list  多个  现在同样的数据以girdview形式展示  就只有个girdview项 所以返回1
        return 1;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.child_layout_girdview, null);

        }
        CanAddInListViewGridView canAddInListViewGridView = (CanAddInListViewGridView) convertView.findViewById(R.id.channel_item_child_gridView);
      List<Map<String,Object>> childMapList= childMapList_list.get(groupPosition);
        nowChildMapList= childMapList_list.get(groupPosition);
        SimpleAdapter simpleAdapter=new SimpleAdapter(context,childMapList, R.layout.gridview_item,new String[]{"childName"},new int[]{R.id.id_tv_gridview_item});
        canAddInListViewGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"点击了"+nowChildMapList.get(position).get("childName"),Toast.LENGTH_SHORT).show();
            }
        });
        canAddInListViewGridView.setAdapter(simpleAdapter);
       /* TextView tv_items_child = (TextView) convertView
                .findViewById(R.id.tv_items_child);
        String childName =childMapList_list.get(groupPosition).get(childPosition).get("childName").toString();
        tv_items_child.setText(childName);*/
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // return false;
        return true;
    }
}
