package com.example.l.TripFlash.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.l.TripFlash.DBHelper;
import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.model.dto.User;
import com.example.l.TripFlash.presenter.UserDetailPresenter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends AppCompatActivity implements UserDetailViewInterface {

    GlobalData globalData;
    DBHelper dbHelper;
    private UserDetailPresenter userDetailPresenter;
    @BindView(R.id.user_image_detail)
    CircleImageView image;
    @BindView(R.id.user_id_detail)
    TextView textId;
    private static final int PHOTO_REQUEST_GALLERY = 1;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 2;// 结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this,"TripFlash.db",null,1);
        setContentView(R.layout.user_detail_layout);

        ButterKnife.bind(this);

        globalData = (GlobalData) getApplication();
        userDetailPresenter=new UserDetailPresenter(UserDetailActivity.this);
        textId.setText(globalData.getPrimaryUser().getUserUsername());

        //image init undone
        Glide.with(this)
                .load(getString(R.string.host_url_real_share) + getString(R.string.download_user_image_path) + globalData.getPrimaryUser().getUserId())
                .apply(new RequestOptions().placeholder(R.drawable.outline_account_circle_black_24)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(image);

        image.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,PHOTO_REQUEST_GALLERY);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==PHOTO_REQUEST_GALLERY){
            if(data!=null){
                Uri uri=data.getData();
                crop(uri);
            }
        }
        else if(requestCode==PHOTO_REQUEST_CUT){
            if(data!=null){
                Bitmap bitmap=data.getParcelableExtra("data");//this is the image you get

                userDetailPresenter.upLoadImage(this, bitmap, globalData.getPrimaryUser().getUserId());//upLoad Undone

                //this.image.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void codeScan() {
        /*
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(CodeScanActivity.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
                */
    }

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void showInfo(int type){
        if(type==0){
            Toast.makeText(UserDetailActivity.this, "Image Upload Success", Toast.LENGTH_SHORT).show();
            Glide.with(this)
                    .load(getString(R.string.host_url_real_share) + getString(R.string.download_user_image_path) + globalData.getPrimaryUser().getUserId())
                    .apply(new RequestOptions().placeholder(R.drawable.outline_account_circle_black_24)
                            .diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(image);
        }
        else if(type==1){
            Toast.makeText(UserDetailActivity.this, "Image Upload Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setUser(User user){
        globalData.setPrimaryUser(user);
    }
}
