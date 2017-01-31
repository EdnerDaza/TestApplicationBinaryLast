package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.ClientesEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CLIENTES_ENTITY".
*/
public class ClientesEntityDao extends AbstractDao<ClientesEntity, Long> {

    public static final String TABLENAME = "CLIENTES_ENTITY";

    /**
     * Properties of entity ClientesEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Cedula = new Property(1, Integer.class, "Cedula", false, "CEDULA");
        public final static Property Nombre = new Property(2, String.class, "Nombre", false, "NOMBRE");
        public final static Property Telefono = new Property(3, String.class, "Telefono", false, "TELEFONO");
        public final static Property Email = new Property(4, String.class, "Email", false, "EMAIL");
    }


    public ClientesEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ClientesEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLIENTES_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CEDULA\" INTEGER," + // 1: Cedula
                "\"NOMBRE\" TEXT," + // 2: Nombre
                "\"TELEFONO\" TEXT," + // 3: Telefono
                "\"EMAIL\" TEXT);"); // 4: Email
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLIENTES_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ClientesEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer Cedula = entity.getCedula();
        if (Cedula != null) {
            stmt.bindLong(2, Cedula);
        }
 
        String Nombre = entity.getNombre();
        if (Nombre != null) {
            stmt.bindString(3, Nombre);
        }
 
        String Telefono = entity.getTelefono();
        if (Telefono != null) {
            stmt.bindString(4, Telefono);
        }
 
        String Email = entity.getEmail();
        if (Email != null) {
            stmt.bindString(5, Email);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ClientesEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer Cedula = entity.getCedula();
        if (Cedula != null) {
            stmt.bindLong(2, Cedula);
        }
 
        String Nombre = entity.getNombre();
        if (Nombre != null) {
            stmt.bindString(3, Nombre);
        }
 
        String Telefono = entity.getTelefono();
        if (Telefono != null) {
            stmt.bindString(4, Telefono);
        }
 
        String Email = entity.getEmail();
        if (Email != null) {
            stmt.bindString(5, Email);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ClientesEntity readEntity(Cursor cursor, int offset) {
        ClientesEntity entity = new ClientesEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // Cedula
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Nombre
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Telefono
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // Email
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ClientesEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCedula(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setNombre(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTelefono(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmail(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ClientesEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ClientesEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ClientesEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
