package net.sls.pc.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import util.model.SearchSortEnum;

public class Searcher {
	
	public final String url = "http://search.365020.com:9080/solr/db";
	
	public SearchResultDto query(String key,String areaCode,SearchSortEnum sort,Boolean asc,int page,int number){
		SearchResultDto dto = new SearchResultDto();
		SolrServer server = new HttpSolrServer(url);
		SolrQuery query = new SolrQuery();
		query.setQuery(key);
		if(sort != null){
			query.setSort(sort.getField(),(asc == null?ORDER.asc:asc?ORDER.asc:ORDER.desc));
		}
		query.setStart((page-1)*number);
		query.setRows(number);
		if(areaCode != null){
			query.setFilterQueries(dealAreaCode(areaCode));
		}
		try {
			QueryResponse res = server.query(query, METHOD.POST);
			dto.getResponse().setDocs(dealSDL(res.getResults()));
			dto.setTotal(res.getResults().getNumFound());
		} catch (Exception e) {e.printStackTrace();}
		return dto;
	}
	
	private List<Map<String,Object>> dealSDL(SolrDocumentList sdl){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Iterator<SolrDocument> it = sdl.iterator();
		SolrDocument sd;
		Map<String,Object> map;
		while(it.hasNext()){
			sd = it.next();
			map = new HashMap<String,Object>();
			for(String key:sd.keySet()){
				map.put(key, sd.getFieldValue(key));
			}
			list.add(map);
		}
		return list;
	} 
	
	public String dealAreaCode(String areaCode){
		List<String> list = new ArrayList<String>();
		switch(areaCode.length()){
		case 15:list.add(areaCode);
		case 12:list.add(areaCode.substring(0, 12));
		case 9:list.add(areaCode.substring(0, 9));
		case 6:list.add(areaCode.substring(0, 6));
		case 3:list.add(areaCode.substring(0, 3));
		case 1:list.add(areaCode.substring(0,1));
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0,j=list.size();i<j;i++){
			sb.append("areaCode:");
			sb.append(list.get(i));
			if(i != j-1){
				sb.append(" OR ");
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		Searcher s = new Searcher();
		System.out.println(s.query("猪肉", "111100", SearchSortEnum.PRICE, null, 1, 5).getResponse().getDocs());
	}
}
