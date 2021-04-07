package carDate.inv;

import java.util.ArrayList;

// temporay only to be del
public class invTemp {
	
	private int tempId;
	private ArrayList desc;
	public invTemp() {
		super();
	}
	@Override
	public String toString() {
		return "invTemp [tempId=" + tempId + ", desc=" + desc + "]";
	}
	public int getTempId() {
		return tempId;
	}
	public void setTempId(int tempId) {
		this.tempId = tempId;
	}
	public ArrayList getDesc() {
		return desc;
	}
	public void setDesc(ArrayList desc) {
		this.desc = desc;
	}

}
