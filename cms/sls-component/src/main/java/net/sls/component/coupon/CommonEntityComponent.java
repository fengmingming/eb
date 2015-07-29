package net.sls.component.coupon;

import framework.web.Pager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sls006 on 2015/5/22.
 */
public class CommonEntityComponent<K,M> implements ICommonEntityComponent<K> {

    //@Autowired
    M m ;
    @Override
    public int insertEntity(List<K> lst) {
        return 0;
    }

    @Override
    public int deleteEntity(List<K> lst) {
        return 0;
    }

    @Override
    public long queryCount(K k) {
        return 0;
    }

    @Override
    public int deleteByIds(K k, long[] ids) {
        return 0;
    }

    @Override
    public List<K> queryByIds(K k, long[] ids) {
        return null;
    }

    @Override
    public Pager<List<K>> selectPage(K k, int pgIdx, int pgSize) {
        return null;
    }
}
