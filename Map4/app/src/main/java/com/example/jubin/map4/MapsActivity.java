package com.example.jubin.map4;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationMarker;
    public static final int REQUEST_LOCATION_CODE = 99;

    LatLng southwest = new LatLng(17.444467, 78.344300);
    LatLng northeast = new LatLng(17.447818, 78.351764);
    LatLng init_focus = new LatLng(17.446580, 78.349916);//initial camera focus location when app starts
    LatLng init_focus_ATM = new LatLng(17.446281, 78.350311);//initial camera focus location when app starts for ATMS
    LatLng init_focus_juice = new LatLng(17.447383, 78.348964);//initial camera focus location when app starts for juice
    LatLng init_focus_cant = new LatLng(17.447383, 78.348964);//initial camera focus location when app starts for canteens
    double loc_A[][] = {
            {17.445859, 78.351590},//ouside atm
            {17.445949, 78.351660},
            {17.446115, 78.351476},
            {17.446069, 78.351456},//IIIt-gate
            {17.445923, 78.351303}, {17.445772, 78.351392}, {17.445696, 78.351471}, {17.445598, 78.351296},//IIIT-H PARKING(7)
            {17.445736, 78.351126},
            {17.445660, 78.351048},
            {17.445739, 78.350925},
            {17.445829, 78.350815}, {17.445798, 78.350710}, {17.445698, 78.350514},//EERC(13)
            {17.445982, 78.350641},
            {17.446084, 78.350498}, {17.446018, 78.350367}, {17.445967, 78.350072},//beech wali(17)
            {17.446305, 78.350233},
            {17.446456, 78.350050},
            {17.446596, 78.349876}, {17.446555, 78.349718}, {17.446353, 78.349621},//SBH(22)
            {17.446837, 78.349600},
            {17.446921, 78.349485},
            {17.447029, 78.349375},
            {17.447139, 78.349243},
            {17.447244, 78.349117},
            {17.447336, 78.349002},
            {17.447430, 78.348889}, {17.447374, 78.348838},//NILGIRI(30)
            {17.447523, 78.348787},
            {17.447650, 78.348629},
            {17.447753, 78.348505},//parking bakul(33)
            {17.447853, 78.348422}, {17.447965, 78.348315},//last 2 bakul(35)
            {17.447996, 78.348125}, {17.447942, 78.347921},//last 2 amphi(37)
            {17.447668, 78.348446},
            {17.447592, 78.348382},
            {17.447507, 78.348286},{17.447725, 78.348058},//Basketball court(41)
            {17.447443, 78.348248},
            {17.447413, 78.348200},{17.447500, 78.348114},{17.447571, 78.348047},{17.447658, 78.347907},{17.447761, 78.347819},{17.447845, 78.347738},{17.447960, 78.347669},//(Akka canteen)}(49)
            {17.447341, 78.348133},
            {17.447254, 78.348058},{17.447221, 78.348178},{17.447180, 78.348251},{17.447128, 78.348339},{17.447031, 78.348463},//(sports office)}(55)
            {17.447195, 78.348007},{17.447267, 78.347859},//girls hostel(57)
            {17.447093, 78.347913},
            {17.447016, 78.347843},
            {17.446926, 78.347754},
            {17.446855, 78.347685},{17.447011, 78.347532},//NBH mess(62)
            {17.446796, 78.347642},
            {17.446719, 78.347572},
            {17.446589, 78.347459},{17.446745, 78.347253},//NBH boys hostel(66)->
            {17.446553, 78.347406},
            {17.446458, 78.347320},
            {17.446392, 78.347234},// (parking NBH)(69)
            {17.446436, 78.347179},{17.446448, 78.347121},{17.446408, 78.346975},{17.446367, 78.346811},{17.446335, 78.346699},{17.446279, 78.346611},{17.446225, 78.346560},// (felicity ground)(76)
            {17.446384, 78.347258},{17.446344, 78.347210},{17.446244, 78.347109},{17.446120, 78.346993},{17.445992, 78.346871},{17.445863, 78.346754},{17.445714, 78.346615},{17.445583, 78.346487},{17.445474, 78.346389},{17.445411, 78.346316},{17.445365, 78.346342},{17.445305, 78.346361},{17.445216, 78.346331},{17.445172, 78.346271},{17.445169, 78.346181},{17.445199, 78.346115},{17.445105, 78.346029}, //(OBH)(93)
            {17.446374, 78.347358},
            {17.446294, 78.347437},{17.446165, 78.347292},{17.446053, 78.347191},{17.445950, 78.347124},{17.445914, 78.346952},//(bakery)(99)
            {17.445770, 78.347190},// (coffee shop and juice centre and frankie and paid canteen)(100)
            {17.445853, 78.347384},{17.445777, 78.347504},{17.445676, 78.347447},//guest house(103)
            {17.446273, 78.347480},
            {17.446215, 78.347568},
            {17.446119, 78.347683},
            {17.446054, 78.347776},
            {17.445964, 78.347867},
            {17.445857, 78.347977},
            {17.445734, 78.348172},
            {17.445625, 78.348295},
            {17.445564, 78.348337},
            {17.445516, 78.348422},
            {17.445465, 78.348505},
            {17.445373, 78.348613},{17.445634, 78.348857},// (T-Hub)(116)
            {17.445250, 78.348758},
            {17.445143, 78.348900},
            {17.445053, 78.349010},
            {17.444948, 78.349157},
            {17.444836, 78.349278},
            {17.444759, 78.349388},
            {17.444662, 78.349522},
            {17.444577, 78.349616},
            {17.444485, 78.349729},
            {17.444365, 78.349884},{17.444227, 78.349774},{17.444083, 78.349656},{17.443892, 78.349493},{17.443748, 78.349329},{17.443606, 78.349188},{17.443451, 78.349079},//faculty quarters(132)
            {17.444421, 78.349951},
            {17.444534, 78.350053},
            {17.444675, 78.350177},
            {17.444769, 78.350278},
            {17.444829, 78.350309},//(front_parking_vindhya)(137)
            {17.444839, 78.350327},
            {17.444909, 78.350389},{17.444864, 78.350465},{17.444794, 78.350582},{17.444740, 78.350687},{17.444698, 78.350823},{17.444698, 78.350823},//IIT TOWER1(144)
            {17.445090, 78.350543},{17.445148, 78.350465},{17.445236, 78.350470},//IIIT Tree(147)
            {17.445203, 78.350657},
            {17.445270, 78.350712},{17.445186, 78.350955},{17.445091, 78.351245},{17.445013, 78.351455},//Electrical deopartment(152)
            {17.445534, 78.350934},
            {17.445613, 78.351015},//(154)connect to (9) after this

            {17.446880, 78.349467},//(155)connect to(24) before this
            {17.446831, 78.349427},
            {17.446778, 78.349377},
            {17.446713, 78.349316},
            {17.446644, 78.349248},
            {17.446593, 78.349211},{17.446674, 78.349114},{17.446795, 78.349013},// NILGIRI Parking)(162)
            {17.446540, 78.349158},
            {17.446503, 78.349122},
            {17.446484, 78.349106},
            {17.446445, 78.349175},{17.446411, 78.349240},{17.446476, 78.349300},//(IIIT-Tower2)(168)
            {17.446319, 78.349255},//(Vindhya canteen and juice centre)(169)
            {17.446431, 78.349057},
            {17.446322, 78.348960},
            {17.446199, 78.348860},
            {17.446124, 78.348797},
            {17.446089, 78.348766},//connected (174)
            {17.446031, 78.348714},
            {17.445986, 78.348669},{17.445928, 78.348757},{17.445861, 78.348835},// (Hello curry)(178)
            {17.445915, 78.348607},
            {17.445784, 78.348479},
            {17.445713, 78.348411},
            {17.445672, 78.348371},{17.445605, 78.348492},// (air conditioning unit)(183)
            {17.445625, 78.348329},//(184) connect to (111)

            {17.444879, 78.350237},//(185) connect to (137)
            {17.444947, 78.350160},
            {17.445032, 78.350054},{17.444975, 78.349943},//(Talent sprint(188)
            {17.445121, 78.349950},{17.445202, 78.350044},//(Directors office,towards EERC,below spatial lab)(190)
            {17.445229, 78.349826},
            {17.445277, 78.349754},{17.445225, 78.349671},//(KOHLI RESEARCH BLOCK)(193)
            {17.445479, 78.349513},{17.445541, 78.349603},// (B4 entrance,CSTAR entrance)(195)
            {17.445556, 78.349407},{17.445442, 78.349269},// (Entry and exit himalaya building)(197)
            {17.445703, 78.349246},{17.445633, 78.349148},// (Entrance to HIMALAYA 105)(199)
            {17.445746, 78.349156},{17.445876, 78.349212}, //(Library)(201)
            {17.445984, 78.348886},
            {17.446050, 78.348803},//(203)connect to (174)

            {17.446119, 78.348716},//(204) connect to (174)
            {17.446250, 78.348539},
            {17.446404, 78.348334},
            {17.446604, 78.348066},{17.446266, 78.347741},// (School and aarogaya centre)(208)
            {17.446671, 78.347974},
            {17.446774, 78.347823},
            {17.446845, 78.347742},//(211)connect to(61)
    };

    double locations_1[][] = {
            {17.445859, 78.351590},//outside ATM
            {17.446069, 78.351456},//IIIT gate
            {17.445598, 78.351296},//IIIT-H Parking
            {17.445698, 78.350514},//EERC
            {17.445967, 78.350072},// (beech wali)
            {17.446353, 78.349621},//SBH
            {17.447374, 78.348838},//NILGIRI
            {17.447753, 78.348505},//parking bakul
            {17.447965, 78.348315},//bakul
            {17.447942, 78.347921},//Amphi Thteatre
            {17.447725, 78.348058},//BasketBall court
            {17.447960, 78.347669},//Akka canteen
            {17.447031, 78.348463},//Sports room
            {17.447274, 78.347862},//girls hostel
            {17.447011, 78.347532},//NBH MESS
            {17.446745, 78.347253},//NBH Boys hostel
            {17.446392, 78.347234},//Parking NBH
            {17.446225, 78.346560},//felicity ground
            {17.445105, 78.346029},// OBH
            {17.445914, 78.346952},//bakery
            {17.445770, 78.347190 },//coffee shop and juice centre and frankie and paid canteen)
            {17.445676, 78.347447},// (guest house)
            {17.445634, 78.348857},// T-Hub
            {17.443451, 78.349079},//faculty quarters
            {17.444829, 78.350309},//front_parking_vindhya
            {17.444698, 78.350823},//(IIT TOWER1)
            {17.445236, 78.350470},// (IIIT Tree)
            {17.445013, 78.351455},//(Electrical department)
            {17.446795, 78.349013},// (NILGIRI Parking)
            {17.446476, 78.349300},//(IIIT-Tower2)
            {17.446319, 78.349255},//(Vindhya canteen and juice centre)
            {17.445861, 78.348835},// (Hello curry)
            {17.445605, 78.348492},// (air conditioning unit)
            {17.444975, 78.349943},//(Talent sprint)
            {17.445202, 78.350044},//(Directors office,towards EERC,below spatial lab)
            {17.445225, 78.349671},//(KOHLI RESEARCH BLOCK)
            {17.445541, 78.349603},// (B4 entrance,CSTAR entrance)
            {17.445442, 78.349269},// (Entry and exit himalaya building)
            {17.445633, 78.349148},// (Entrance to HIMALAYA 105)
            {17.445876, 78.349212},// (Library)
            {17.446266, 78.347741} // (School and aarogaya centre)
    };



    String locations_1_name[] = {
            "outside ATM",
            "IIIT Gate",
            "main parking",
            "EERC",
            "Vindhya Building",
            "SBH",
            "Nilgiri",
            "parking bakul",
            "Bakul Nivas",
            "amphi theatre",
            "basketball court",
            "akka canteen",
            "Sports room",
            "Parijaat(Girls Hostel)",
            "NBH mess",
            "NBH(New Boys Hostel)",
            "parking NBH",
            "felicity ground",
            "OBH(OLD Boys Hostel)",
            "Bakery",
            "coffee shop and juice centre and frankie and paid canteen",
            "Guest House(Atithi Nivas)",
            "T-HUB",
            "Faculty quarters(Buddha Nivas)",
            "front parking vindhya",
            "IIIT Tower 1",
            "IIIT Tree",
            "Electrical department",
            "Nilgiri parking",
            "IIIT Tower 2",
            "Vindhya canteen and juice centre",
            "Hello Curry",
            "Air conditioning unit",
            "Talent Sprint",
            "Directors office",
            "KCIS(Kohli Centre on Intelligent system)",
            "CSTAR Entrance",
            "Himalaya Building",
            "Entrance to HIMALAYA 105",
            "Library",
            "School and aarogaya centre"
    };

    //marker definition

    //
    String locations_1_name_index[] = {
            "0",
            "3",
            "7",
            "13",
            "17",
            "22",
            "30",
            "33",
            "35",
            "37",
            "41",
            "49",
            "55",
            "57",
            "62",
            "66",
            "69",
            "76",
            "93",
            "99",
            "100",
            "103",
            "116",
            "132",
            "137",
            "143",
            "147",
            "152",
            "162",
            "168",
            "169",
            "178",
            "183",
            "188",
            "190",
            "193",
            "195",
            "197",
            "199",
            "201",
            "208"
    };
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //spinner---
        Spinner spinner_or = (Spinner) findViewById(R.id.spinner7);
        Spinner spinner_des = (Spinner) findViewById(R.id.spinner8);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_or = ArrayAdapter.createFromResource(this, R.array.origin_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_des = ArrayAdapter.createFromResource(this, R.array.destination_array, android.R.layout.simple_spinner_item);
        adapter_or.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_des.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_or.setAdapter(adapter_or);
        spinner_des.setAdapter(adapter_des);
        spinner_or.setOnItemSelectedListener(this);
        spinner_des.setOnItemSelectedListener(this);

        //spinner----
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            Toast.makeText(this, "GPS is Enabled in your device", Toast.LENGTH_LONG).show();
        }
        else
        {
            showGPSDisabledAlertToUser();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //Permission is granted
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            buildGoogleApiClient();
                            mMap.setMyLocationEnabled(true);
                        }

                    }
                    else
                    {
                        //permission is denied
                        Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_LONG).show();
                    }
                    return;
                }
        }
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

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        try
        {
            // Customise the styling of the base map u0sing a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success)
            {
                Log.e("VIVZ", "Style parsing failed.");
            }
        }
        catch (Resources.NotFoundException e)
        {
            Log.e("VIVZ", "Can't find style. Error: ", e);
        }


        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setTrafficEnabled(true);

        //1->orange on top of bluedot and both move simultaneously
        //mMap.setMyLocationEnabled(true);
        //mMap.getUiSettings().setMyLocationButtonEnabled(false);
        //

        //2->only orange dot and it sets automatically
        mMap.setMyLocationEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        //
        float iiit_minzoom = 16.00f;//min zoom of cameara
        float iiit_maxzoom = 19.00f;//max zoom of camera


        /*
        List<PatternItem> dottedPattern = Arrays.asList(new Dot(), new Gap(10));
        mMap.addPolyline(new PolylineOptions().addAll(coordList2)
                .width(10)
                .pattern(dottedPattern)
                .color(Color.MAGENTA)
        );
        */
        ////////////////////////////all the locations

        //marker definition
        for(int i=0;i<=locations_1.length-1;i++)
        {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(locations_1[i][0], locations_1[i][1]))
                    .title(locations_1_name[i])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            );
            /* mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(locations_1[i][0], locations_1[i][1]))
                    .title(locations_1_name[i])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            ).setVisible(false);*/
        }

        //////////////////////

        /*
        ArrayList<LatLng> coordList_K = new ArrayList<LatLng>();//latLng object list
        for (int i = 0; i <= loc_A.length - 1; i++)
        {
            coordList_K.add(new LatLng(loc_A[i][0], loc_A[i][1]));
        }
        mMap.addPolyline(new PolylineOptions().addAll(coordList_K)
                .width(10)
                .pattern(dottedPattern)
                .color(Color.GRAY)
        );
        */
        /////////////////////////////////////////////////////
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.setMinZoomPreference(iiit_minzoom);
        mMap.setMaxZoomPreference(iiit_maxzoom);
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.setLatLngBoundsForCameraTarget(bounds);
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(init_focus).
                tilt(0).
                zoom(17).
                bearing(220).
                build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //////////////////////////////////////////
        ////adding polygon for basketball court
        mMap.addPolygon(new PolygonOptions()
                .add(
                        new LatLng(17.447523, 78.348225),
                        new LatLng(17.447757, 78.347897),
                        new LatLng(17.447885, 78.348048),
                        new LatLng(17.447674, 78.348357),
                        new LatLng(17.447523, 78.348225)
                    )
                .fillColor(Color.GRAY));
        ////////////////////

    }

    public void onClick(View v)
    {
        ////spinner value extraction and setting it to origin and destination to feed to routing logic
        Spinner mySpinner7=(Spinner) findViewById(R.id.spinner7);
        String text1 = mySpinner7.getSelectedItem().toString();
        Spinner mySpinner8=(Spinner) findViewById(R.id.spinner8);
        String text2 = mySpinner8.getSelectedItem().toString();
        Log.e("VIVZ",text1);
        Log.e("VIVZ",text2);
        Log.e("VIVZ", String.valueOf(loc_A.length));//212->0-211
        Log.e("VIVZ", String.valueOf(locations_1.length));//41->0-41
        Log.e("VIVZ",String.valueOf(locations_1_name_index.length));//41->0-41


        //to handle text1 == text2 exception
        if(text1.equals(text2))
        {
            Toast.makeText(this,
                    "Origin and Destination are same\n"
                    +"Choose different Origin and Destination"
                    , Toast.LENGTH_LONG)
                    .show();
            return;
        }

        //setting text obtained from spinners to START and END value
        Path_Between_Nodes.START="";
        Path_Between_Nodes.END="";
        for(int i=0;i<=locations_1_name.length-1;i++)
        {
            if(text1.equals("MY LOCATION"))
            {
                //make a method that return a string  value close to my location
                Path_Between_Nodes.START = String.valueOf(i);
            }
            else if(text1.equals(locations_1_name[i]))
            {
                //Path_Between_Nodes.START = String.valueOf(i);
                Path_Between_Nodes.START = locations_1_name_index[i];
            }

            if(text2.equals(locations_1_name[i]))
            {
                //Path_Between_Nodes.END = String.valueOf(i);
                Path_Between_Nodes.END = locations_1_name_index[i];
            }
        }

        Log.e("VIVZ","*******************");
        Log.e("VIVZ",String.valueOf(locations_1_name.length));
        Log.e("VIVZ","*******************");
        Log.e("VIVZ",Path_Between_Nodes.START);
        Log.e("VIVZ",Path_Between_Nodes.END);
        Log.e("VIVZ","*******************");

        /////routing logic
        Path_Between_Nodes graph = new Path_Between_Nodes();
        graph.makeGraph(loc_A.length);
        LinkedList<String> visited = new LinkedList();
        //System.out.println("Enter the source node:");
        Path_Between_Nodes.START = Path_Between_Nodes.START;
        //System.out.println("Enter the destination node:");
        Path_Between_Nodes.END = Path_Between_Nodes.END;
        visited.add(Path_Between_Nodes.START);
        new Path_Between_Nodes().breadthFirst(graph, visited);
        Log.e("VIVZ", String.valueOf(Path_Between_Nodes.link_ll));

        ////

        //info printing
        for(int i = 0; i <= Path_Between_Nodes.link_ll.size() - 1; i++)
        {
            String str_node_list = Path_Between_Nodes.link_ll.get(i);
            Log.e("VIVZ",str_node_list + "->");
            Log.e("VIVZ", String.valueOf(str_node_list.length()));
        }
        //


        /////finding minimum length list index

        int minm=Integer.MAX_VALUE;
        int minmind = 0;
        for(int i = 0; i <= Path_Between_Nodes.link_ll.size() - 1; i++)
        {
            String str_node_list = Path_Between_Nodes.link_ll.get(i);
            String[] str_node = str_node_list.split(",");
            int zz = str_node.length;
            if(zz <= minm )
            {
                minm = zz;
                minmind=i;
            }
        }
        Log.e("VIVZ","-----------------------");
        Log.e("VIVZ",String.valueOf(minm));
        Log.e("VIVZ",String.valueOf(minmind));
        Log.e("VIVZ","-----------------------");

        /////for iiit gate path exception(for OBH,NBH,arrgoya centre)
        if      (

                        (Path_Between_Nodes.START.equals("3") && Path_Between_Nodes.END.equals("93")) ||
                        (Path_Between_Nodes.START.equals("93") && Path_Between_Nodes.END.equals("3")) ||
                                (Path_Between_Nodes.START.equals("3") && Path_Between_Nodes.END.equals("66"))||
                                (Path_Between_Nodes.START.equals("66") && Path_Between_Nodes.END.equals("3"))||
                                (Path_Between_Nodes.START.equals("3") && Path_Between_Nodes.END.equals("208"))||
                                (Path_Between_Nodes.START.equals("208") && Path_Between_Nodes.END.equals("3"))

                )

        {
            minmind = 2;
        }

        ////////
        /*
        Polyline line = map.addPolyline(new PolylineOptions()
                .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
                .width(5)
                .color(Color.RED));

        mMap.addPolyline(new PolylineOptions().addAll(coordList2)
                        .width(10)
                        .pattern(dottedPattern)
                        .color(Color.MAGENTA)
                        */
        ///To draw polyline
        //List<Polyline> polylines = new ArrayList<Polyline>();

        ArrayList<LatLng> coordList_K = new ArrayList<LatLng>();//latLng object list

        String str_node_list = Path_Between_Nodes.link_ll.get(minmind);
        String[] str_node = str_node_list.split(",");
        for(int j=0;j<=str_node.length-1;j++)
        {
            int tz = Integer.parseInt(str_node[j]);
            coordList_K.add(new LatLng(loc_A[tz][0],loc_A[tz][1]));
        }

        mMap.clear();
        List<PatternItem> dottedPattern = Arrays.asList(new Dot(), new Gap(5));
        mMap.addPolyline(new PolylineOptions().addAll(coordList_K)
                .width(10)
                .pattern(dottedPattern)

                .color(Color.GRAY)
        );

        //Adding origin and destination markers
        mMap.addMarker(new MarkerOptions()
                .position(coordList_K.get(0))
                .snippet("ORIGIN")
                .title(text1)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        );

        mMap.addMarker(new MarkerOptions()
                .position(coordList_K.get(coordList_K.size()-1))
                .snippet("Destination")
                .title(text2)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        );
        //
        visited.clear();
        Path_Between_Nodes.link_ll.clear();
        coordList_K.clear();
        //////drawing polyline ends
        ////adding polygon for basketball court
        mMap.addPolygon(new PolygonOptions()
                .add(
                        new LatLng(17.447523, 78.348225),
                        new LatLng(17.447757, 78.347897),
                        new LatLng(17.447885, 78.348048),
                        new LatLng(17.447674, 78.348357),
                        new LatLng(17.447523, 78.348225)
                )
                .fillColor(Color.GRAY));
        ////////////////////

    }




    public void onClick_Directors_office(View v)
    {
        Log.e("VIVZ","director_office");
        mMap.clear();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.setLatLngBoundsForCameraTarget(bounds);
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(init_focus).
                tilt(0).
                zoom(17).
                bearing(220).
                build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445208, 78.349967))
                .title("Director's Office")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        );
    }

    public void onClick_ATMS(View v)
    {
        Log.e("VIVZ","ATMS");
        mMap.clear();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.setLatLngBoundsForCameraTarget(bounds);
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(init_focus_ATM).
                tilt(0).
                zoom(17).
                bearing(220).
                build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445872, 78.351558))
                .title("SBI ATM")
                .snippet("Outside Campus")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.446365, 78.349522))
                .title("SBI ATM")
                .snippet("Inside Campus")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445568, 78.348856))
                .title("HDFC ATM")
                .snippet("T-Hub Ground Floor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );
    }

    public void onClick_juice_centres(View v)
    {
        Log.e("VIVZ","juice_centres");
        mMap.clear();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.setLatLngBoundsForCameraTarget(bounds);
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(init_focus_juice).
                tilt(0).
                zoom(17).
                bearing(220).
                build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.446320, 78.349227))
                .title("Vindhya Juice Center")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445795, 78.347078))
                .title("Guest House Juice Center")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        );
        ////adding polygon for basketball court
        mMap.addPolygon(new PolygonOptions()
                .add(
                        new LatLng(17.447523, 78.348225),
                        new LatLng(17.447757, 78.347897),
                        new LatLng(17.447885, 78.348048),
                        new LatLng(17.447674, 78.348357),
                        new LatLng(17.447523, 78.348225)
                )
                .fillColor(Color.GRAY));
        ////////////////////

    }

    public void onClick_canteen(View v)
    {
        Log.e("VIVZ","Click_canteen");
        mMap.clear();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.setLatLngBoundsForCameraTarget(bounds);
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(init_focus_cant).
                tilt(0).
                zoom(17).
                bearing(220).
                build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445875, 78.348835))
                .title("Hello Curry")
                .snippet("T-Hub Ground Floor")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.446120, 78.349335))
                .title("Vindhya Canteen")
                .snippet("Main Canteen")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445962, 78.346998))
                .title("Bakery")
                .snippet("Cakes and Icecream are available here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445781, 78.347089))
                .title("Guest House Canteen")
                .snippet("Chineze and Paratha are availabele here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.445819, 78.347202))
                .title("Frankie Stand")
                .snippet("veg,non-veg and Egg Rolls are served here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.447904, 78.347690))
                .title("Mini Canteen")
                .snippet("Beside Basketball Court")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        );
        ////adding polygon for basketball court
        mMap.addPolygon(new PolygonOptions()
                .add(
                        new LatLng(17.447523, 78.348225),
                        new LatLng(17.447757, 78.347897),
                        new LatLng(17.447885, 78.348048),
                        new LatLng(17.447674, 78.348357),
                        new LatLng(17.447523, 78.348225)
                )
                .fillColor(Color.GRAY));
        ////////////////////


    }

    /////dialog box to endable GPS
    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(callGPSSettingIntent);
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        ////////////////////
    }

    public boolean checkLocationPermission(){

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }
            return  false;
        }
        else
        {
            return false;
        }
    }



    protected synchronized void buildGoogleApiClient()
    {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        client.connect();
    }
    @Override
    public void onLocationChanged(Location location)
    {
        lastlocation = location;

        if(currentLocationMarker != null)
        {
            currentLocationMarker.remove();
        }

        LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title("Current Location");
        //to get latlng of current place
        markerOptions.snippet( String.valueOf(location.getLatitude())+","+String.valueOf(location.getLatitude()));
        //
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        currentLocationMarker = mMap.addMarker(markerOptions);


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        //mMap.animateCamera(CameraUpdateFactory.zoomBy(16));

        /*
        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
        */
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {

        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
