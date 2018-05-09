package edu.mccc.cos210.ds.fp.javaforth.machineModel;
/**
 * Interface for objects which listen for changes in the Forth Dictionary.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public interface IDictionaryUpdatedEventListener {
	/**
	 * Event update method. 
	 * @param entries - the map of names to ForthWords that is the current state of the dictionary.
	 */
	void onDictionaryUpdated(edu.mccc.cos210.ds.Map<String, ForthWordBase> entries);
}
