package net.sls.pc.search;

import java.util.List;
import java.util.Map;

public class SearchResultDto {

	private Response response = new Response();
	private long total;
	
	@SuppressWarnings("rawtypes")
	public class Response{
		
		private List<? extends Map> docs;
		public List<? extends Map> getDocs() {
			return docs;
		}
		public void setDocs(List<? extends Map> docs) {
			this.docs = docs;
		}
		
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
