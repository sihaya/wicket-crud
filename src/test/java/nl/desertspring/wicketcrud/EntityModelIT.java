/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import javax.persistence.EntityManager;
import nl.desertspring.wicketcrud.sample.Car;
import static org.junit.Assert.*;
/**
 *
 * @author sihaya
 */
public class EntityModelIT
{
    private EntityManager entityManager;
    
    public void givenAnEntityModelItGetsTheValue() {
        Car car = new Car();
        car.setName("name");
        
        EntityModel<Car> model = new EntityModel(entityManager.getMetamodel().entity(Car.class));
                
        assertEquals("name", model.getSingularAttributes().get(0).getValue(car));
    }
}
