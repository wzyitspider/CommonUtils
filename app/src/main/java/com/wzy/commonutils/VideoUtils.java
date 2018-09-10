package com.wzy.commonutils;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * 获取本地视频的工具类
 */
public class VideoUtils {

    /**
     * 获取视频的缩略图
     * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     * @param videoPath 视频的路径
     * @param width 指定输出视频缩略图的宽度
     * @param height 指定输出视频缩略图的高度度
     * @param kind 参照MediaStore.Images(Video).Thumbnails类中的常量MINI_KIND和MICRO_KIND。
     *            其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        if(bitmap!= null){
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

    /**
     * 获取指定路径中的视频文件
     * @param list 装扫描出来的视频文件实体类
     * @param file 指定的文件
     */
    public static void getVideoFile(final List<VideoOrImgInfo> list, File file) {// 获得视频文件
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);//获取文件后缀名
                    if (name.equalsIgnoreCase(".mp4")  //忽略大小写
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".mpg")) {
                        VideoOrImgInfo vi = new VideoOrImgInfo();
                        vi.displayName = file.getName();//文件名
                        vi.filePath = file.getAbsolutePath();//文件路径
                        vi.type = 1;
                        vi.srcFile = file;
                        AXLog.e("wzytest","vi.displayName:"+vi.displayName+" vi.filePath:"+vi.filePath);
                        list.add(vi);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });
    }

    /**
     * 获取指定路径中的视频文件
     * @param list 装扫描出来的视频文件实体类
     * @param file 指定的文件
     */
    public static void getVideoPaths(final List<String> list, File file) {// 获得视频文件
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);//获取文件后缀名
                    if (name.equalsIgnoreCase(".mp4")  //忽略大小写
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".mpg")) {
                        list.add(file.getAbsolutePath());
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getVideoPaths(list, file);
                }
                return false;
            }
        });
    }

    /**
     * 获取指定路径中的图片文件
     * @param list 装扫描出来的视频文件实体类
     * @param file 指定的文件
     */
    public static void getImageFile(final List<VideoOrImgInfo> list, File file) {
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);//获取文件后缀名
                    if (name.equalsIgnoreCase(".bmp")  //忽略大小写
                            || name.equalsIgnoreCase(".jpg")
                            || name.equalsIgnoreCase(".png")
                            || name.equalsIgnoreCase(".gif")
                            || name.equalsIgnoreCase(".jpeg")) {
                        VideoOrImgInfo vi = new VideoOrImgInfo();
                        vi.displayName = file.getName();//文件名
                        vi.filePath = file.getAbsolutePath();//文件路径
                        vi.type = 0;
                        AXLog.e("wzytest","vi.displayName:"+vi.displayName+" vi.filePath:"+vi.filePath);
                        list.add(vi);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getImageFile(list, file);
                }
                return false;
            }
        });
    }

    /**
     * 获取指定路径中的图片文件
     * @param list 装扫描出来的视频文件实体类
     * @param file 指定的文件
     */
    public static void getImagePaths(final List<String> list, File file) {// 获得视频文件
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);//获取文件后缀名
                    if (name.equalsIgnoreCase(".bmp")  //忽略大小写
                            || name.equalsIgnoreCase(".jpg")
                            || name.equalsIgnoreCase(".png")
                            || name.equalsIgnoreCase(".gif")
                            || name.equalsIgnoreCase(".jpeg")) {
                        list.add(file.getAbsolutePath());
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getImagePaths(list, file);
                }
                return false;
            }
        });
    }
    public static class VideoOrImgInfo {
        public int type ; // 0 代表图片，1代表视频
        public String displayName ;
        public String filePath ;
        public String fileDuration;
        public File srcFile ;
    }
}