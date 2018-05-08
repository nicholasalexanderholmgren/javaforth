package edu.mccc.cos210.ds.demo.mvc;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.ISortedList;
import edu.mccc.cos210.ds.SortedList;

public class Controller implements ListSelectionListener {
	private IModel<String> model;
	private View view;
	public Controller(IModel<String> model, View view) {
		this.model = model;
		this.view = view;
		ISortedList<String> list;
		if (this.model.getComparator() == null) {
			list = new SortedList<>();
		} else {
			list = new SortedList<>(this.model.getComparator());
		}
		ISet<String> names = model.getNames();
		for (String key : names) {
			list.add(key);
		}
		for (String n : list) {
			view.addName(n);
		}
		view.addListSelectionListener(this);
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		int index = view.getJList().getSelectedIndex();
		if (index != -1) {
			String name = (String) view.getJList().getModel().getElementAt(index);
			view.setPhoto(model.getPhoto(name));
		}
	}
}
