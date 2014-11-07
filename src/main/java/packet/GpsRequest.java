package packet;

import java.util.Date;

public class GpsRequest {
	
	private int 	mark;
	private int 	id;
	private int 	len;
	private String 	simId;
	private int		liushui;
	private float	longitude;
	private float	latitude;
	private int		gpsSpeed;
	private int		recorerdSpeed;
	private int		angle;
	private int		alarmFlag;
	private int		height;
	private int		status;
	private int		oil;
	private int		voltage;
	private int		miles;
	private int		dir;
	private Date  	dateTime;
	
	public GpsRequest(int id){
		this.id = id;
	}
	
	public GpsRequest(int id, int len, String simId, int liushui,
			float longitude, float latitude, int gpsSpeed, int recorerdSpeed,
			int angle, int alarmFlag, int height, int status, int oil,
			int voltage, int miles, Date dateTime) {
		
		this.id = id;
		this.len = len;
		this.simId = simId;
		this.liushui = liushui;
		this.longitude = longitude;
		this.latitude = latitude;
		this.gpsSpeed = gpsSpeed;
		this.recorerdSpeed = recorerdSpeed;
		this.angle = angle;
		this.alarmFlag = alarmFlag;
		this.height = height;
		this.status = status;
		this.oil = oil;
		this.voltage = voltage;
		this.miles = miles;
		this.dateTime = dateTime;
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
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public int getGpsSpeed() {
		return gpsSpeed;
	}
	public void setGpsSpeed(int gpsSpeed) {
		this.gpsSpeed = gpsSpeed;
	}
	public int getRecorerdSpeed() {
		return recorerdSpeed;
	}
	public void setRecorerdSpeed(int recorerdSpeed) {
		this.recorerdSpeed = recorerdSpeed;
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public int getAlarmFlag() {
		return alarmFlag;
	}
	public void setAlarmFlag(int alarmFlag) {
		this.alarmFlag = alarmFlag;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOil() {
		return oil;
	}
	public void setOil(int oil) {
		this.oil = oil;
	}
	public int getVoltage() {
		return voltage;
	}
	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}
	public int getMiles() {
		return miles;
	}
	public void setMiles(int miles) {
		this.miles = miles;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "GpsRequest [simId=" + simId + ", liushui=" + liushui
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", gpsSpeed=" + gpsSpeed + ", recorerdSpeed=" + recorerdSpeed
				+ ", angle=" + angle + ", alarmFlag=" + alarmFlag + ", height="
				+ height + ", status=" + status + ", oil=" + oil + ", voltage="
				+ voltage + ", miles=" + miles + ", dateTime=" + dateTime + "]";
	}
	
}
