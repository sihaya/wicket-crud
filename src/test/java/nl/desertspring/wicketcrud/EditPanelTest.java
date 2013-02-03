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
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class EditPanelTest
{

    WicketTester wicketTester;
    EditPanel<Car> editPanel;
    Model model;
    CrudRepository crudRepository;
    Car car;
    EntityModel<Car> entityModel;
    SimpleSingularAttribute name;

    @Before
    public void setUp()
    {
        wicketTester = new WicketTester();
        
        crudRepository = mock(CrudRepository.class);

        car = mock(Car.class);

        entityModel = mock(EntityModel.class);
        name = mock(SimpleSingularAttribute.class);
        List<SimpleSingularAttribute> result = Arrays.<SimpleSingularAttribute>asList(name);

        when(entityModel.getSingularAttributes()).thenReturn(result);
        when(name.getValue(car)).thenReturn("the name");

        model = Model.of(car);

        editPanel = new EditPanel<Car>("component", crudRepository, entityModel, model);
    }

    @Test
    public void givenAnEntityModelItRendersInputBoxes()
    {
        wicketTester.startComponentInPage(editPanel);

        wicketTester.assertModelValue("component:form:items:0:field", "the name");
    }

    @Test
    public void givenAnEntityWhenSavingItMerges()
    {
        wicketTester.startComponentInPage(editPanel);
        
        when(crudRepository.merge(car)).thenReturn(car);
        
        FormTester formTester = wicketTester.newFormTester("component:form");
        formTester.setValue("items:0:field", "the new value");
        
        formTester.submit("save");
        
        verify(name).setValue(car, "the new value");
        verify(crudRepository).merge(car);
    }
}
