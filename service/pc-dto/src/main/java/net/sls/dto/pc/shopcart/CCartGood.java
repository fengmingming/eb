package net.sls.dto.pc.shopcart;

public class CCartGood extends CartGood{

	private static final long serialVersionUID = 1L;

	private boolean _isSelect = true;
	
	private int _number = 1;
	
	public CCartGood(){
		super();
	}
	
	public CCartGood(long id){
		super(id);
	}
	
	public CCartGood(long id,int number){
		super(id, number);
	}
	
	public int get_number() {
		return _number;
	}

	public CCartGood set_number(int _number) {
		this._number = _number;
		return this;
	}

	public boolean is_isSelect() {
		return _isSelect;
	}

	public CCartGood set_isSelect(boolean _isSelect) {
		this._isSelect = _isSelect;
		return this;
	}
}
