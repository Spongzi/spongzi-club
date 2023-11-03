package com.spongzi.club.common.entity;

import com.spongzi.club.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 统一返回结果类
 *
 * @author spong
 * @date 2023/10/06
 */
@Data
public class Result<T> {
    private Integer code;

    private Boolean success;

    private String message;

    private T data;

    /**
     * 成功 不带返回结果
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

    /**
     * 成功 带返回结果
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }

    /**
     * 失败 不带返回结果
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

    /**
     * 失败 带返回结果
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        result.setData(data);
        return result;
    }

    /**
     * 失败 带错误信息
     *
     * @param message 错误信息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(message);
        return result;
    }
}
