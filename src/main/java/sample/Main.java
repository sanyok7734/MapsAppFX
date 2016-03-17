package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.*;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements MapComponentInitializedListener {

    GoogleMapView mapView;
    GoogleMap map;

    @Override
    public void start(Stage stage) throws Exception {

        //Create the JavaFX component and set this as a listener so we know when
        //the map has been initialized, at which point we can then begin manipulating it.
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);

        Scene scene = new Scene(mapView);

        stage.setTitle("Google Maps");
        stage.setScene(scene);
        stage.show();
    }


//    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.823323, 35.172598))
              /*  .mapType(MapType.ROADMAP)*/
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(2);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();


        LatLong latLongStart = new LatLong(47.823323, 35.172598);
        markerOptions.position(latLongStart)
                .visible(Boolean.TRUE)
                .title("My Marker");

        MarkerOptions markerOptions1 = new MarkerOptions();
        LatLong latLongEnd = new LatLong(48.518341, 35.878804);
        markerOptions1.position(latLongEnd)
                .visible(Boolean.TRUE)
                .title("My Marker");


        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);
        Marker markerEnd = new Marker(markerOptions1);
        map.addMarker(markerEnd);

        LatLong[] ary = new LatLong[]{latLongStart, latLongEnd};
        MVCArray mvc = new MVCArray(ary);

        PolylineOptions polyOpts = new PolylineOptions()
                .path(mvc)
                .strokeColor("red")
                .strokeWeight(2);

        Polyline poly = new Polyline(polyOpts);
        map.addMapShape(poly);

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Start" );
        InfoWindow window = new InfoWindow(infoWindowOptions);
        window.open(map, marker);

        InfoWindowOptions infoWindowOptionsEnd = new InfoWindowOptions();
        infoWindowOptionsEnd.content("End" );
        InfoWindow windowEnd = new InfoWindow(infoWindowOptionsEnd);
        windowEnd.open(map, markerEnd);
    }

    public static void main(String[] args) {
        launch(args);
    }
}