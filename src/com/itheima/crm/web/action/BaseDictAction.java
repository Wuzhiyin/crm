package com.itheima.crm.web.action;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;


public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    private BaseDict baseDict = new BaseDict();
    @Override
    public BaseDict getModel() {
        return baseDict;
    }

    //注入service
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    public String findByTypeCode() throws IOException {
        System.out.println("BaseDictAction的findByTypeCode方法执行了");
        //调用业务层查询
        List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        System.out.println(jsonArray.toString());
        //将JSON打印到页面
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }

}
