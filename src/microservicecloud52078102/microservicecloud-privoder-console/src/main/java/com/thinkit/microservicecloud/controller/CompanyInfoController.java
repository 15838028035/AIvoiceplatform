package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.console.Company_Certificate;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.impl.CompanyCertificateServiceImpl;
import com.thinkit.microservicecloud.util.GenerateSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class CompanyInfoController {

    private Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);

    @Value("${company_certificate.filepath}")
    private  String company_filepath;

    @Autowired
    private CompanyCertificateServiceImpl service;

    @RequestMapping(value = "/provider/console/company_certificate/{session}",method = RequestMethod.POST)
    public ResultInfo company(HttpServletRequest request, @RequestParam("file1") MultipartFile file1){

        ResultInfo result = new ResultInfo();
        result.setLogin("");
        result.setResult("");
        logger.info(file1.getOriginalFilename());
        String file1_name =  GenerateSessionId.getSid()+file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf("."),file1.getOriginalFilename().length());
        logger.info(file1_name);
        try {
            uploadfile(file1.getBytes(),company_filepath, file1_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String company_name =  request.getParameter("company_name");
        String company_website =  request.getParameter("company_website");
        String company_field =  request.getParameter("company_field");
        String social_credit_code =  request.getParameter("social_credit_code");
        String address =  request.getParameter("address");
        String contacts =  request.getParameter("contacts");
        String contact_number =  request.getParameter("contact_number");



        if(company_name.equals("") || company_website.equals("") || address.equals("") || company_field.equals("") || social_credit_code.equals("") || company_name == null || company_website==null  || address==null || company_field==null || social_credit_code== null){
            result.setCode("1002");
            result.setMsg("参数不合法");
            return result;
        }

        Company_Certificate company = new Company_Certificate();
        company.setCompany_name(company_name);
        company.setCompany_website(company_website);
        company.setCompany_field(company_field);
        company.setAddress(address);
        company.setContacts(contacts);
        company.setContact_number(contact_number);
        company.setSocial_credit_code(social_credit_code);
        company.setBusiness_license(company_filepath+file1_name);
        company.setStatus(0); // 待审核状态

        result.setCode("1001");
        result.setMsg("已提交审核");
        return result;
    }




    public  void  uploadfile(byte[] file , String  filepath, String filename){
        File target = new File(filepath);
        if(!target.exists()){
            target.mkdirs();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filepath+filename);
            out.write(file);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
