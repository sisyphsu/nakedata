package com.github.sisyphsu.nakedata.context;

import com.github.sisyphsu.nakedata.context.model.ContextType;
import com.github.sisyphsu.nakedata.io.InputReader;
import com.github.sisyphsu.nakedata.io.OutputWriter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * An version of Context
 *
 * @author sulin
 * @since 2019-04-29 13:47:26
 */
@Data
@Deprecated
public class ContextVersion {

    private static final byte NAME_EXPIRED = 1 << 1;
    private static final byte TYPE_EXPIRED = 1 << 2;
    private static final byte NAME_ADDED = 1 << 3;
    private static final byte TYPE_ADDED = 1 << 4;
    private static final byte TMP_NAME = 1 << 5;
    private static final byte TMP_TYPE = 1 << 6;

    /**
     * 版本号ID
     */
    private int version;
    /**
     * 已过期的变量名
     */
    private List<Integer> nameExpired = new ArrayList<>();
    /**
     * 已过期的数据框架
     */
    private List<Integer> structExpired = new ArrayList<>();
    /**
     * 已过期的数据类型
     */
    private List<Integer> typeExpired = new ArrayList<>();
    /**
     * 新增的变量名
     */
    private List<String> nameAdded = new ArrayList<>();
    /**
     * 新增的数据框架
     */
    private List<Object> structAdded = new ArrayList<>();
    /**
     * 新增的数据类型
     */
    private List<ContextType> typeAdded = new ArrayList<>();
    /**
     * 临时使用的变量名
     */
    private List<String> nameTemp = new ArrayList<>();
    /**
     * 临时使用的数据框架
     */
    private List<Object> frameTemp = new ArrayList<>();
    /**
     * 临时使用的数据类型
     */
    private List<ContextType> typeTemp = new ArrayList<>();

    /**
     * 上下文增量版本输出
     *
     * @param writer 底层输出接口
     */
    public void doWrite(OutputWriter writer) {
        // 输出版本号尾数
        writer.writeUint(version % (1 << 15));
        // 输出Flag
        byte nameExpiredFlag = nameExpired.isEmpty() ? 0 : NAME_EXPIRED;
        byte typeExpiredFlag = typeExpired.isEmpty() ? 0 : TYPE_EXPIRED;
        byte nameAddedFlag = nameAdded.isEmpty() ? 0 : NAME_ADDED;
        byte typeAddedFlag = typeAdded.isEmpty() ? 0 : TYPE_ADDED;
        byte tmpNameFlag = nameTemp.isEmpty() ? 0 : TMP_NAME;
        byte tmpTypeFlag = typeTemp.isEmpty() ? 0 : TMP_TYPE;
        byte flag = (byte) (nameExpiredFlag | typeExpiredFlag | nameAddedFlag | typeAddedFlag | tmpNameFlag | tmpTypeFlag);
        writer.writeByte(flag);
        // 输出已废弃变量名
        if (!nameExpired.isEmpty()) {
            writer.writeUint(nameExpired.size());
            nameExpired.forEach(writer::writeUint);
        }
        // 输出已废弃属性名
        if (!typeExpired.isEmpty()) {
            writer.writeUint(typeExpired.size());
            typeExpired.forEach(writer::writeUint);
        }
        // 输出新增的变量名
        if (!nameAdded.isEmpty()) {
            writer.writeUint(nameAdded.size());
            nameAdded.forEach(writer::writeString);
        }
        // 输出新增的类型
        if (!typeAdded.isEmpty()) {
            writer.writeUint(typeAdded.size());
            typeAdded.forEach(type -> type.doWrite(writer));
        }
        // 输出临时变量名
        if (!nameTemp.isEmpty()) {
            writer.writeUint(nameTemp.size());
            nameTemp.forEach(writer::writeString);
        }
        // 输出临时类型
        if (!typeTemp.isEmpty()) {
            writer.writeUint(typeTemp.size());
            typeTemp.forEach(type -> type.doWrite(writer));
        }
    }

    /**
     * 从输入流中读取ContextVersion信息
     *
     * @param reader 输入流接口
     */
    public void doRead(InputReader reader) {
        // 读取版本号
        this.version = (int) reader.readUint();
        // 读取Flag
        byte flag = reader.readByte();
        boolean nameExpiredFlag = (flag & NAME_EXPIRED) != 0;
        boolean typeExpiredFlag = (flag & TYPE_EXPIRED) != 0;
        boolean nameAddedFlag = (flag & NAME_ADDED) != 0;
        boolean typeAddedFlag = (flag & TYPE_ADDED) != 0;
        boolean tmpNameFlag = (flag & TMP_NAME) != 0;
        boolean tmpTypeFlag = (flag & TMP_TYPE) != 0;
        // 读取已废弃变量ID
        if (nameExpiredFlag) {
            int size = (int) reader.readUint();
            for (int i = 0; i < size; i++) {
                this.nameExpired.add((int) reader.readUint());
            }
        }
        // 读取已废弃类型ID
        if (typeExpiredFlag) {
            int size = (int) reader.readUint();
            for (int i = 0; i < size; i++) {
                this.typeExpired.add((int) reader.readUint());
            }
        }
        // 读取新增变量名
        if (nameAddedFlag) {
            int size = (int) reader.readUint();
            for (int i = 0; i < size; i++) {
                this.nameAdded.add(reader.readString());
            }
        }
        // 读取新增类型
        if (typeAddedFlag) {
            int size = (int) reader.readUint();
            for (int i = 0; i < size; i++) {
//                ContextType type = new ContextType();
//                type.doRead(reader);
//                this.typeAdded.add(type);
            }
        }
        // 读取临时变量名
        if (tmpNameFlag) {
            int size = (int) reader.readUint();
            for (int i = 0; i < size; i++) {
                this.nameTemp.add(reader.readString());
            }
        }
        // 读取临时类型
        if (tmpTypeFlag) {
            int size = (int) reader.readUint();
            for (int i = 0; i < size; i++) {
//                ContextType type = new ContextType();
//                type.doRead(reader);
//                this.typeTemp.add(type);
            }
        }
    }

}
