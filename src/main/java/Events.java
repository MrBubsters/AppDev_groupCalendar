import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;

public class Events {

	public Calendar service;
	public String summary;
	public String loc;
	public String desc;
	public DateTime startTime;
	public DateTime endTime;
	public String[] recur;
	public String timezone;
	
	Events() {
		
	}

	Events(Calendar service, String summary, String loc, String desc, 
    		DateTime startTime, DateTime endTime, String[] recur, String timezone) {
		this.service = service;
		this.summary = summary;
		this.loc = loc;
		this.desc = desc;
		this.startTime = startTime;
		this.endTime = endTime;
		this.recur = recur;
		this.timezone = timezone;
	}
	
	public Calendar getService() {
		return service;
	}
	
	public String getSum() {
		return summary;
	}
	
	public String getLoc() {
		return loc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public DateTime getStart() {
		return startTime;
	}
	
	public DateTime getEnd() {
		return endTime;
	}
	
	public String[] getRecur() {
		return recur;
	}
	
	public String getTimeZone() {
		return timezone;
	}
}
