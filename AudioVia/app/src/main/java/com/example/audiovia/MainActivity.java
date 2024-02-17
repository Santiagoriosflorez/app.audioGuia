package com.example.audiovia;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 1;
    private MapView mapView;
    private IMapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de osmdroid
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE));

        // Inicializar el MapView y configurar algunas opciones
        mapView = findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK); // Establecer el origen de las teselas (tiles)
        mapView.setBuiltInZoomControls(true); // Habilitar controles de zoom
        mapView.setMultiTouchControls(true); // Habilitar zoom multitouch

        GeoPoint startPonint = new GeoPoint(4.967699692412894, -74.10815248569432);
        mapView.getController().setCenter(startPonint);
        int zoomLevel=11;
        mapView.getController().setZoom(zoomLevel);
        mapController = mapView.getController();

        // Solicitar permisos de ubicación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION);
        } else {
            // Si ya se tienen los permisos, obtener la ubicación actual
            obtenerUbicacionActual();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Si se otorgan los permisos, obtener la ubicación actual
                obtenerUbicacionActual();
            }
        }
    }

    private void obtenerUbicacionActual() {
        // Aquí debes implementar la lógica para obtener la ubicación actual del dispositivo.
        // Puedes usar la API de ubicación de Android, como FusedLocationProviderClient.
        // Por simplicidad, en este ejemplo, estableceremos una ubicación de ejemplo en Barcelona, España

        // Centrar el mapa en la ubicación actual
        //mapController.setCenter(currentLocation);

        // Añadir un marcador en la ubicación actual
        //Marker marker = new Marker(mapView);
        //marker.setPosition(currentLocation);
        //mapView.getOverlays().add(marker);

        // Refrescar el mapa para que se muestre el marcador
        mapView.invalidate();
    }
}