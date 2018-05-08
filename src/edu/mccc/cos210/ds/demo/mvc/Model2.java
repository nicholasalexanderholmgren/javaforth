package edu.mccc.cos210.ds.demo.mvc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.Map;

public class Model2 implements IModel<String> {
	private IMap<String, Color> theMap = new Map<>();
	public Model2() {
		initMap();
	}
	@Override
	public ISet<String> getNames() {
		return theMap.keySet();
	}
	@Override
	public BufferedImage getPhoto(String name) {
		BufferedImage bi = new BufferedImage(184, 184, BufferedImage.TYPE_INT_BGR);
		Graphics2D g2d = bi.createGraphics();
		g2d.setPaint(theMap.get(name));
		g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g2d.dispose();
		return bi;
	}
	@Override
	public Comparator<? super String> getComparator() {
		return null;
	}
	private void initMap() {
		theMap.put("Black", new Color(0, 0, 0));
		theMap.put("Gray", new Color(128, 128, 128));
		theMap.put("Cyan", new Color(0, 255, 255));
		theMap.put("Purple", new Color(255, 0, 255));
		theMap.put("Yellow", new Color(255, 255, 0));
		theMap.put("Blue", new Color(0, 0, 255));
		theMap.put("Green", new Color(0, 255, 0));
		theMap.put("Red", new Color(255, 0, 0));
	}
}
