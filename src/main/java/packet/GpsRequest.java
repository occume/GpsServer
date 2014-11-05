package packet;

public class GpsRequest {
	
	private int 	mark;
	private int 	id;
	private int 	len;
	private String 	simId;
	private int		liushui;
	
	private int		alert;
	private int		gpsStatu;
	private int		latitudeX;
	private int		latitudeY;
	private int		hight;
	private int		speed;
	private int		dir;
	private String  time;
	
	public GpsRequest(int id){
		this.id = id;
	}
	
	public GpsRequest(int mark, int id, int len, String simId, int liushui,
			int alert, int gpsStatu, int latitudeX, int latitudeY, int hight,
			int speed, int dir, String time) {
		this.mark = mark;
		this.id = id;
		this.len = len;
		this.simId = simId;
		this.liushui = liushui;
		this.alert = alert;
		this.gpsStatu = gpsStatu;
		this.latitudeX = latitudeX;
		this.latitudeY = latitudeY;
		this.hight = hight;
		this.speed = speed;
		this.dir = dir;
		this.time = time;
	}
	
	public GpsRequest(){}
	
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getSimId() {
		return simId;
	}
	public void setSimId(String simId) {
		this.simId = simId;
	}
	public int getLiushui() {
		return liushui;
	}
	public void setLiushui(int liushui) {
		this.liushui = liushui;
	}
	public int getAlert() {
		return alert;
	}
	public void setAlert(int alert) {
		this.alert = alert;
	}
	public int getGpsStatu() {
		return gpsStatu;
	}
	public void setGpsStatu(int gpsStatu) {
		this.gpsStatu = gpsStatu;
	}
	public int getLatitudeX() {
		return latitudeX;
	}
	public void setLatitudeX(int latitudeX) {
		this.latitudeX = latitudeX;
	}
	public int getLatitudeY() {
		return latitudeY;
	}
	public void setLatitudeY(int latitudeY) {
		this.latitudeY = latitudeY;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "GpsRequest [mark=" + mark + ", id=" + id + ", len=" + len
				+ ", simId=" + simId + ", liushui=" + liushui + ", alert="
				+ alert + ", gpsStatu=" + gpsStatu + ", latitudeX=" + latitudeX
				+ ", latitudeY=" + latitudeY + ", hight=" + hight + ", speed="
				+ speed + ", dir=" + dir + ", time=" + time + "]";
	}
	
	
}
