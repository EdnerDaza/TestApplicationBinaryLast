package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.Interfaces;

import android.view.View;

import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.ClientesEntity;

/**
 * Created by administrador on 10/06/16.
 */
public interface DelegateItemAdapter {

    public void onItemClicked(ClientesEntity entityResponse);
    public void onEditClicked(ClientesEntity entityResponse, View view);
    public void onDeleteClicked(ClientesEntity entityResponse, View view);

}
