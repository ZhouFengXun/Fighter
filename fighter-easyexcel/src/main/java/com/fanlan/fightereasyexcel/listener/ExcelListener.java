package com.fanlan.fightereasyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelListener  extends AnalysisEventListener {
    private static Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();
    public void invoke(Object o, AnalysisContext analysisContext) {
        logger.info("导入数据{}", JSON.toJSONString(o));
        datas.add(o);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        // doSomething(o);//根据自己业务做处理
    }

    private void doSomething(Object object) {
        //1、入库调用接口，可在这里写，也可在业务层写
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//         datas.clear();//解析结束销毁不用的资源
    }
}

