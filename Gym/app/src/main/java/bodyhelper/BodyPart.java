package bodyhelper;

import java.util.UUID;

public class BodyPart {
	
	private UUID mId;
	private int bp_drawable;
	private String viewPath;
	
	public BodyPart(int draw,String view) {
		// TODO Auto-generated constructor stub
		bp_drawable=draw;
		viewPath=view;
		mId = UUID.randomUUID();
	}
	
	public UUID getId() {
		return mId;
	}
	
	public int getBp_drawable() {
		return bp_drawable;
	}
	public void setBp_drawable(int bp_drawable) {
		this.bp_drawable = bp_drawable;
	}
	public String getViewPath() {
		return viewPath;
	}
	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}
	
	
}
