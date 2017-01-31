package com.ednerdaza.activitytechnology.testapplicationbinary.classes.helpers;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ednerdaza.activitytechnology.testapplicationbinary.R;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.Interfaces.DelegateItemAdapter;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.ClientesEntity;


/**
 * Created by administrador on 10/06/16.
 */
public class Helpers {


    public static Activity activity;
    public static Activity getActivity() {
        return activity;
    }
    public static void setActivity(Activity activity) {
        Helpers.activity = activity;
    }

    public static Context _contexto;
    public static Context getContexto() {
        return _contexto;
    }
    public static void setContexto(Context _contexto) {
        Helpers._contexto = _contexto;
    }

    private static ProgressDialog progressDialog;
    public static ProgressDialog getProgressDialog() {
        return progressDialog;
    }
    public static void setProgressDialog(ProgressDialog progressDialog) {
        Helpers.progressDialog = progressDialog;
    }

    private static DelegateItemAdapter mDelegateItemAdapter;
    public static DelegateItemAdapter getDelegate() {
        return mDelegateItemAdapter;
    }
    public static void setDelegate(DelegateItemAdapter mDelegateItemAdapter) {
        Helpers.mDelegateItemAdapter = mDelegateItemAdapter;
    }

    private static Intent intent;

    public static AlertDialog customDialogConnection(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle(resources.getString(R.string.store));
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(getContexto().getResources().getString(R.string.accept_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });

        builder.setNeutralButton(getContexto().getResources().getString(R.string.network_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        dialog.cancel();
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        getActivity().startActivity(intent);
                    }
                });

        final AlertDialog dialog = builder.create();
        return dialog;
    }

    public static AlertDialog customDialogDelete(Context context, String message, final ClientesEntity clientesEntity, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.action_eliminar));
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getResources().getString(R.string.accept_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(mDelegateItemAdapter!=null){
                            mDelegateItemAdapter.onDeleteClicked(clientesEntity, view);
                        }
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });

        builder.setNeutralButton(context.getResources().getString(R.string.cancel_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                });

        final AlertDialog dialog = builder.create();
        TextView msg = new TextView(context);
        msg.setGravity(Gravity.CENTER);
        return dialog;
    }

    public static AlertDialog customDialogMessage(Context context, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.action_info));
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getResources().getString(R.string.accept_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
        final AlertDialog dialog = builder.create();
        TextView msg = new TextView(context);
        msg.setGravity(Gravity.CENTER);
        return dialog;

    } // FIN DEL METODO

    public static ProgressDialog customProgressDialog(){
        if (progressDialog != null){
            customProgressDialogClose();
        }
        progressDialog = new ProgressDialog(getActivity());
        return progressDialog;

    }

    public static void customProgressDialogClose() {
        if ((progressDialog != null) && progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog = null;
    }

    /**
     * METODO QUE MUESTRA UN LOADING
     */
    public static void progressDialogLoadingShow(String message){
        // SI EL LOADING ES DIFERENTE DE NULO, LO CERRAMOS
        if (progressDialog != null){
            progressDialogClose();
        }
        // Y CREAMOS UNO NUEVO
        progressDialog = customProgressDialog().show(getContexto(), "", message, true, false);
    }

    /**
     * METODO QUE OCULTA UN LOADING
     */
    public static void progressDialogClose() {
        // SI EL LOADING ES DIFERENTE DE NULO Y SE ESTA MOSTRANDO LO CERRAMOS
        if ((progressDialog != null) && progressDialog.isShowing())
            progressDialog.dismiss();
        // LO CONVERTIMOS EN NULO
        progressDialog = null;
    }


}
