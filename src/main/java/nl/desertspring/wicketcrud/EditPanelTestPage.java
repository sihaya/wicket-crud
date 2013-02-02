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
    
    public EditPanelTestPage() {
        add(new EditPanel<Car>("panel", entityModelFactory.create(Car.class), Model.of(new Car())));
    }
    
}
