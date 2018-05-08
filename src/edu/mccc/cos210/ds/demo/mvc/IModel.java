package edu.mccc.cos210.ds.demo.mvc;

import java.awt.image.BufferedImage;
import java.util.Comparator;
import edu.mccc.cos210.ds.ISet;

public interface IModel<T> {
	public ISet<String> getNames();
	public BufferedImage getPhoto(String name);
	public Comparator<? super T> getComparator();
}
