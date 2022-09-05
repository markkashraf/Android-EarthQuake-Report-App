package com.example.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {



    //a helper method to get the correct circle color based on the magnitude value
    public int getMagnitudeColor(double magnitude) {
        int magnitude1Color = ContextCompat.getColor( getContext(), R.color.magnitude1);
        int magnitude2Color = ContextCompat.getColor( getContext(), R.color.magnitude2);
        int magnitude3Color = ContextCompat.getColor( getContext(), R.color.magnitude3);
        int magnitude4Color = ContextCompat.getColor( getContext(), R.color.magnitude4);
        int magnitude5Color = ContextCompat.getColor( getContext(), R.color.magnitude5);
        int magnitude6Color = ContextCompat.getColor( getContext(), R.color.magnitude6);
        int magnitude7Color = ContextCompat.getColor( getContext(), R.color.magnitude7);
        int magnitude8Color = ContextCompat.getColor( getContext(), R.color.magnitude8);
        int magnitude9Color = ContextCompat.getColor( getContext(), R.color.magnitude9);
        int magnitude10Color= ContextCompat.getColor(getContext(), R.color.magnitude10plus);

        switch ((int) magnitude)
        {
            case 0:
            case 1:
                return magnitude1Color;
            case 2:
                return magnitude2Color;
            case 3:
                return magnitude3Color;
            case 4:
                return magnitude4Color;
            case 5:
                return magnitude5Color;
            case 6:
                return magnitude6Color;
            case 7:
                return magnitude7Color;
            case 8:
                return magnitude8Color;
            case 9:
                return magnitude9Color;
            default:
                return magnitude10Color;

        }
    }

    //Helper methods to correctly format time and date as a string.
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


    // a constructor that doesn't use resource id.
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //get the current earthquake object and create needed stuff
        Earthquake earthquake = getItem(position);
        String locationOffsetString;
        String locationString = "";
        Date dateObject  = new Date(earthquake.getTime());

        //Create views needed and connect them with the corresponding resource ids
        TextView primaryLocation = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView locationOffset = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView magValue = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.time);
        GradientDrawable magnitudeCircle = (GradientDrawable) magValue.getBackground();



        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMag());



        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        // correctly format the location and the offset
        if(earthquake.getPlace().contains("of"))
        {
            String[] words = earthquake.getPlace().split(" ");
            locationOffsetString = words[0] +" "+ words[1] +" "+ words[2];
            for(int i=3; i<words.length; i++)
            {
                locationString += words[i] + " ";
            }

        }
        else
        {
            locationOffsetString = "Near ";
            locationString = earthquake.getPlace();
        }




        //set values of the TextViews
        magValue.setText(Double.toString(earthquake.getMag()));
        locationOffset.setText(locationOffsetString);
        primaryLocation.setText(locationString);
        date.setText(formatDate(dateObject));
        time.setText(formatTime(dateObject));


        return listItemView;

    }
}
