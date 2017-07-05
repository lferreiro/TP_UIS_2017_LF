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
import android.widget.TextView;
import android.widget.Toast;

import com.uis.carmensandiego.carmensandiego.adapter.LugaresAdapter;
import com.uis.carmensandiego.carmensandiego.model.Caso;
import com.uis.carmensandiego.carmensandiego.model.Pista;
import com.uis.carmensandiego.carmensandiego.service.CarmenSanDiegoService;
import com.uis.carmensandiego.carmensandiego.service.Connection;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PistasFragment extends Fragment {

    private ListView lvLugares;

    public PistasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pistas, container, false);
        obtenerLugares(view);
        final ListView lv = (ListView) view.findViewById(R.id.listLugares);
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
        Caso caso = ((MainActivity) getActivity()).getCaso();

        CarmenSanDiegoService carmenSanDiegoService = new Connection().getService();
        carmenSanDiegoService.getPista(caso.getId(), lugar, new Callback<Pista>() {
            @Override
            public void success(Pista pista, Response response) {
                    llenarPista(pista);
            }

            @Override
            public void failure(RetrofitError error) {
                    Log.e("error", error.getMessage());
                    error.printStackTrace();
            }
        });

    }

    public void llenarPista(Pista pista){
        final TextView pistasTw = ((TextView) getView(). findViewById(R.id.textoPistas));
        if(pista.getResultadoOrden() == null){
            pistasTw.setText(pista.getPista());
        }
        else{
            pistasTw.setText("Resultado :" + pista.getResultadoOrden());
        }
    }
}

