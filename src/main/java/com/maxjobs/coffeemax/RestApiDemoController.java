package com.maxjobs.coffeemax;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * To
 * */

@RestController
class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.addAll(List.of(
                new Coffee("Cafe Ceraza"),
                new Coffee("Cafe Ganodor"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cafe Tres Pontas")
        ));
    }

    // Get all coffees controller config
    @GetMapping("/coffees")
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

    // Controller to get item by its ID config
    /**
     * Note that a PathVariable was used when we wanted to get an
     * item by ID. it was used to pass the id as a parameter to the
     * getCoffeeById method
     * */
    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id){
        for (Coffee c : coffees){
            if(c.getId().equals(id)){
                return  Optional.of(c);
            }
        }
        return Optional.empty();
    }

    /**
     * @RequestBody was used in creating a new coffee object from the
     * Coffee class.
     * @RequestBody was also used at the parameter level of the
     * postCoffee method to create an object of the class to take the required
     * data to create the coffee object with the required data
     * it also used the coffees object from Coffee class and augmented it with
     * the coffee data of the postCoffee method
     */
    @PostMapping("/coffees")
    Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

    // Put Method for updating a resource
    // the IETF specifies that if an object to be updated does not exist,
    // then it should be created instead
    /**
     * Notice the PutMapping requires a PathVariable and a RequestBody
     * The PathVariable is used to get the id of the object if it exists and
     * updates the object, if not, it creates it anew.
     **/
    @PutMapping("coffees/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeeIndex = -1;

        for(Coffee c: coffees){
            if(c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }
    /**
     * DeleteMapping seems different as its a void method
     * that takes a PathVariable and removes that object by the id
     * that's passed to the PathVariable.
     * It also uses the removeIf Collection method to remove the item.
     * removeIf accepts a Predicate, meaning we can provide a lambda that
     * will evaluate a boolean value of true for the desired coffee to remove
     * */
    @DeleteMapping("/coffees/{id}")
    void deleteCoffee(@PathVariable String id){
        coffees.removeIf(c -> c.getId().equals(id));
    }





}