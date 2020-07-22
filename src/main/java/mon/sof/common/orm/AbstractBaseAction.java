package mon.sof.common.orm;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mon.sof.common.orm.search.QueryField;

import java.util.List;

public abstract class AbstractBaseAction<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseAction<T> {


    public abstract IBaseAction<T> getMapper();

    /**
     * 通过多表查找
     *
     * @Author zhangxiaomei
     * @Date 2020-04-30 15:50:38
     * @Param
     * @Return
     */
    @Override
    public List<T> queryByMultiTable(QueryField queryField, List queryMultis) {
        return getMapper().queryByMultiTable(queryField, queryMultis,null,null);
    }

    /**
     * 根据条件多表查找
     *
     * @Author zhangxiaomei
     * @Date 2020-04-30 15:50:38
     * @Param
     * @Return
     */
    @Override
    public List<T> queryByMultiTable(QueryField queryField, List queryMultis, List conditions) {
        return getMapper().queryByMultiTable(queryField, queryMultis,conditions,null);
    }


    /**
     * 根据条件通过多表查找排序
     *
     * @Author zhangxiaomei
     * @Date 2020-04-30 15:50:38
     * @Param
     * @Return
     */
    @Override
    public List<T> queryByMultiTable(QueryField queryField, List queryMultis, List conditions, List orders) {
        return getMapper().queryByMultiTable(queryField, queryMultis, conditions, orders);
    }
}
