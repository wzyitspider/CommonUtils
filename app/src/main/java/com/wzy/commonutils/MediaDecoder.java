package com.wzy.commonutils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;

import java.io.File;

public class MediaDecoder {
    private static final String TAG = "MediaDecoder";
    private String fileLength;
    private String path;
    public MediaDecoder(String file) {
            path = file;
    }
    /**
     * 获取视频某一帧
     * @param timeMs 毫秒
     * @param listener
     */
    public boolean decodeFrame (long timeMs,OnGetBitmapListener listener){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        if (TextUtils.isEmpty(path)) {
            return false;
        }

        File file = new File(path);
        if (!file.exists()) {
            return false;
        }

        Bitmap bitmap = null;
        try {
            retriever.setDataSource(path);
            if(timeMs==0)
                timeMs = 1 ;
            bitmap = retriever.getFrameAtTime(timeMs); //取得指定时间的Bitmap，即可以实现抓图（缩略图）功能
        } catch (IllegalArgumentException ex) {
            AXLog.e("wzytest","decodeFrame 非法状态异常");
        } catch (RuntimeException ex) {
            AXLog.e("wzytest","decodeFrame 运行时候异常");
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
                AXLog.e("wzytest","release 运行时候异常");
            }
        }

        if(bitmap == null) return false;
        listener.getBitmap(bitmap, timeMs, Long.valueOf(fileLength));
        return true;
    }
    /**
     * 取得视频文件播放长度
     * @return
     */
    public String getVedioFileLength(){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(path);
        fileLength = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        return fileLength;
    }

    public interface OnGetBitmapListener {
        public void getBitmap(Bitmap bitmap, long timeMs, long duration);
    }
}