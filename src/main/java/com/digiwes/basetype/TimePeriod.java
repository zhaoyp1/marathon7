package com.digiwes.basetype;

import com.digiwes.common.utils.ParameterUtil;

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
        assert !ParameterUtil.checkParameterIsNull(startDateTime):"starDateTime must not be  null.";
        assert !ParameterUtil.checkParameterIsNull(endDateTime):"endDateTime must not be  null.";
        assert startDateTime.compareTo(endDateTime)!=1:"endDateTime must be greater than starDateTime.";
        this.startDateTime =startDateTime;
        this.endDateTime = endDateTime;
    }
    /**
     * @param time
     * @return
     */
    public boolean isInTimePeriod(Date time){
        ParameterUtil.checkParameterIsNulForException(time, "time");
        if (null != this.startDateTime && null != this.endDateTime) {
            if(1 == time.compareTo(this.startDateTime) && -1 == time.compareTo(this.endDateTime)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public  boolean isOverlap(TimePeriod validFor){
        ParameterUtil.checkParameterIsNulForException(validFor, "validFor");
        if( null == startDateTime && null== endDateTime){
            return true;
        }
        if( null == validFor){
            return true;
        }
        if (0 >= validFor.getStartDateTime().compareTo(endDateTime) && 0 <= validFor.endDateTime.compareTo(startDateTime)) return true;
        else return false;
    }
    public boolean isInTimePeriod(TimePeriod validFor){
        ParameterUtil.checkParameterIsNulForException(validFor, "validFor");
        if (null != this.startDateTime && null != this.endDateTime) {
            if((-1 != this.startDateTime.compareTo(validFor.getStartDateTime()) )&& (1 != this.endDateTime.compareTo(validFor.getEndDateTime()))){
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
        vaildFor.put("startDateTime", null == this.startDateTime ? "" : format.format(this.startDateTime));
        vaildFor.put("endDateTime", null == this.endDateTime ? "" : format.format(this.endDateTime));
        return  vaildFor.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o  || getClass() != o.getClass()) return false;

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