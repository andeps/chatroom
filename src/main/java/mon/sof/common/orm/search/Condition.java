package mon.sof.common.orm.search;

import java.util.Collection;

public class Condition {
	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 字段类型
	 */
	private FieldTypeEnum fieldType;

	/**
	 * 匹配类别
	 */
	private MatchTypeEnum matchType;
	
	/**
	 * 是否为脚本
	 * 如果该项为True 则 value 中的值 将作为SQL脚本 直接 通过AND 拼接到 查询条件后
	 */
	private boolean script;

	/**
	 * 要匹配的值
	 */
	private Object value;

	public Condition() {
	}

	public Condition(String fieldName, FieldTypeEnum fieldType, MatchTypeEnum matchType, Object value) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.matchType = matchType;
		this.script = false;
		this.value = value;
	}
	
	public Condition(String sql){
	    this.script = true;
	    this.value = sql;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}

	public MatchTypeEnum getMatchType() {
		return matchType;
	}

	public void setMatchType(MatchTypeEnum matchType) {
		this.matchType = matchType;
	}
	
	public boolean getScript() {
	    return script;
	}

	public void setScript(boolean script) {
	    this.script = script;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		if (value != null && value instanceof Collection) {
			throw new IllegalArgumentException("Unsupport " + Collection.class.getName() + " type!");
		}
		this.value = value;
	}
}
