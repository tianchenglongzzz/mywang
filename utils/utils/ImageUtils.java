package com.jhjz.emr.lstd_public.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.bean.FuJian;
import com.jhjz.emr.lstd_public.config.UrlConfig;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.ImageMultipleWrapper;
import com.yanzhenjie.album.api.widget.Widget;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by hopc on 2018/4/13.
 */

public class ImageUtils {
    public  static  void previewImages(Context context, List<FuJian> list, int position, String title) {
        ArrayList<String> imageList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            imageList.add(UrlConfig.IMG_URL + list.get(i).getCunFangLuJing());
        }
        Album.gallery(context)
                .requestCode(2)
                .checkedList(imageList)
                .currentPosition(position)
                .navigationAlpha(80)
                .checkable(false)
                .widget(Widget.newDarkBuilder(context).title(title).build())
                .onResult(new Action<ArrayList<String>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull ArrayList<String> result) {
                        // TODO If it is optional, here you can accept the results of user selection.
                    }
                })
                .start();
    }
    public  static void selectImage(final Context context, List<FuJian> list, final String  type, final SelectImageResllt selectImageResllt) {
        ImageMultipleWrapper imageMultipleWrapper = Album.image(context)
                .multipleChoice()
                .requestCode(200)
                .camera(true)
                .columnCount(3);

                imageMultipleWrapper.selectCount(9 - list.size());

//            case "DJZ":
//                imageMultipleWrapper.selectCount(9 - djz_list.size());
//                break;
//            case "NXP":
//                imageMultipleWrapper.selectCount(9 - nxp_list.size());
//                break;
        Widget widget = Widget.newDarkBuilder(context).title("选择图片")
                .statusBarColor(context.getResources().getColor(R.color.black))
                .toolBarColor(context.getResources().getColor(R.color.newMainColor))
                .mediaItemCheckSelector(context.getResources().getColor(R.color.grgray),context. getResources().getColor(R.color.newMainColor))
                .build();
        imageMultipleWrapper.widget(widget)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {

                        compressImg(result,context,type,selectImageResllt);
                    }
                }).onCancel(new Action<String>() {
            @Override
            public void onAction(int requestCode, @NonNull String result) {

            }
        }).start();
    }
    private static  void compressImg(final List<AlbumFile> list, final Context context, final String  type, final SelectImageResllt selectImageResllt) {
        List<String> pathList = new ArrayList<>();
        final List<File> fileList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            pathList.add(list.get(i).getPath());
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("正在上传...");
        Luban.with(context)
                .load(pathList)                                   // 传人要压缩的图片列表
                .ignoreBy(100)                                  // 忽略不压缩图片的大小
                .setTargetDir(PathUtils.getPath())                        // 设置压缩后文件存储位置
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                        dialog.show();

                    }

                    @Override
                    public void onSuccess(File file) {
                        dialog.dismiss();
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        fileList.add(file);
                        if (fileList.size() == list.size()) {
                            selectImageResllt.selected(type,fileList);
//                            UpLoad(fileList, SpUtil.getPatient().getHZID(), TABLE_NAME, Field, type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.show(context,"照片压缩失败", Toast.LENGTH_SHORT);
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();    //启动压缩
    }

    public  interface  SelectImageResllt{
        void selected(String type, List<File> fileList);
    }

}
