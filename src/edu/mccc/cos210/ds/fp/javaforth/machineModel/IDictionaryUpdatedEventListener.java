package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public interface IDictionaryUpdatedEventListener {
	void onDictionaryUpdated(edu.mccc.cos210.ds.Map<String, ForthWordBase> entries);
}
