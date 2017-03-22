package org.koushik.javabrains.messenger.resources.beans;

import javax.ws.rs.QueryParam;

// If  you dont want to use all the Annotations like QueryParam n all or the list is getting unweildy, so go for this
// @BeanParam, Create a Bean like MesssageFilterBean & create the variables with @QueryParam &
// remove from the resource, the @QueryParam... 
public class MessageFilterBean {

	private @QueryParam("year") int year;
	private @QueryParam("start") int start;
	private @QueryParam("size") int size;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
