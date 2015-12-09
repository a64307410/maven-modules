package com.lehman.commons.vo;

public class ProgressEntity {
	private long pBytesRead = 0L;
	private long pContentLength = 0L;
	private long loadSpeed = 0L;
	private long remainingTime = 0L;
	private int pItems;
	public long getpBytesRead() {
		return pBytesRead;
	}
	public void setpBytesRead(long pBytesRead) {
		this.pBytesRead = pBytesRead;
	}
	public long getpContentLength() {
		return pContentLength;
	}
	public void setpContentLength(long pContentLength) {
		this.pContentLength = pContentLength;
	}
	public int getpItems() {
		return pItems;
	}
	public void setpItems(int pItems) {
		this.pItems = pItems;
	}
	
	public long getLoadSpeed() {
		return loadSpeed;
	}
	
	public void setLoadSpeed(long loadSpeed) {
		//String rtnStr= "0KB";
		//loadSpeed = loadSpeed/1024;
		//rtnStr = loadSpeed + "KB";
		//if(loadSpeed/1024 > 1){
		//	rtnStr = loadSpeed + "MB";
		//}
		this.loadSpeed = loadSpeed;
	}
	
	
	
	public long getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(long loadSpeed) {
		/*
		if(pContentLength != 0 && loadSpeed != 0){
			long remainingSize = pContentLength - pBytesRead;
			
			remainingSize = remainingSize / loadSpeed;
			long hour = 0, minute = 0, second = 0;
			hour=remainingSize/60/60;
		    minute=remainingSize/60%60;
		    second=remainingSize%60;
			this.remainingTime = loadSpeed;//(hour < 10? "0"+hour:hour)+":"+(minute<10? "0"+minute:minute)+":"+(second<10? "0"+second:second);
		}*/
		this.remainingTime = loadSpeed;
	}
	@Override
	public String toString() {
		return "ProgressEntity [pBytesRead=" + pBytesRead + ", pContentLength="
				+ pContentLength + ", loadSpeed=" + loadSpeed
				+ ", remainingTime=" + remainingTime + ", pItems=" + pItems
				+ "]";
	}
	
	
	

}
