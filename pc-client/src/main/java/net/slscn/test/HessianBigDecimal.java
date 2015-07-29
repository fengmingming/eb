package net.slscn.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;


/**
 * Test hessian BigDecimal Serialization
 * @author sls006
 *
 */
public class HessianBigDecimal {
	
	public static void main(String [] args) throws IOException{
		BigDecimal object = new BigDecimal("12474639.945458954");
		Hessian2Output os = new Hessian2Output(null);
		os.setSerializerFactory(new SerializerFactory());
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		os.init(buffer);
		os.writeObject(object);
		os.close();
		byte[] bytes = buffer.toByteArray();

		 Hessian2Input is = new Hessian2Input(new ByteArrayInputStream(bytes));
		 BigDecimal newObject = (BigDecimal) is.readObject();// is.readObject();
		 is.close();
		 System.out.println(object.equals(newObject));
	}

}
