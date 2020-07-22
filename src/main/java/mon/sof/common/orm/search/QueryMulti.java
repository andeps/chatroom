package mon.sof.common.orm.search;


/**
 * 多表查询
 *
 * @Author zhangxiaomei
 * @Date 2020-04-30 11:28:57
 * @Param
 * @Return
 */
public class QueryMulti {

    /**
     * 字段名
     */
    private String column;

    /**
     * 别名
     */
    private String alias;

    /**
     * 匹配类别
     */
    private MatchTypeEnum matchType;


    /**
     * 匹配字段
     */
    private RelatedField relatedField;


    public QueryMulti(String column, String alias, MatchTypeEnum matchType, RelatedField relatedField) {
        this.column = column;
        this.alias = alias;
        this.matchType = matchType;
        this.relatedField = relatedField;
    }

    public QueryMulti() {
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public MatchTypeEnum getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchTypeEnum matchType) {
        this.matchType = matchType;
    }

    public RelatedField getRelatedField() {
        return relatedField;
    }

    public void setRelatedField(RelatedField relatedField) {
        this.relatedField = relatedField;
    }
}
