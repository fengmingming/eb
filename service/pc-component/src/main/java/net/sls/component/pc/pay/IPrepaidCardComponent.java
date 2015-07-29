package net.sls.component.pc.pay;

import net.sls.dto.pc.pay.PrepaidCard;

public interface IPrepaidCardComponent {

	PrepaidCard selectPrepaidCard(String password);

}
