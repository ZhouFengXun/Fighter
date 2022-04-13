package com.fanlan.fightertest.funcation.inferfaces;

/**
 * 分支处理接口
 * runnable   无参无返回
 * 无入参，无返回值
 **/
@FunctionalInterface
public interface BranchHandle {

    /**
     * 分支操作
     *
     * @param trueHandle 为true时要进行的操作
     * @param falseHandle 为false时要进行的操作
     * @return void
     **/
    void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);

}
