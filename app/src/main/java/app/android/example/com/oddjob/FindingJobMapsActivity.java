package app.android.example.com.oddjob;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.TimeUnit;

public class FindingJobMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Bundle profileBundle;
    private Profile profile;
    private String personName;
    private String[] personSkills;
    private String personToChat;
    private static final String SHERRY = "SHERRY";
    private static final String JOCELYN = "JOCELYN";
    private static final String SUJEETH = "SUJEETH";

    private String invoice;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        profileBundle = getIntent().getExtras();
        profile = (Profile) profileBundle.getSerializable("profile");

        personName = profile.getName();
        personSkills = profile.getSkills();

        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private boolean contains(String[] arr, String compareTo){
        for (String word : arr) {
            if (word.equals(compareTo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if (status != ConnectionResult.SUCCESS) { // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        } else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();

            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);

            // Getting LocationManager object from System Service LOCATION_SERVICE
            final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            final String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            final Location location = locationManager.getLastKnownLocation(provider);

            final LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    // redraw the marker when get location update.
                    drawMarker(location);
                }
            };

            drawDefaultMarker();

            //locationManager.requestLocationUpdates(provider,20000,0, (android.location.LocationListener) locationListener);
        }
    }

    private void drawMarker(Location location) {
        mMap.clear();
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(currentPosition));
    }

    private void drawDefaultMarker() {
        mMap.clear();
        LatLng currentPosition = new LatLng(37.4102940,-122.0364610);
        Marker currentPositionMarker = mMap.addMarker(new MarkerOptions().position(currentPosition));
        currentPositionMarker.setVisible(true);

        if (contains(personSkills, "babysitting")) {
            LatLng babysitterPosition = new LatLng(37.4149, -122.0486);
            MarkerOptions babysitterMarkerOptions = new MarkerOptions();
            babysitterMarkerOptions.position(babysitterPosition).title(SHERRY).snippet("I need a babysitter!");
            Marker babysitterMarker = mMap.addMarker(babysitterMarkerOptions);
            babysitterMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        }

        if (contains(personSkills, "driving")) {
            LatLng mechanicPosition = new LatLng(37.4220, -122.0841);
            MarkerOptions mechanicMarkerOptions = new MarkerOptions();
            mechanicMarkerOptions.position(mechanicPosition).title(SHERRY).snippet("I need a ride!");
            Marker mechanicMarker = mMap.addMarker(mechanicMarkerOptions);
            mechanicMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

            LatLng driverPosition = new LatLng(37.3688, -122.0363);
            MarkerOptions driverMarkerOptions = new MarkerOptions();
            driverMarkerOptions.position(driverPosition).title(JOCELYN).snippet("I need a driver!");
            Marker driverMarker = mMap.addMarker(driverMarkerOptions);
            driverMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        }

        if (contains(personSkills, "tutoring")) {
            LatLng tutorPosition = new LatLng(37.4,-122.0);
            MarkerOptions tutorMarkerOptions = new MarkerOptions();
            tutorMarkerOptions.position(tutorPosition).title(SUJEETH).snippet("I need a tutor!");
            Marker tutorMarker = mMap.addMarker(tutorMarkerOptions);
            tutorMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        }

        if (contains(personSkills, "lifting")) {
            LatLng tutorPosition = new LatLng(37.401,-122.02);
            MarkerOptions tutorMarkerOptions = new MarkerOptions();
            tutorMarkerOptions.position(tutorPosition).title(SUJEETH).snippet("I need help moving!");
            Marker tutorMarker = mMap.addMarker(tutorMarkerOptions);
            tutorMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
//                Intent intent1 = new Intent(FindingJobMapsActivity.this, InstantMessaging.class);
//                String title = marker.getTitle();
//                intent1.putExtra("markertitle", title);
//                startActivity(intent1);
                if (marker.getTitle().equals(JOCELYN)) {
                    personToChat = "1445664847";
                } else if (marker.getTitle().equals(SHERRY)){
                    personToChat = "100008352789236";
                } else {
                    personToChat = "100001898881820";
                }

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://messaging/"+personToChat)));

                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));

                invoice = "Order Summary:\n\tLabor: $15\n\tTotal: $20";

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jocelyn.tran1@gmail.com"});
                intent.putExtra(Intent.EXTRA_TEXT, invoice);

                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 0);
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://app.android.example.com.oddjob/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://app.android.example.com.oddjob/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
