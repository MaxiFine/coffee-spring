package com.maxjobs.coffeemax;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Refactoring to reduce repetition in code with method with
 * */

@RestController
@RequestMapping("/coffees")
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
    // takes no argument when @ReequestMapping is specified at the
    // at the controller level.
    @GetMapping()  // becomes the base url or home of GET request
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

    // Controller to get item by its ID config
    /**
     * Note that a PathVariable was used when we wanted to get an
     * item by ID. it was used to pass the id as a parameter to the
     * getCoffeeById method
     * */
    // takes only the '/{id}' parameter when the @RequestMapping is set
    // at the Controller class level.
    @GetMapping("/{id}")
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
    // does not take any param because of @RequestMapping
    // at the Controller class level
    @PostMapping()
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
     * Identify how that was achieved using the ResponseEntity<>class
     **/
    // Takes only the '/{id}' param when the @RequestMapping is set at the
    // Controller class level.
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeeIndex = -1;

        for(Coffee c: coffees){
            if(c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        /**
         * For our put method, it con do two things of which the results are
         * all different. Therefor we need to specify the response to know the
         * type of action that was performed. This is done by using the
         * ResponseEntity class to identify the action that was performed.
         * */
        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }
    /**
     * DeleteMapping seems different as its a void method
     * that takes a PathVariable and removes that object by the id
     * that's passed to the PathVariable.
     * It also uses the removeIf Collection method to remove the item.
     * removeIf accepts a Predicate, meaning we can provide a lambda that
     * will evaluate a boolean value of true for the desired coffee to remove
     * */
    // Takes only the '/{id}' param when the @RequestMapping is set at the
    // Controller class level.
    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id){
        coffees.removeIf(c -> c.getId().equals(id));
    }





}