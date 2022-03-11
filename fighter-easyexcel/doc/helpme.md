# Getting Started

## SpringBoot集成EasyExcel的使用
quickly mstaer the web page download excel and upload excel file

github地址：https://github.com/alibaba/easyexcel

####EasyExcel简介
   easyExcel是阿里巴巴开源poi插件之一，主要解决了poi框架使用复杂，sax解析模式不容易操作，数据量大起来容易OOM，解决了POI并发造成的报错。主要解决方式：通过解压文件的方式加载，一行一行的加载，并且抛弃样式字体等不重要的数据，降低内存的占用

#### EasyExcel优势
- 注解式自定义操作。
- 输入输出简单，提供输入输出过程的接口
- 支持一定程度的单元格合并等灵活化操作


#### 依赖
	<!-- 
	<dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>easyexcel</artifactId>
       <version>2.1.4</version>
    </dependency>
	-->
`
- 入门参考：
https://blog.csdn.net/weixin_44146379/article/details/108430585
- 过期字段更新参考：
https://blog.csdn.net/weixin_38405253/article/details/108656204
- 错误案例
https://blog.csdn.net/m0_37628958/article/details/110679707
