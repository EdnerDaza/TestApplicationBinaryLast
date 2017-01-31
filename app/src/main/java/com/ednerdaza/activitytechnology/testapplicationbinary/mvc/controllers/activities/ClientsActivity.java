package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ednerdaza.activitytechnology.testapplicationbinary.R;
import com.ednerdaza.activitytechnology.testapplicationbinary.classes.helpers.Helpers;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.Interfaces.DelegateItemAdapter;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.adapters.AdapterItems;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.ClientesEntityDao;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.DaoMaster;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.DaoSession;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.PermisosEntityDao;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.UsuariosEntityDao;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.ClientesEntity;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.PermisosEntity;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.UsuariosEntity;

import java.util.List;

public class ClientsActivity extends AppCompatActivity implements DelegateItemAdapter {

    //Variables relativas a Green Dao Database
    public DaoSession daoSession;
    public DaoMaster daoMaster;
    private SQLiteDatabase db;
    private UsuariosEntityDao usuariosDao;
    private List<UsuariosEntity> usuarios;
    private Context mContext;
    private UsuariosEntity mUsuarioActual;
    private Long mUsuarioActualId;

    RecyclerView recyclerView ;
    LinearLayoutManager linearLayoutManager;
    AdapterItems adapterItems;
    Context context;
    NavigationView navigationView;
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    private TextView textView;
    private ClientesEntityDao clientesDao;
    private List<ClientesEntity> mClientesActuales;
    private List<ClientesEntity> mListClientes;
    private PermisosEntityDao permisosDao;
    private List<PermisosEntity> mPermisosActuales;
    private boolean mPermisoCrear, mPermisoEditar, mPermisoEliminar;
    private boolean[] permisos;
    private static final int EDITAR_ELIMINAR_GESTOR = 1;

