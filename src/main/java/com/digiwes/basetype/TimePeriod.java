package com.digiwes.basetype;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A base / value business entity used to represent a period of time, between two time points
 */
public class TimePeriod {

    /**
     * An instant of time, starting at the TimePeriod
     * 
     * Notes:
     * If null, then represents to the beginning of time
     */
    public Date startDateTime;
    /**
     * An instant of time, ending at the TimePeriod:
     * 
     * Notes:
     * If null, then represents to the end of time
     */
    public Date endDateTime;

    public Date getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public TimePeriod(Date startDateTime, Date endDateTime) {
        if( null == startDateTime || null == endDateTime){
            throw  new IllegalArgumentException("starDateTime or endDateTime can not be  null ");
        }
        if ( null != startDateTime ) {
            this.startDateTime =startDateTime;
        }
        if (null != endDateTime ) {
            this.endDateTime = endDateTime;
        }
    }
    /**
     * @param time
     * @return
     */
    public boolean isInTimePeriod(Date time){
        if (this.startDateTime != null && this.endDateTime != null) {
            if(time.compareTo(this.startDateTime) == 1 && time.compareTo(this.endDateTime) == -1){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public  boolean isOverlap(TimePeriod validFor){
        if( null == startDateTime && null== endDateTime){
            return true;
        }
        if( null == validFor){
            return true;
        }
        if (validFor.getStartDateTime().compareTo(endDateTime)<=0 && validFor.endDateTime.compareTo(startDateTime)>=0) return true;
        else return false;
    }
    public boolean isInTimePeriod(TimePeriod validFor){
        if (this.startDateTime != null && this.endDateTime != null) {
            if((validFor.getStartDateTime().compareTo(this.startDateTime) == 1 ||validFor.getStartDateTime().compareTo(this.startDateTime) == 0 )&& (validFor.getEndDateTime().compareTo(this.endDateTime) == -1 ||validFor.getEndDateTime().compareTo(this.endDateTime) == 0 )){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Map<String,String> vaildFor=new HashMap<String,String>();
        vaildFor.put("startDateTime", this.startDateTime == null ? "" : format.format(this.startDateTime));
        vaildFor.put("endDateTime", this.endDateTime == null ? "" : format.format(this.endDateTime));
        return  vaildFor.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriod that = (TimePeriod) o;

        if (!startDateTime.equals(that.startDateTime)) return false;
        return endDateTime.equals(that.endDateTime);

    }

    @Override
    public int hashCode() {
        int result = startDateTime.hashCode();
        result = 31 * result + endDateTime.hashCode();
        return result;
    }

}