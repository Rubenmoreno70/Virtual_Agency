package com.example.proyectomovil.ui.vehicles;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomovil.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VehiclesFragment extends Fragment {

    private String vehicles = "[{\"name\":\"Picanto\",\"line\":\"GT-Line\",\"class\":\"Automovil\",\"model\":\"2021\",\"mileage\":0,\"cylinder\":1250,\"price\":56090000,\"stock\":true,\"features\":[{\"service\":\"Particular\",\"doors\":4,\"capacity\":5,\"armored\":false,\"import\":false,\"fuel\":\"Gasolina\",\"color\":\"Gris\",\"transmission\":\"Mecánica\"}]},{\"name\":\"Rio\",\"line\":\"Sedán\",\"class\":\"Automóvil\",\"model\":\"2021\",\"mileage\":0,\"cylinder\":1400,\"price\":62890000,\"stock\":true,\"features\":[{\"service\":\"Particular\",\"doors\":4,\"capacity\":5,\"armored\":false,\"import\":false,\"fuel\":\"Gasolina\",\"color\":\"Rojo\",\"transmission\":\"Automática\"}]},{\"name\":\"Sonet\",\"line\":\"Emotion\",\"class\":\"Suv\",\"model\":\"2021\",\"mileage\":0,\"cylinder\":1500,\"price\":80490000,\"stock\":true,\"features\":[{\"service\":\"Particular\",\"doors\":4,\"capacity\":5,\"armored\":false,\"import\":true,\"fuel\":\"Gasolina\",\"color\":\"Blanco\",\"transmission\":\"Mecánica\"}]},{\"name\":\"Carnival\",\"line\":\"Zenith\",\"class\":\"Suv\",\"model\":\"2021\",\"mileage\":0,\"cylinder\":3500,\"price\":209990000,\"stock\":true,\"features\":[{\"service\":\"Particular\",\"doors\":4,\"capacity\":7,\"armored\":false,\"import\":true,\"fuel\":\"Gasolina\",\"color\":\"Azul\",\"transmission\":\"Automática\"}]},{\"name\":\"Niro\",\"line\":\"Emotion\",\"class\":\"Híbrido\",\"model\":\"2021\",\"mileage\":0,\"cylinder\":1600,\"price\":118990000,\"stock\":true,\"features\":[{\"service\":\"Particular\",\"doors\":4,\"capacity\":5,\"armored\":false,\"import\":true,\"fuel\":\"Híbrido\",\"color\":\"Azul\",\"transmission\":\"Automática\"}]},{\"name\":\"Stonic\",\"line\":\"Vibrant\",\"class\":\"Híbrido\",\"model\":\"2021\",\"mileage\":0,\"cylinder\":1000,\"price\":83490000,\"stock\":true,\"features\":[{\"service\":\"Particular\",\"doors\":4,\"capacity\":5,\"armored\":false,\"import\":true,\"fuel\":\"Híbrido\",\"color\":\"Rojo\",\"transmission\":\"Automática\"}]}]";
    private RecyclerView recyclerView_vehicles;
    private RecyclerView.Adapter mAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_vehicles,container,false);

        recyclerView_vehicles = root.findViewById(R.id.rev_vehicles);
        recyclerView_vehicles.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView_vehicles.setLayoutManager(new GridLayoutManager(getActivity(),2)); grid con columns

        try {
            JSONArray jsonVehicles = new JSONArray(vehicles);

            mAdapter = new VehiclesAdaptder(jsonVehicles, getActivity());

            recyclerView_vehicles.setAdapter(mAdapter);

            JSONObject vehicles0 = jsonVehicles.getJSONObject(0);
            String name = vehicles0.getString("name");

            Toast.makeText(getActivity(), "Nombre: "+name, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

class VehiclesAdaptder extends RecyclerView.Adapter<VehiclesAdaptder.ViewHolder> {

    private JSONArray vehicles;
    private Activity miActivity;

    public VehiclesAdaptder(JSONArray vehicles, Activity miActivity) {
        this.vehicles = vehicles;
        this.miActivity = miActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_vehicles, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { //match entre la presentacion y la data

        try {
            //Log.e("POSICION", "POS: " + position);
            String name = this.vehicles.getJSONObject(position).getString("name");
            String line = this.vehicles.getJSONObject(position).getString("line");
            String model = this.vehicles.getJSONObject(position).getString("model");
            String mileage = this.vehicles.getJSONObject(position).getString("mileage");
            String cylinder = this.vehicles.getJSONObject(position).getString("cylinder");
            String price = this.vehicles.getJSONObject(position).getString("price");

            //String categoria = this.productos.getJSONObject(position).getString("categoria");
            //int precio = this.productos.getJSONObject(position).getInt("precio");
            //String imagen = this.productos.getJSONObject(position).getString("imagen");

            holder.textview_item_name.setText("Name: " + name);
            holder.textview_item_line.setText("Line: " + line);
            holder.textview_item_model.setText("Model: " + model);
            holder.textview_item_mileage.setText("Mileage: " + mileage + "Km");
            holder.textview_item_cylinder.setText("Cylinder: " + cylinder + "cc");
            holder.textview_item_price.setText("Price: $" + price);

            //holder.tev_item_categoria.setText(categoria);
            //holder.tev_item_precio.setText("$" + precio);



        } catch (JSONException e) {
            holder.textview_item_name.setText("error");
        }

    }

    @Override
    public int getItemCount() {
        //Log.e("CANTIDAD_VEHICLES", "" + this.vehicles.length());
        return this.vehicles.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder { //recorre los items
        private TextView textview_item_name;
        private TextView textview_item_line;
        private TextView textview_item_model;
        private TextView textview_item_mileage;
        private TextView textview_item_cylinder;
        private TextView textview_item_price;


        public ViewHolder(View v) {
            super(v);
            textview_item_name = v.findViewById(R.id.txt_item_name);
            textview_item_line = v.findViewById(R.id.txt_item_line);
            textview_item_model = v.findViewById(R.id.txt_item_model);
            textview_item_mileage = v.findViewById(R.id.txt_item_mileage);
            textview_item_cylinder = v.findViewById(R.id.txt_item_cylinder);
            textview_item_price = v.findViewById(R.id.txt_item_price);

        }
    }
}