    //region LIFE CYCLE METHODS

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mContext = this;
            setContentView(R.layout.activity_clients);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);



            Intent intent = this.getIntent();
            mUsuarioActualId = (Long)intent.getLongExtra("id",0);

            //Conexión a la base de datos
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "basededatos-1-db", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();

            //Obtenemos un objeto de tipo "GestorDao" que manipula por nosotros
            //la tabla Gestor de la Base de Datos
            usuariosDao = daoSession.getUsuariosEntityDao();
            clientesDao = daoSession.getClientesEntityDao();
            permisosDao = daoSession.getPermisosEntityDao();

            //Obtiene listado de Gestores, equivale a: SELECT * FROM GESTOR
            mUsuarioActual = usuariosDao.load(mUsuarioActualId);
            mClientesActuales = clientesDao.loadAll();
            mPermisosActuales = permisosDao.loadAll();

            permisos = getPermissions(mUsuarioActual.getNivelPermiso());

            setTitle(mUsuarioActual.getNombre());

            recyclerView = (RecyclerView)findViewById(R.id.rv_root);
            recyclerView.setHasFixedSize(true);

            linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(permisos[0]){
                        Intent intent = new Intent(ClientsActivity.this, AddClientActivity.class);
                        intent.putExtra("id", mUsuarioActualId);
                        startActivity(intent);
                        //finish();
                    }else{
                        Snackbar.make(view, mUsuarioActual.getNombre()+" NO TIENE PERMISO DE AGREGAR NUEVO CLIENTE",
                                Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            });

            drawList(mClientesActuales);
            Helpers.setDelegate(this);
        }

    //endregion

    //region MENU METHODS

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_client, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_info) {
                String message = String.format(getResources().getString(R.string.message_user),
                        mUsuarioActual.getNombre(),
                        mUsuarioActual.getNivelPermiso().toString().trim(),
                        getPermissionsString(mUsuarioActual.getNivelPermiso()),
                        getPermissionsUser());
                Helpers.customDialogMessage(mContext, message).show();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    //endregion

    //region DELEGATE ITEM ADAPTER METHODS

        @Override
        public void onItemClicked(ClientesEntity entityResponse) {

        }

        @Override
        public void onEditClicked(ClientesEntity entityResponse, View view) {
            if(permisos[2]){
               /* Intent intent = new Intent(ClientsActivity.this, AddClientActivity.class);
                intent.putExtra("id", mUsuarioActualId);
                startActivity(intent);*/
                //finish();
                Snackbar.make(view, "EDITANDO ARCHIVO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(ClientsActivity.this, EditClientActivity.class);
                intent.putExtra("idUser", mUsuarioActualId);
                intent.putExtra("idClient", entityResponse.getId());
                startActivity(intent);

                //clientesDao.update(entityResponse);

                //Si se llega hasta aquí todo va bien, y se devuelve OK
                //setResult(RESULT_OK);
            }else{
                Snackbar.make(view, mUsuarioActual.getNombre()+" NO TIENE PERMISO DE EDICION", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        @Override
        public void onDeleteClicked(ClientesEntity entityResponse, View view) {
            if(permisos[1]){
                /*Intent intent = new Intent(ClientsActivity.this, AddClientActivity.class);
                intent.putExtra("id", mUsuarioActualId);
                startActivity(intent);*/
                //finish();

                //Helpers.customDialogDelete(mContext, "Desea ud borrar").show();

                Snackbar.make(view, "BORRANDO ARCHIVO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Evento disparado al pulsar sobre el botón Eliminar
                clientesDao.delete(entityResponse);

                //Si se llega hasta aquí todo va bien, y se devuelve OK
                setResult(RESULT_OK);
                recyclerView.setAdapter(null);
                mClientesActuales = clientesDao.loadAll();
                drawList(mClientesActuales);
                //finish();
            }else{
                Snackbar.make(view, mUsuarioActual.getNombre()+" NO TIENE PERMISO DE BORRAR", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

    //endregion

    //region ON ACTIVITY RESULT METHOD

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            //Se evalua lo recibido, si es OK, se procede a actualizar la interfaz
            if (resultCode == Activity.RESULT_OK && requestCode == EDITAR_ELIMINAR_GESTOR) {

                Toast.makeText(this, "Operación realizada con éxito", Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(null);
                mClientesActuales = clientesDao.loadAll();
                drawList(mClientesActuales);

            }
        }

    //endregion

    //region PRIVATE METHODS

    private boolean[] getPermissions(int nivelPermiso) {

        int permisoDecimales = 3;
        int[] binarioIntArray = new int[]{0,0,0};
        boolean[] binarioBooleanArray = new boolean[]{false,false,false};
        int decimal = nivelPermiso;

        while ( decimal > 0 ) {
            int bin = decimal % 2;
            binarioIntArray[--permisoDecimales] = bin;
            decimal /= 2;
        }

        for(int i = 0; i < binarioIntArray.length; i++) {
            binarioBooleanArray[i] = (binarioIntArray[i] != 0);
        }

        return binarioBooleanArray;
    }

    private String getPermissionsString (int nivelPermiso) {
        int permisoDecimales = 3;
        String[] binarioStringArray = new String[]{"0","0","0"};
        int decimal = nivelPermiso;
        String binario = "";
        while ( decimal > 0 ) {
            int bin = decimal % 2;
            binarioStringArray[--permisoDecimales] = bin+"";
            decimal /= 2;
        }

        for(int i = 0; i < binarioStringArray.length; i++) {
            binario = binario + binarioStringArray[i];
        }

        return binario;
    }

    private String getPermissionsUser(){
        String permiso = "";
        boolean[] permisos = getPermissions(mUsuarioActual.getNivelPermiso());

        for(int i = 0; i < permisos.length; i++) {
            if(permisos[i]){
                permiso = permiso + ", " +mPermisosActuales.get(i).getModulo();
            }
        }
        return permiso;
    }

    private void drawList(List<ClientesEntity> clientes) {
        //arrayListDataList.clear();
        mListClientes = clientes;
        adapterItems = new AdapterItems(ClientsActivity.this, mListClientes);
        adapterItems.setDelegate(this);
        recyclerView.setAdapter(adapterItems);
        adapterItems.notifyDataSetChanged();
    }

    //endregion

}
