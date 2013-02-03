/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import javax.persistence.EntityManager;
import nl.desertspring.wicketcrud.EntityModel.SimpleSingularAttribute;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class EditPanel<T> extends Panel
{

    Class<T> entityClass;
    EntityManager entityManager;

    public EditPanel(String id, final CrudRepository crudRepository, final EntityModel<T> entityModel, final IModel<T> model)
    {
        super(id);

        Form form = new Form("form");
        add(form);

        form.add(new ListView<SimpleSingularAttribute>("items", entityModel.getSingularAttributes())
        {

            @Override
            protected void populateItem(ListItem<SimpleSingularAttribute> item)
            {
                item.add(new Label("name", new PropertyModel<String>(item.getModel(), "name")));
                item.add(new TextField("field", new SingularAttributeValueModel(model, item.getModelObject())).setType(item.getModelObject().getType()));
            }
        });

        form.add(new Button("save")
        {

            @Override
            public void onSubmit()
            {
                model.setObject(crudRepository.merge(model.getObject()));
            }
        });
    }
}
