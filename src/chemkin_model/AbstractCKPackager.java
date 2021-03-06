package chemkin_model;

import util.Loggable;
import datamodel.ModelValue;

/**
 * The abstract type that collects a number of chemkin simulations.
 * 
 * Three attributes:
 * <LI>Configuration object
 * <LI>An array of chemkin simulations
 * <LI>An array of model values, structured in the same way as the array of simulations.
 * 
 * This type will be used in a decorator pattern, therefore, getters to the attributes are provided.
 * @author Nick
 *
 */
public abstract class AbstractCKPackager extends Loggable{
	
	AbstractCKEmulation [] simulations;
	
	public ModelValue[] modelValues;
	
	public abstract AbstractCKEmulation []  runAllSimulations();

	public ModelValue[] getModelValues() {
		return modelValues;
	}
}
