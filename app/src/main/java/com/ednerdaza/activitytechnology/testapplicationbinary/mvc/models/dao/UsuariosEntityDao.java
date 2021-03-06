package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.UsuariosEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USUARIOS_ENTITY".
*/
public class UsuariosEntityDao extends AbstractDao<UsuariosEntity, Long> {

    public static final String TABLENAME = "USUARIOS_ENTITY";

    /**
     * Properties of entity UsuariosEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nombre = new Property(1, String.class, "Nombre", false, "NOMBRE");
        public final static Property Contrasena = new Property(2, String.class, "Contrasena", false, "CONTRASENA");
        public final static Property NivelPermiso = new Property(3, Integer.class, "NivelPermiso", false, "NIVEL_PERMISO");
    }


    public UsuariosEntityDao(DaoConfig config) {
        super(config);
    }
    
    public UsuariosEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USUARIOS_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NOMBRE\" TEXT," + // 1: Nombre
                "\"CONTRASENA\" TEXT," + // 2: Contrasena
                "\"NIVEL_PERMISO\" INTEGER);"); // 3: NivelPermiso
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USUARIOS_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UsuariosEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Nombre = entity.getNombre();
        if (Nombre != null) {
            stmt.bindString(2, Nombre);
        }
 
        String Contrasena = entity.getContrasena();
        if (Contrasena != null) {
            stmt.bindString(3, Contrasena);
        }
 
        Integer NivelPermiso = entity.getNivelPermiso();
        if (NivelPermiso != null) {
            stmt.bindLong(4, NivelPermiso);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UsuariosEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Nombre = entity.getNombre();
        if (Nombre != null) {
            stmt.bindString(2, Nombre);
        }
 
        String Contrasena = entity.getContrasena();
        if (Contrasena != null) {
            stmt.bindString(3, Contrasena);
        }
 
        Integer NivelPermiso = entity.getNivelPermiso();
        if (NivelPermiso != null) {
            stmt.bindLong(4, NivelPermiso);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UsuariosEntity readEntity(Cursor cursor, int offset) {
        UsuariosEntity entity = new UsuariosEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Nombre
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Contrasena
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3) // NivelPermiso
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UsuariosEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombre(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContrasena(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNivelPermiso(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UsuariosEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UsuariosEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UsuariosEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
