/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.adapter;

import android.content.Context;


import com.huijiachifan.bestpractice.R;
import com.huijiachifan.bestpractice.adapter.common.MultiItemCommonAdapter;
import com.huijiachifan.bestpractice.adapter.common.MultiItemTypeSupportable;
import com.huijiachifan.bestpractice.adapter.common.ViewHolder;
import com.huijiachifan.bestpractice.model.bean.ChatMessage;

import java.util.List;

/**
 * Description: ListViewDemo页面
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class MutilItemTypeAdapter extends MultiItemCommonAdapter<ChatMessage> {

    public MutilItemTypeAdapter(Context context, List<ChatMessage> datas) {
        super(context, datas, new MultiItemTypeSupportable<ChatMessage>() {
            @Override
            public int getLayoutId(int position, ChatMessage msg) {
                if (msg.isComMeg()) {
                    return R.layout.main_chat_from_msg;
                }
                return R.layout.main_chat_send_msg;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int postion, ChatMessage msg) {
                if (msg.isComMeg()) {
                    return ChatMessage.RECIEVE_MSG;
                }
                return ChatMessage.SEND_MSG;
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage) {

        switch (holder.getLayoutId()) {
            case R.layout.main_chat_from_msg:
                holder.setText(R.id.chat_from_content, chatMessage.getContent());
                holder.setText(R.id.chat_from_name, chatMessage.getName());
                holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
                break;
            case R.layout.main_chat_send_msg:
                holder.setText(R.id.chat_send_content, chatMessage.getContent());
                holder.setText(R.id.chat_send_name, chatMessage.getName());
                holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());
                break;
        }
    }
}
