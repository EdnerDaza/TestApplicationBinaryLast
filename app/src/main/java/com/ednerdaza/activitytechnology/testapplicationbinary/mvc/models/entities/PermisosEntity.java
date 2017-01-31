package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "PERMISOS_ENTITY".
 */
@Entity
public class PermisosEntity {

    @Id
    private Long id;
    private String Modulo;

    @Generated
    public PermisosEntity() {
    }

    public PermisosEntity(Long id) {
        this.id = id;
    }

    @Generated
    public PermisosEntity(Long id, String Modulo) {
        this.id = id;
        this.Modulo = Modulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModulo() {
        return Modulo;
    }

    public void setModulo(String Modulo) {
        this.Modulo = Modulo;
    }

}