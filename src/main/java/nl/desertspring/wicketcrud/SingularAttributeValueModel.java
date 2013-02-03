/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import nl.desertspring.wicketcrud.EntityModel.SimpleSingularAttribute;
import org.apache.wicket.model.IModel;

/**
 *
 * @author sihaya
 */
public class SingularAttributeValueModel<T> implements IModel<Object>
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
        attribute.setValue(entity.getObject(), object);
    }

    @Override
    public void detach()
    {
    }
}
