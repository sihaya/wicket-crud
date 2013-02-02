/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 *
 * @author sihaya
 */
public class WicketCrudApplication extends WebApplication
{
    @Override
    public Class<? extends Page> getHomePage()
    {
        return EditPanelTestPage.class;
    }

    @Override
    protected void init()
    {
        super.init();
        
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));        
    }
}
