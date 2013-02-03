/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nl.desertspring.wicketcrud.EntityModel.SimpleSingularAttribute;
import nl.desertspring.wicketcrud.sample.Car;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author sihaya
 */
public class ListPanel<T extends Serializable> extends Panel
{

    private class EntityDataProvider extends SortableDataProvider<T>
    {

        private EntityModel entityModel;
        private CrudRepository crudRepository;

        public EntityDataProvider(EntityModel entityModel, CrudRepository crudRepository)
        {            
            this.entityModel = entityModel;
            this.crudRepository = crudRepository;
        }

        @Override
        public Iterator<? extends T> iterator(int first, int count)
        {
            return crudRepository.findAll(entityModel).iterator();
        }

        @Override
        public int size()
        {
            return crudRepository.findAll(entityModel).size();
        }

        @Override
        public IModel<T> model(T object)
        {
            return Model.of(object);
        }
    }

    private class CrudColumn extends AbstractColumn<T>
    {

        private SimpleSingularAttribute attr;

        private CrudColumn(SimpleSingularAttribute attr)
        {
            super(Model.of(attr.getName()));
            
            this.attr = attr;
        }

        @Override
        public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel)
        {
            cellItem.add(new Label(componentId, new SingularAttributeValueModel<T>(rowModel, attr)));
        }
    }

    public ListPanel(String id, CrudRepository crudRepository, EntityModel<T> entityModel)
    {
        super(id);

        List<IColumn<T>> columns = new ArrayList<IColumn<T>>();

        for (EntityModel.SimpleSingularAttribute attr : entityModel.getSingularAttributes()) {
            columns.add(new CrudColumn(attr));
        }

        add(new DefaultDataTable("dataTable", columns, new EntityDataProvider(entityModel, crudRepository), 99999));
    }
}
