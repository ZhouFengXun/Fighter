package com.fanlan.fightereasyexcel.controller;


import com.fanlan.fightereasyexcel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    private ExcelService excelService;
    /**
     * 导出用户信息
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/excelExport")
    public void excelExport(HttpServletResponse response) throws IOException {
        excelService.excelExport(response);
    }

    /**
     * 导出用户信息
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/excelExport1")
    public void excelExport1(HttpServletResponse response) throws IOException {
        excelService.excelExport1(response);
    }
    /**
     * 导入用户信息
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/user/excelImport")
    public String excelImport(@RequestParam("file") MultipartFile file) throws IOException {
        excelService.excelImport(file);
        return "success";
    }
    /**
     * 导入用户信息2
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/user/excelImport1")
    public String excelImport1(@RequestParam("file") MultipartFile file) throws IOException {
        excelService.excelImport(file);
        return "success";
    }
}
