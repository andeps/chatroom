package mon.sof.common.orm.search;


/**
 * 字段匹配
 *
 * @Author zhangxiaomei
 * @Date 2020-04-30 15:22:16
 * @Param
 * @Return
 */
public class RelatedField {

    /**
     * 匹配字段1
     */
    private String fieldName;

    /**
     * 匹配字段2
     */
    private String FieldName2;

    public RelatedField(String fieldName, String fieldName2) {
        this.fieldName = fieldName;
        FieldName2 = fieldName2;
    }

    public RelatedField() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName2() {
        return FieldName2;
    }

    public void setFieldName2(String fieldName2) {
        FieldName2 = fieldName2;
    }
}
