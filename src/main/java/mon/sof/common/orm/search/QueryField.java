package mon.sof.common.orm.search;

/**
 * 结果集信息
 *
 * @Author zhangxiaomei
 * @Date 2020-04-30 15:22:56
 * @Param
 * @Return
 */
public class QueryField {

    /**
     * 查询内容
     */
    private String content;

    /**
     * 查询表名
     */
    private String table;

    /**
     * 表名别名
     */
    private String alias;

    public QueryField(String content, String table, String alias) {
        this.content = content;
        this.table = table;
        this.alias = alias;
    }

    public QueryField() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
