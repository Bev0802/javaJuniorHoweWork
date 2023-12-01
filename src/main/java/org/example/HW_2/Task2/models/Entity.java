package org.example.HW_2.Task2.models;


import org.example.HW_2.Task2.Column;

import java.util.UUID;

@org.example.HW_2.Task2.Entity

public class Entity {

    @Column(name = "id", primaryKey = true)
    private UUID id;

}
