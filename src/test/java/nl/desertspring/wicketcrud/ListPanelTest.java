/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import java.util.Arrays;
import nl.desertspring.wicketcrud.sample.Car;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class ListPanelTest
{
    final String attr1Name = "attr1 name";
    WicketTester wicketTester;
    CrudRepository crudRepository;
    EntityModel<Car> entityModel;
    EntityModel.SimpleSingularAttribute attr1;
    
    @Before
    public void setUp() {
        wicketTester = new WicketTester();
        
        entityModel = mock(EntityModel.class);
        
        attr1 = mock(EntityModel.SimpleSingularAttribute.class);
        
        when(attr1.getName()).thenReturn(attr1Name);        
        when(entityModel.getSingularAttributes()).thenReturn(Arrays.asList(attr1));
        
        crudRepository = mock(CrudRepository.class);
    }

    @Test
    public void givenAnEntityItRendersAllPropertiesAsColumns()
    {
        ListPanel listPanel = new ListPanel("component", crudRepository, entityModel);
        
        wicketTester.startComponentInPage(listPanel);
        
        wicketTester.assertContains(attr1Name);
    }
    
    @Test
    public void givenAListOfEntitiesItRendersTheValues() {
        Car car1 = mock(Car.class);
        when(attr1.getValue(car1)).thenReturn("Name of the car");
        when(crudRepository.findAll(entityModel)).thenReturn(Arrays.asList(car1));
        
        ListPanel listPanel = new ListPanel("component", crudRepository, entityModel);
        
        wicketTester.startComponentInPage(listPanel);
        wicketTester.assertContains("Name of the car");
    }
}
