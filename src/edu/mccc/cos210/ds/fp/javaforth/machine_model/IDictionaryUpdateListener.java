package edu.mccc.cos210.ds.fp.javaforth.machine_model;
import java.util.Map;
public interface IDictionaryUpdateListener {
    public void onDictionaryUpdated(Map<String, String> entries);
}