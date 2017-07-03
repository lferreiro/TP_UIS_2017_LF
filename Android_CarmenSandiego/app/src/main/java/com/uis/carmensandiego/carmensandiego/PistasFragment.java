package com.uis.carmensandiego.carmensandiego;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.uis.carmensandiego.carmensandiego.adapter.LugaresAdapter;
import com.uis.carmensandiego.carmensandiego.model.Caso;
import com.uis.carmensandiego.carmensandiego.service.CarmenSanDiegoService;
import com.uis.carmensandiego.carmensandiego.service.Connection;

public class PistasFragment extends Fragment {

    private ListView lvLugares;

    public PistasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pistas, container, false);
        obtenerLugares(view);
        final ListView lv = (ListView) view.findViewById(R.id.listLugares);
        //NO ANDA
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lugarSeleccionado = (String) (lv.getItemAtPosition(position));
                mostrarPistas(lugarSeleccionado);
            }
        });

        return view;
    }

    public void obtenerLugares(View view) {
        Caso caso = ((MainActivity) getActivity()).getCaso();
        lvLugares = (ListView) view.findViewById(R.id.listLugares);
        LugaresAdapter adapter = new LugaresAdapter(getActivity(),caso.getPais().getLugares());
        lvLugares.setAdapter(adapter);
    }

    public void mostrarPistas(String lugar){

    }

    /*int idVillanoSeleccionado = getIdVillano(villanos, nombreVillanoSeleccionado);

    Toast toastOrdenEmitida = Toast.makeText(getContext(), "Orden emitida exitosamente contra: "+ nombreVillanoSeleccionado, Toast.LENGTH_SHORT);
        toastOrdenEmitida.setGravity(Gravity.CENTER, 0, 0);

        toastOrdenEmitida.show();*/
}

