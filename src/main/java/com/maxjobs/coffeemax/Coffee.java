package com.maxjobs.coffeemax;

import java.util.UUID;

public class Coffee {
    private String name;
    private final String id;

   // Constructor for the class with 2 params
    // @id and @name to store name and id of a product
    public Coffee(String id, String name){
        this.id = id;
        this.name = name;
    }
    /**
     * FIND OUT MORE ABOUT THIS TYPE OF CONSTRUCTOR DEFINITION
     * This provides a unique identifier if none is provided for the the
     * field upon Coffee creation.
     * */
    public Coffee(String name){ // what is it going to do?
        this(UUID.randomUUID().toString(), name);
    }

   // Getters for the class attributes
    public String getId(){  // does not need a mutator method
        return id;  // set to final so that once set, it doesn't change
    }

    public String getName(){
        return name;
    }

    // Stetter for name field
    public void setName(String name){
        this.name = name;
    }
}
