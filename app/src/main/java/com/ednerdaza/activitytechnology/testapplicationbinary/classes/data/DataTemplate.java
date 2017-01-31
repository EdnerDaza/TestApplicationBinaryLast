package com.ednerdaza.activitytechnology.testapplicationbinary.classes.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.ClientesEntityDao;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.PermisosEntityDao;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.UsuariosEntityDao;

/**
 * Created by administrador on 15/12/14.
 */
public class DataTemplate {

    public static SQLiteDatabase db;
    public static void generarDatos(SQLiteDatabase db) {
        DataTemplate.db = db;

        ContentValues usuario = new ContentValues();

        //Repetir tantas veces como registros queramos
        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 110 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "EDNER");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 7);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 111 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "INVITADO");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 0);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 112 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "ADMIN");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 3);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 113 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "ACTIVITY");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 6);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 114 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "ACTIVITY2");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 5);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 115 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "ACTIVITY3");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 4);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 116 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "ACTIVITY4");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 1);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        usuario.put(UsuariosEntityDao.Properties.Id.columnName, 117 );
        usuario.put(UsuariosEntityDao.Properties.Nombre.columnName, "ERASER");
        usuario.put(UsuariosEntityDao.Properties.Contrasena.columnName, "1234");
        usuario.put(UsuariosEntityDao.Properties.NivelPermiso.columnName, 2);
        db.insert(UsuariosEntityDao.TABLENAME, "", usuario);

        ContentValues permisos = new ContentValues();

        permisos.put(PermisosEntityDao.Properties.Id.columnName, 10 );
        permisos.put(PermisosEntityDao.Properties.Modulo.columnName, "CREAR");
        db.insert(PermisosEntityDao.TABLENAME, "", permisos);

        permisos.put(PermisosEntityDao.Properties.Id.columnName, 11 );
        permisos.put(PermisosEntityDao.Properties.Modulo.columnName, "ELIMINAR");
        db.insert(PermisosEntityDao.TABLENAME, "", permisos);

        permisos.put(PermisosEntityDao.Properties.Id.columnName, 12 );
        permisos.put(PermisosEntityDao.Properties.Modulo.columnName, "EDITAR");
        db.insert(PermisosEntityDao.TABLENAME, "", permisos);

        ContentValues clientes = new ContentValues();

        clientes.put(ClientesEntityDao.Properties.Id.columnName, 1110 );
        clientes.put(ClientesEntityDao.Properties.Cedula.columnName, 15234674);
        clientes.put(ClientesEntityDao.Properties.Nombre.columnName, "Adalberto Gonzalez");
        clientes.put(ClientesEntityDao.Properties.Telefono.columnName, "5678876");
        clientes.put(ClientesEntityDao.Properties.Email.columnName, "adalberto.gonzalez@gmail.com");
        db.insert(ClientesEntityDao.TABLENAME, "", clientes);

        clientes.put(ClientesEntityDao.Properties.Id.columnName, 1111 );
        clientes.put(ClientesEntityDao.Properties.Cedula.columnName, 15234647);
        clientes.put(ClientesEntityDao.Properties.Nombre.columnName, "Julian Arana");
        clientes.put(ClientesEntityDao.Properties.Telefono.columnName, "5678345");
        clientes.put(ClientesEntityDao.Properties.Email.columnName, "julian.arana@gmail.com");
        db.insert(ClientesEntityDao.TABLENAME, "", clientes);

        clientes.put(ClientesEntityDao.Properties.Id.columnName, 1112 );
        clientes.put(ClientesEntityDao.Properties.Cedula.columnName, 11434674);
        clientes.put(ClientesEntityDao.Properties.Nombre.columnName, "Yair Sierra");
        clientes.put(ClientesEntityDao.Properties.Telefono.columnName, "5678234");
        clientes.put(ClientesEntityDao.Properties.Email.columnName, "yair.sierra@gmail.com");
        db.insert(ClientesEntityDao.TABLENAME, "", clientes);

        clientes.put(ClientesEntityDao.Properties.Id.columnName, 1113 );
        clientes.put(ClientesEntityDao.Properties.Cedula.columnName, 15234090);
        clientes.put(ClientesEntityDao.Properties.Nombre.columnName, "Priya Shari");
        clientes.put(ClientesEntityDao.Properties.Telefono.columnName, "5678767");
        clientes.put(ClientesEntityDao.Properties.Email.columnName, "priya.shari@gmail.com");
        db.insert(ClientesEntityDao.TABLENAME, "", clientes);

        clientes.put(ClientesEntityDao.Properties.Id.columnName, 1114 );
        clientes.put(ClientesEntityDao.Properties.Cedula.columnName, 15278090);
        clientes.put(ClientesEntityDao.Properties.Nombre.columnName, "Jack Sparrow");
        clientes.put(ClientesEntityDao.Properties.Telefono.columnName, "5600767");
        clientes.put(ClientesEntityDao.Properties.Email.columnName, "jack.sparrow@gmail.com");
        db.insert(ClientesEntityDao.TABLENAME, "", clientes);

    }

}
