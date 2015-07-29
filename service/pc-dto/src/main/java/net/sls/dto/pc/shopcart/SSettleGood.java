package net.sls.dto.pc.shopcart;

public class SSettleGood extends SettleGood {

	private static final long serialVersionUID = 1L;

	private boolean _isSelect = true;
	
	private int _number = 1;
	
	private Long _goodAreaId;
	
	//是否判断区域库存
	private boolean _isJudgeAreaStcok = false;

	public int get_number() {
		return _number;
	}

	public void set_number(int _number) {
		this._number = _number;
	}

	public boolean is_isSelect() {
		return _isSelect;
	}

	public void set_isSelect(boolean _isSelect) {
		this._isSelect = _isSelect;
	}

	public Long get_goodAreaId() {
		return _goodAreaId;
	}

	public void set_goodAreaId(Long _goodAreaId) {
		this._goodAreaId = _goodAreaId;
	}

	public boolean is_isJudgeAreaStcok() {
		return _isJudgeAreaStcok;
	}

	public void set_isJudgeAreaStcok(boolean _isJudgeAreaStcok) {
		this._isJudgeAreaStcok = _isJudgeAreaStcok;
	}

}
