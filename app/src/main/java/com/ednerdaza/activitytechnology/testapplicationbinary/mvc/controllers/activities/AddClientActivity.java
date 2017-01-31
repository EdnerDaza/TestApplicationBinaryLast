package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ednerdaza.activitytechnology.testapplicationbinary.R;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.ClientesEntityDao;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.DaoMaster;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.DaoSession;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.ClientesEntity;

public class AddClientActivity extends AppCompatActivity implements View.OnClickListener{

    //Variables propias
    private EditText nombreCliente, cedulaCliente, telefonoCliente, emailCliente;
    private ClientesEntityDao mClientesDao;
    private Button mButtonAddCliente;

    //Variables relativas a Green Dao Database
    public DaoSession daoSession;
    public DaoMaster daoMaster;
    private SQLiteDatabase db;
    private Long mUsuarioActualId;

    //region LIFE CYCLE METHODS

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_client);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            Intent intent = this.getIntent();
            mUsuarioActualId = (Long)intent.getLongExtra("id",0);

            //Obtiene el texto del EditText
            nombreCliente = (EditText) findViewById(R.id.edittext_nombre_add);
            cedulaCliente = (EditText) findViewById(R.id.edittext_cedula_add);
            telefonoCliente = (EditText) findViewById(R.id.edittext_telefono_add);
            emailCliente = (EditText) findViewById(R.id.edittext_email_add);

            mButtonAddCliente = (Button) findViewById(R.id.add_client_button);
            mButtonAddCliente.setOnClickListener(this);

            //Conexión a la base de datos
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "basededatos-1-db", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();

            mClientesDao = daoSession.getClientesEntityDao();

        }

    //endregion

    //region View.OnClickListener METHODS

        @Override
        public void onClick(View view) {
            guardarGestor(view);
        }

    //endregion

    //region PRIVATE METHODS

        private void guardarGestor(View v){

        //Se obtiene el nombre del EditText
        String nombre = nombreCliente.getText().toString().trim();
        String cedula = cedulaCliente.getText().toString().trim();
        String telefono = telefonoCliente.getText().toString().trim();
        String email = emailCliente.getText().toString().trim();

        if(!nombre.equals("")){
            if(!cedula.equals("")){
                if(!telefono.equals("")){
                    if(!email.equals("")){
                        //Se crea nuevo gestor
                        ClientesEntity clienteNuevo = new ClientesEntity();
                        clienteNuevo.setNombre(nombre);
                        clienteNuevo.setCedula(Integer.parseInt(cedula));
                        clienteNuevo.setTelefono(telefono);
                        clienteNuevo.setEmail(email);

                        //Se inserta el nuevo gestor en la base de datos
                        mClientesDao.insert(clienteNuevo);
                        Toast.makeText(this, "Se ha añadido "+nombre, Toast.LENGTH_SHORT).show();

                        //Realizada la operación, se envía a la página principal
                        Intent intent = new Intent(this, ClientsActivity.class);
                        intent.putExtra("id", mUsuarioActualId);
                        startActivity(intent);
                        finish();
                    }else{
                        Snackbar.make(v, "CAMPO EMAIL, VACIO", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }else{
                    Snackbar.make(v, "CAMPO TELEFONO, VACIO", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }else{
                Snackbar.make(v, "CAMPO CEDULA, VACIO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }else{
            Snackbar.make(v, "CAMPO NOMBRE, VACIO", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


    }

    //endregion

}
