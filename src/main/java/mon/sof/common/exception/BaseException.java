
package mon.sof.common.exception;


/**
 * 异常总处理基类
 * @Author  zhangxiaomei
 * @Date    2020-02-24 13:14:37
 * @Param
 * @Return
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -2786310264145061572L;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
