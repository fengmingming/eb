package net.sls.dto.pc.act;

import java.io.Serializable;

public class AbstractHandler implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long actId;
	
	private int actType;
	
	private String actName;

	public long getActId() {
		return actId;
	}

	public void setActId(long actId) {
		this.actId = actId;
	}

	public int getActType() {
		return actType;
	}

	public void setActType(int actType) {
		this.actType = actType;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)actId;
		result = prime * result + actType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractHandler other = (AbstractHandler) obj;
		if (actId != other.actId)
			return false;
		if (actType != other.actType)
			return false;
		return true;
	}
	
}
