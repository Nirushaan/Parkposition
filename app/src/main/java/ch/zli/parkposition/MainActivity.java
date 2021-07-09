package ch.zli.parkposition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Button button_foto;
    Button button_position;
    Button button_reset;
    ImageView imageView;

    LocationManager locationManager;

    ByteArrayOutputStream baos;

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
                getLocation();
            }
        });


        button_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
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


    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    /**
     * Called when the location has changed.
     *
     * @param location the updated location
     */
    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        System.out.println(latitude + " : " + longitude);

    }

    /**
     * This callback will never be invoked on Android Q and above, and providers can be considered
     * as always in the {@link LocationProvider#AVAILABLE} state.
     *
     * @param provider
     * @param status
     * @param extras
     * @deprecated This callback will never be invoked on Android Q and above.
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    /**
     * Called when the provider is enabled by the user.
     *
     * @param provider the name of the location provider that has become enabled
     */
    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    /**
     * Called when the provider is disabled by the user. If requestLocationUpdates
     * is called on an already disabled provider, this method is called
     * immediately.
     *
     * @param provider the name of the location provider that has become disabled
     */
    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
