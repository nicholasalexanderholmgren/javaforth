package edu.mccc.cos210.ds.fp.javaforth.util;
/**
 * Interface for objects which listen for changes to a forth stack.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public interface IStackUpdatedEventListener {
	/**
	 * Event update method. 
	 * @param stack - the current contents of the stack
	 */
	void onStackUpdated(Iterable<Object> stack);
}
