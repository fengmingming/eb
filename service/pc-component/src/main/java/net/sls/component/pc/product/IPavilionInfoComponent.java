package net.sls.component.pc.product;

import net.sls.dto.pc.product.PavilionInfo;

public interface IPavilionInfoComponent {

	PavilionInfo selectPavilionInfoByCode(String code);

}
