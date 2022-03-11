package com.fanlan.fightereasyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.fanlan.fightereasyexcel.dao.EduSubjectDao;
import com.fanlan.fightereasyexcel.listener.ExcelListener;
import com.fanlan.fightereasyexcel.mbg.mapper.EduSubjectMapper;
import com.fanlan.fightereasyexcel.mbg.model.EduSubject;
import com.fanlan.fightereasyexcel.service.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
    @Resource
    EduSubjectMapper eduSubjectMapper;

    @Resource
    EduSubjectDao eduSubjectDao;
    /**
     * 导出用户信息
     *
     * @param response
     * @throws IOException
     */
    @Override
    public void excelExport(HttpServletResponse response) throws IOException {
        //准备导出的数据
        List<EduSubject> list = eduSubjectMapper.selectByExample(null);
        logger.info("记录导出数据行数：{}", list.size());
        String fileName = "用户名单";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        //xls 一个 Sheet 最多支持 65535 行，如果数据要在一个 Sheet 中可以通过指定 ExcelFormat.Xlsx 来导出
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS, true);
        Sheet sheet = new Sheet(1, 0, EduSubject.class);
        //设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        sheet.setSheetName("用户名单");
        writer.write(list, sheet);
        writer.finish();
        out.flush();
        response.getOutputStream().close();
        out.close();
    }

    @Override
    public void excelExport1(HttpServletResponse response) throws IOException {
        //准备导出的数据
        List<EduSubject> list = eduSubjectMapper.selectByExample(null);
        logger.info("记录导出数据行数：{}", list.size());
        String fileName = "用户名单";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        // 新建一个ExcelWriterBuilder实例
        ExcelWriterBuilder writerBuilder = EasyExcel.write();
        // 输出的文件对象，可以是File、路径（字符串）或者OutputStream实例
        writerBuilder.file(out);
        // 指定sheet，可以是数字序号sheetNo或者字符串sheetName，可以不设置，由下面提到的WriteSheet覆盖
        writerBuilder.sheet("用户名单");
        // 文件的密码
        //writerBuilder.password("");
        // Excel文件格式，包括ExcelTypeEnum.XLSX和ExcelTypeEnum.XLS
        writerBuilder.excelType(ExcelTypeEnum.XLSX);
        // 是否自动关闭输出流
        writerBuilder.autoCloseStream(true);
        // 指定文件的标题行，可以是Class对象（结合@ExcelProperty注解使用），或者List<List<String>>实例
        //writerBuilder.head(Collections.singletonList(Collections.singletonList("head")));
        writerBuilder.head(EduSubject.class);
        // 构建ExcelWriter实例
        ExcelWriter excelWriter = writerBuilder.build();
        //List<List<String>> data = new ArrayList<>();
        // 构建输出的sheet
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName("target");
        excelWriter.write(list, writeSheet);
        // 这一步一定要调用，否则输出的文件有可能不完整
        excelWriter.finish();
        out.flush();
        response.getOutputStream().close();
        out.close();
    }

    /**
     * 导入用户信息
     *
     * @param file
     * @throws IOException
     */
    @Override
    public void excelImport(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        //传入参数
        ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
        //读取信息
        excelReader.read(new Sheet(1, 1, EduSubject.class));
        //获取数据
        List<Object> list = listener.getDatas();
        logger.info("记录数据行数：{}",list.size());
        List<EduSubject> originalList = new ArrayList<EduSubject>();
        EduSubject catagory = new EduSubject();
        //转换数据类型
        for (int i = 0; i < list.size(); i++) {
            catagory = (EduSubject) list.get(i);
            originalList.add(catagory);
        }
        logger.info("记录导入数据行数：{}",originalList.size());
        //对list进行去重并拿到新的list
        List<EduSubject> lists = originalList.stream()
                .filter(s -> StringUtils.isNotBlank(s.getTitle()))
                .collect(Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<EduSubject>(Comparator.comparing(EduSubject::getTitle))), ArrayList::new));
        //执行批量插入
        if (lists.size() > 0){
            logger.info("记录导入数据行数：{}",lists.size());
            logger.info("执行批量入库");
            eduSubjectDao.addLists(lists);
            return;
        }
        logger.info("解析数据为空");
    }

    /**
     * 导入用户信息
     * @param file
     * @throws IOException
     */
    @Override
    public void excelImport1(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        //创建ExcelReaderBuilder
        ExcelReaderBuilder readerBuilder = EasyExcel.read();
        //读取的文件对象，可以是File、路径（字符串）或者InputStream实例
        readerBuilder.file(inputStream);
        //指定sheet，可以是数字序号sheetNo或者字符串sheetName，若不指定则会读取所有的sheet
        readerBuilder.sheet(1,"用户名单");
        // 是否自动关闭输入流
        readerBuilder.autoCloseStream(true);
        // Excel文件格式，包括ExcelTypeEnum.XLSX和ExcelTypeEnum.XLS
        readerBuilder.excelType(ExcelTypeEnum.XLSX);
        // 指定文件的标题行，可以是Class对象（结合@ExcelProperty注解使用），或者List<List<String>>实例
        //readerBuilder.head(Collections.singletonList(Collections.singletonList("head")));
        readerBuilder.head(EduSubject.class);
        // 注册读取事件的监听器，默认的数据类型为Map<Integer,String>，第一列的元素的下标从0开始
        readerBuilder.registerReadListener(new AnalysisEventListener() {

            @Override
            public void invokeHeadMap(Map headMap, AnalysisContext context) {
                // 这里会回调标题行，文件内容的首行会认为是标题行
            }
            //可以通过实例获取该值
            private List<Object> datas = new ArrayList<Object>();
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                logger.info("导入数据{}", JSON.toJSONString(o));
                // 这里会回调每行的数据
                datas.add(o);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
                doSomething(datas);//根据自己业务做处理
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        });
        // 构建读取器
        ExcelReader excelReader = readerBuilder.build();
        // 读取数据
        excelReader.readAll();
        excelReader.finish();
    }

    private void doSomething(List<Object> list) {
        //1、入库调用接口，可在这里写，也可在业务层写
        logger.info("记录导出数据行数：{}", list.size());
        List<EduSubject> originalList = new ArrayList<EduSubject>();
        EduSubject catagory = new EduSubject();
        //转换数据类型
        for (int i = 0; i < list.size(); i++) {
            catagory = (EduSubject) list.get(i);
            originalList.add(catagory);
        }
        logger.info("记录导入数据行数：{}",originalList.size());
        //对list进行去重并拿到新的list
        List<EduSubject> lists = originalList.stream()
                .filter(s -> StringUtils.isNotBlank(s.getTitle()))
                .collect(Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<EduSubject>(Comparator.comparing(EduSubject::getTitle))), ArrayList::new));
        //执行批量插入
        if (lists.size() > 0){
            logger.info("记录导入数据行数：{}",lists.size());
            logger.info("执行批量入库");
            eduSubjectDao.addLists(lists);
            return;
        }
        logger.info("解析数据为空");

    }
}
