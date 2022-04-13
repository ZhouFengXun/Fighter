package com.fanlan.fightertest.funcation;

import com.fanlan.fightertest.funcation.inferfaces.BranchHandle;
import com.fanlan.fightertest.funcation.inferfaces.PresentOrElseHandler;


/**
 * consumer  消费性函数 有参无返回
 */
@FunctionalInterface
interface ThrowExceptionFunction {

    /**
     * 抛出异常信息
     *
     * @param message 异常信息
     * @return void
     **/
    void throwMessage(String message);
}

/**
 * funcation  有参有返回
 * runnable   无参无返回
 * Supplier   供给性函数 无参有返回
 * consumer  消费性函数 有参无返回
 */
public class Test {

    public static void main(String[] args) {

        isTure(true).throwMessage("对");


        isTureOrFalse(true).trueOrFalseHandle(()->{
            System.out.println("true");
        },()->{
            System.out.println("false");
        });

        isBlankOrNoBlank("")
                .presentOrElseHandle(
                System.out::println,()->{
                            System.out.println("空字符串");
        });
    }

    public static ThrowExceptionFunction isTure(boolean b){

        return (errorMessage) -> {
            if (b){
                System.out.println(errorMessage);
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param b
     * @return com.example.demo.func.BranchHandle
     **/
    public static BranchHandle isTureOrFalse(boolean b){

        return (trueHandle, falseHandle) -> {
            if (b){
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param b
     * @return com.example.demo.func.BranchHandle
     **/
    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str){

        return (consumer, runnable) -> {
            if (str == null || str.length() == 0){
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }
}
