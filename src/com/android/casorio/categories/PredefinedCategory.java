package com.android.casorio.categories;


public class PredefinedCategory extends Category {
	
	
	public PredefinedCategory(String name, int id) {
		super();
		this.setName(name);
		this.setId(0);
		this.setPredefinedId(id);
	}
	
	public int predefinedId;

	public int getPredefinedId() {
		return predefinedId;
	}

	public void setPredefinedId(int predefinedId) {
		this.predefinedId = predefinedId;
	}

}
