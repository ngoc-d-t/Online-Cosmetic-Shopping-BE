package com.ngocdt.tttn.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SkinType")
@Data
public class SkinType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
