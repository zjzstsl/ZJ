package org.tis.tools.asf.module.er.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 对应ER图中的
 * @author zhaoch
 */
@XmlRootElement(name = "word")
public class ERWord {

    /**
     * 标识ID
     */
    @XmlElement(name="id")
    private String id ;

    /**
     * 长度
     */
    @XmlElement(name="length")
    private Integer length ;

    /**
     * 精度
     */
    @XmlElement(name="decimal")
    private String decimal ;

    /**
     * 是否为数组
     */
    @XmlElement(name="array")
    private Boolean array ;

    /**
     * 数组长度
     */
    @XmlElement(name="array_dimension")
    private String arrayDimension ;

    /**
     * 是否为无符号
     */
    @XmlElement(name="unsigned")
    private Boolean unsigned ;

    /**
     * 是否填充0
     */
    @XmlElement(name="zerofill")
    private Boolean zerofill ;

    /**
     * 是否为二进制
     */
    @XmlElement(name="binary")
    private String binary ;

    /**
     * 参数
     */
    @XmlElement(name="args")
    private String args ;

    /**
     * 字符语义
     */
    @XmlElement(name="char_semantics")
    private Boolean charSemantics ;

    /**
     * 描述
     */
    @XmlElement(name="description")
    private String description ;

    /**
     * 逻辑名
     */
    @XmlElement(name="logical_name")
    private String logicalName ;

    /**
     * 物理名
     */
    @XmlElement(name="physical_name")
    private String physicalName ;

    /**
     * 类型
     */
    @XmlElement(name="type")
    private String type ;


    @XmlTransient
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }

    @XmlTransient
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public String getLogicalName() {
        return logicalName;
    }
    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    @XmlTransient
    public String getPhysicalName() {
        return physicalName;
    }
    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    @XmlTransient
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public String getDecimal() {
        return decimal;
    }
    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    @XmlTransient
    public Boolean getArray() {
        return array;
    }
    public void setArray(Boolean array) {
        this.array = array;
    }

    @XmlTransient
    public String getArrayDimension() {
        return arrayDimension;
    }
    public void setArrayDimension(String arrayDimension) {
        this.arrayDimension = arrayDimension;
    }

    @XmlTransient
    public Boolean getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(Boolean unsigned) {
        this.unsigned = unsigned;
    }
    @XmlTransient
    public Boolean getZerofill() {
        return zerofill;
    }
    public void setZerofill(Boolean zerofill) {
        this.zerofill = zerofill;
    }

    @XmlTransient
    public String getBinary() {
        return binary;
    }
    public void setBinary(String binary) {
        this.binary = binary;
    }

    @XmlTransient
    public String getArgs() {
        return args;
    }
    public void setArgs(String args) {
        this.args = args;
    }

    @XmlTransient
    public Boolean getCharSemantics() {
        return charSemantics;
    }
    public void setCharSemantics(Boolean charSemantics) {
        this.charSemantics = charSemantics;
    }
}
