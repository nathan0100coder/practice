package practice.topics.exception;

import lombok.Getter;

/**
 * @author: zp
 * @Date: 2019-10-10 14:42
 * @Description:
 */
@Getter
public class BizException extends RuntimeException{
//    private Integer code;

    /**
     * 使用已有的错误类型
     *
     * @param type 枚举类中的错误类型
     */
    public BizException(ErrorType type){
        super(type.getMsg());
//        this.code = type.getCode();
    }

    /**
     * 自定义错误类型
     *
     * @param code 自定义的错误码
     * @param msg 自定义的错误提示
     */
    public BizException(Integer code, String msg){
        super(msg);
//        this.code = code;
    }
}