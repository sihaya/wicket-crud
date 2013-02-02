/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import java.util.Arrays;
import java.util.List;
import javax.persistence.metamodel.SingularAttribute;
import nl.desertspring.wicketcrud.EntityModel.SimpleSingularAttribute;
import nl.desertspring.wicketcrud.sample.Car;
import nl.desertspring.wicketcrud.sample.Car_;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.mockito.Mockito.*;
/**
 *
 * @author sihaya
 */
public class EditPanelTest
{
    @Test
    public void givenAnEntityModelItRendersInputBoxes() {
        WicketTester wicketTester = new WicketTester();
        
        Car car = mock(Car.class);
        
        EntityModel<Car> entityModel = mock(EntityModel.class);        
        SimpleSingularAttribute name = mock(SimpleSingularAttribute.class);                
        List<SimpleSingularAttribute> result = Arrays.<SimpleSingularAttribute>asList(name);
        
        when(entityModel.getSingularAttributes()).thenReturn(result);
        when(name.getValue(car)).thenReturn("the name");
        
        EditPanel<Car> editPanel = new EditPanel<Car>("component", entityModel, Model.of(car));
                
        wicketTester.startComponentInPage(editPanel);
                
        wicketTester.assertModelValue("component:items:0:field", "the name");
    }
}
