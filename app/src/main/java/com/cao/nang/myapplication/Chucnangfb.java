package com.cao.nang.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Chucnangfb extends AppCompatActivity {
    Button shareLink , shareImg , chonvideo , shareVideo;
    VideoView videoMedia ;
    ImageView imgHinhAnh;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;
    public static int select_image = 1;
    public static int pick_video = 2;
    Bitmap bitmap;
    Uri selecvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucnangfb);
        anhxaabc();
        shareDialog = new ShareDialog(Chucnangfb.this);
        shareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (ShareDialog.canShow(ShareLinkContent.class)){
                   shareLinkContent = new ShareLinkContent.Builder().build();
               }
             shareDialog.show(shareLinkContent);
            }
        });
        imgHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent , select_image);
            }
        });
        shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);
            }
        });
        chonvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent , pick_video);
            }
        });
        shareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareVideo shareVideo = null;
                shareVideo = new ShareVideo.Builder()
                        .setLocalUrl(selecvideo)
                        .build();
                ShareVideoContent content = new ShareVideoContent.Builder()
                        .setVideo(shareVideo)
                        .build();
                shareDialog.show(content);
                videoMedia.stopPlayback();
            }
        });
    }

    private void anhxaabc() {
        shareLink = (Button) findViewById(R.id.shareLink);
        shareImg = (Button) findViewById(R.id.shareImg);
        chonvideo = (Button) findViewById(R.id.chonvideo);
        shareVideo = (Button) findViewById(R.id.shareVideo);
        videoMedia = (VideoView) findViewById(R.id.videoMedia);
        imgHinhAnh = (ImageView) findViewById(R.id.imgHinhAnh);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == select_image && resultCode == RESULT_OK){
            try{
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                 bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinhAnh.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        if (requestCode == pick_video && resultCode == RESULT_OK){
            selecvideo = data.getData();
            videoMedia.setVideoURI(selecvideo);
            videoMedia.start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
