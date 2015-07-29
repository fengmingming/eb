package net.sls.component.coupon;

import framework.web.Pager;

import java.util.List;

/**
 * Created by sls006 on 2015/5/22.
 */
public interface ICommonEntityComponent<K> {

    // Insert object and return the effort rows
    int insertEntity(List<K> lst);

    // Delete object and return the effort rows.
    int deleteEntity(List<K> lst);

    // Get the count of Class
    long queryCount(K k);

    // Delete the object by ids
    int deleteByIds(K k,long[] ids);

    // Query object list by ids
    List<K> queryByIds(K k,long[] ids);

    // Query object list by page
    Pager<List<K>> selectPage(K k,int pgIdx,int pgSize);
}
