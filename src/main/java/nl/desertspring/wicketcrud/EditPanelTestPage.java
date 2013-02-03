/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import javax.persistence.EntityManager;
import nl.desertspring.wicketcrud.sample.Car;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author sihaya
 */
public class EditPanelTestPage extends WebPage
{
    @SpringBean
    private EntityModelFactory entityModelFactory;
    
    @SpringBean
    private CrudRepository crudRepository;
    
    public EditPanelTestPage() {
        add(new ListPanel("listPanel", crudRepository, entityModelFactory.create(Car.class)));
        add(new EditPanel<Car>("panel", crudRepository, entityModelFactory.create(Car.class), Model.of(new Car())));
    }
    
}
