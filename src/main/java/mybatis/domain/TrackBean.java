package mybatis.domain;

import java.util.Date;

import packet.GpsRequest;

public class TrackBean {
	
	private Date	dateTime;
	private float 	longitude;
	private float 	latitude;
	private int		gpsSpeed;
	private int		recorerdSpeed;
	private int		angle;
	private int		alarmFlag;
	private int		height;
	private int		status;
	private int		oil;
	private	int		voltage;
	private	int		miles;
	
	public TrackBean(float longitude, float latitude){
		this.longitude = longitude;
		this.latitude = latitude;
		this.dateTime = new Date();
	}
	
	public TrackBean(GpsRequest request){
		this.dateTime = request.getDateTime();
		this.longitude = request.getLongitude();
		this.latitude = request.getLatitude();
		this.gpsSpeed = request.getGpsSpeed();
		this.recorerdSpeed = request.getRecorerdSpeed();
		this.angle = request.getAngle();
		this.alarmFlag = request.getAlarmFlag();
		this.height = request.getHeight();
		this.status = request.getStatus();
		this.oil = request.getOil();
		this.voltage = request.getVoltage();
		this.miles = request.getMiles();
	}
	
	public TrackBean(Date dateTime, float longitude, float latitude,
			int gpsSpeed, int recorerdSpeed, int angle, int alarmFlag,
			int height, int status, int oil, int voltage, int mile) {
		this.dateTime = dateTime;
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
		this.miles = mile;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
	public void setMile(int miles) {
		this.miles = miles;
	}
	@Override
	public String toString() {
		return "TrackBean [dateTime=" + dateTime + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", gpsSpeed=" + gpsSpeed
				+ ", recorerdSpeed=" + recorerdSpeed + ", angle=" + angle
				+ ", alarmFlag=" + alarmFlag + ", height=" + height
				+ ", status=" + status + ", oil=" + oil + ", voltage="
				+ voltage + ", mile=" + miles + "]";
	}
	
}
