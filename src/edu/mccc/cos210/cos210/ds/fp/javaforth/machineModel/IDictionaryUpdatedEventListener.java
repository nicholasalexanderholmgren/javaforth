package edu.mccc.cos210.ds.fp.javaforth.machineModel;import java.util.Map;

public interface IDictionaryUpdatedEventListener {
	void onDictionaryUpdated(edu.mccc.cos210.ds.Map<String, String> entries);
}
