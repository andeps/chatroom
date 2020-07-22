package mon.sof.common.orm.search;


/**
 * sql查询，字段比较类型
 *
 * @Author zhangxiaomei
 * @Date 2020-04-30 11:28:43
 * @Param
 * @Return
 */
public enum MatchTypeEnum {
    EQ(" =  "), //
    NE(" <> "), //
    LIKE(" LIKE "), //
    LT(" < "), //
    GT(" > "), //
    LE(" <= "), //
    GE(" >= "), //
    IN(" IN "), //
    NOT_IN(" NOT IN "), //
    NULL(" IS NULL "), //
    NOT_NULL(" IS NOT NULL "),
    LEFT_JOIN(" LEFT JOIN "),
    JOIN(" JOIN "),
    RIGHT_JOIN(" RIGHT JOIN ");

    private String operator;

    private MatchTypeEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

}
