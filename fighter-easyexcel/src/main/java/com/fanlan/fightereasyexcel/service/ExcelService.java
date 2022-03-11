package com.fanlan.fightereasyexcel.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {
    /**
     * 导出用户信息
     * @param response
     * @throws IOException
     */

    void excelExport(HttpServletResponse response) throws IOException;
    /**
     * 导出用户信息2
     * @param response
     * @throws IOException
     */
    void excelExport1(HttpServletResponse response) throws IOException;

    /**
     * 导入用户信息
     * @param file
     * @throws IOException
     */
    void excelImport(MultipartFile file) throws IOException;


    /**
     * 导入用户信息2
     * @param file
     * @throws IOException
     */
    void excelImport1(MultipartFile file) throws IOException;
}
