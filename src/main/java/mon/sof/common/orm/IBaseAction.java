package mon.sof.common.orm;

import mon.sof.common.orm.search.Condition;
import mon.sof.common.orm.search.QueryField;
import mon.sof.common.orm.search.QueryMulti;
import mon.sof.common.orm.search.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBaseAction<T> {


    /**
     * 通过多表查找
     *
     * @Author zhangxiaomei
     * @Date 2020-04-30 15:32:54
     * @Param
     * @Return
     */
    List<T> queryByMultiTable(QueryField queryField,
                              List<QueryMulti> queryMultis);


    /**
     * 根据条件多表查找
     *
     * @Author zhangxiaomei
     * @Date 2020-04-30 15:32:54
     * @Param
     * @Return
     */
    List<T> queryByMultiTable(QueryField queryField,
                              List<QueryMulti> queryMultis,
                              List<Condition> conditions);





    /**
     * 根据条件通过多表查找排序
     *
     * @Author zhangxiaomei
     * @Date 2020-04-30 15:32:54
     * @Param
     * @Return
     */
    List<T> queryByMultiTable(@Param("queryField") QueryField queryField,
                              @Param("queryMultis") List<QueryMulti> queryMultis,
                              @Param("conditions") List<Condition> conditions,
                              @Param("orders") List<Sort> orders);

}
