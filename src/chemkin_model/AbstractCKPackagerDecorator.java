package chemkin_model;

import parsers.ConfigurationInput;
import datamodel.ModelValue;

/**
 * Abstract decorator for {@link AbstractCKPackager} in case other things are done
 * with the collection of chemkin simulations.s
 * @author Nick
 *
 */
public abstract class AbstractCKPackagerDecorator extends AbstractCKPackager {

	AbstractCKPackager packager;
	
	public ConfigurationInput getConfig() {
		return packager.getConfig();
	}
	
	public ModelValue[] getModelValues() {
		return packager.getModelValues();
	}
}