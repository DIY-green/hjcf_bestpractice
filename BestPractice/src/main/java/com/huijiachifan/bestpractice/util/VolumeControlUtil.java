/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import android.content.Context;
import android.media.AudioManager;

import com.huijiachifan.bestpractice.BestPracticeApp;

/**
 * Description: 音量控制工具类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月4日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class VolumeControlUtil {

    private static final int STREAM_TYPE_VOICE_CALL = AudioManager.STREAM_VOICE_CALL; // 通话音量
    private static final int STREAM_TYPE_SYSTEM = AudioManager.STREAM_SYSTEM; // 系统音量
    private static final int STREAM_TYPE_RING = AudioManager.STREAM_RING; // 铃声音量
    private static final int STREAM_TYPE_MUSIC = AudioManager.STREAM_MUSIC; // 音乐音量
    private static final int STREAM_TYPE_ALARM = AudioManager.STREAM_ALARM; // 提示声音音量

    private static AudioManager sAudioManager = (AudioManager) BestPracticeApp.getContext().getSystemService(Context.AUDIO_SERVICE);

    private VolumeControlUtil() {}

    /**
     * 获取音量最大值
     * @param streamType 类型
     * @return
     */
    public static int getMaxVolume(int streamType) {
        int max = sAudioManager.getStreamMaxVolume(streamType);
        return max;
    }

    /**
     * 获取音量当前值
     * @param streamType 类型
     * @return
     */
    public static int getCurrentVolume(int streamType) {
        int current = sAudioManager.getStreamVolume(streamType);
        return current;
    }

    /**
     * 设置静音
     * @param streamType 类型
     * @return
     */
    public static void setSilence(int streamType) {
        sAudioManager.setStreamVolume(streamType, 0, 0);
    }

    /**
     * 设置音量（绝对值）
     * @param streamType 类型
     * @return
     */
    public static void setVolume(int volume,int streamType) {
        sAudioManager.setStreamVolume(streamType, volume, 0);
    }

}
