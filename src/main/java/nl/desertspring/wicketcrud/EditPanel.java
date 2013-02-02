/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;
import nl.desertspring.wicketcrud.EntityModel.SimpleSingularAttribute;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class EditPanel<T> extends Panel
{

    Class<T> entityClass;
    EntityManager entityManager;

    private static class SingularAttributeValueModel<T> implements IModel<Object>
    {
        private SimpleSingularAttribute attribute;
        private IModel<T> entity;

        public SingularAttributeValueModel(IModel<T> entity, EntityModel.SimpleSingularAttribute<T> attribute)
        {
            this.entity = entity;
            this.attribute = attribute;            
        }

        @Override
        public Object getObject()
        {
            return attribute.getValue(entity.getObject());
        }

        @Override
        public void setObject(Object object)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void detach()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public EditPanel(String id, final EntityModel<T> entityModel, final IModel<T> model)
    {
        super(id);

        add(new ListView<SimpleSingularAttribute>("items", entityModel.getSingularAttributes())
        {

            @Override
            protected void populateItem(ListItem<SimpleSingularAttribute> item)
            {                
                item.add(new Label("name", new PropertyModel<String>(item.getModel(), "name")));
                item.add(new TextField("field", new SingularAttributeValueModel(model, item.getModelObject())));
            }
        });
    }
}
