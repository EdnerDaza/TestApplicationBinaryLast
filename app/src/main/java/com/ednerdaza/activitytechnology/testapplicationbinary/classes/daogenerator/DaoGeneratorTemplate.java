package com.ednerdaza.activitytechnology.testapplicationbinary.classes.daogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;


public class DaoGeneratorTemplate {


    public static void main(String[]arg) throws Exception {
        //Primer parámetro: versión de la base de datos
        //Segundo parámetro: paquete de mi proyecto Android quiero que se exporten
        //los POJO que identifican a mis tablas en la Base de datos
        Schema schema = new Schema(1, "com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities");
        //Ficheros DAO de cada tabla de la base de datos los exporte al paquete
        //"com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao"
        // de mi proyecto Android
        //schema.enableKeepSectionsByDefault();
        //schema.enableActiveEntitiesByDefault();
        schema.setDefaultJavaPackageDao("com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao");
        //Se añade la base de datos
        addTables(schema);
        //Especificar la ruta del proyecto
        new DaoGenerator().generateAll(schema, "./app/src/menu_client/java/");

    }


    private static void addTables(Schema schema)
    {


        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        Entity PermisosEntity = schema.addEntity("PermisosEntity");
        PermisosEntity.addIdProperty();
        PermisosEntity.addStringProperty("Modulo");


        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        Entity UsuariosEntity = schema.addEntity("UsuariosEntity");
        UsuariosEntity.addIdProperty();
        UsuariosEntity.addStringProperty("Nombre");
        UsuariosEntity.addStringProperty("Contrasena");
        UsuariosEntity.addIntProperty("NivelPermiso");
        /*Property permisoTipoId = PermisosEntity.addLongProperty("NivelPermiso").notNull().getProperty();
        UsuariosEntity.addToMany(PermisosEntity,permisoTipoId);*/


        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        Entity ClientesEntity = schema.addEntity("ClientesEntity");
        ClientesEntity.addIdProperty();
        ClientesEntity.addIntProperty("Cedula");
        ClientesEntity.addStringProperty("Nombre");
        ClientesEntity.addStringProperty("Telefono");
        ClientesEntity.addStringProperty("Email");

    }

    // AHORA CLICK IZQUIERDO EN LA CLASE Y RUN DaoGeneratorTemplate.menu_client()!!

}

