package ch.zli.parkposition;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button button_foto;
    Button button_position;
    Button button_reset;
    ImageView imageView;

    double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_foto = (Button) findViewById(R.id.button_foto);
        button_position = (Button) findViewById(R.id.button_position);
        button_reset = (Button) findViewById(R.id.button_reset);
        imageView = (ImageView) findViewById(R.id.imageView_foto);


        Fragment fragment = new MapActivity();


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_mapView,fragment)
                .commit();


        button_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarkerOptions markerOptions;
            }
        });


        button_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });


        button_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(null);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //Get Capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //Set Capture Image to ImageView

            imageView.setImageBitmap(captureImage);
        }
    }
}
