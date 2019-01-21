package com.eight41.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController
{    
    @RequestMapping(value = "/show", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String downloaded(HttpServletRequest request, HttpServletResponse response)
    {
        List<List<Object>> data = new ArrayList<>();
        
        List<Object> ob1 = new ArrayList<>();
        ob1.add("in.txt");
        ob1.add(6);
        ob1.add("text");
        ob1.add("downloaded/sample.txt");
        data.add(ob1);
        
        ob1 = new ArrayList<>();
        ob1.add("sample.png");
        ob1.add(4);
        ob1.add("image");
        ob1.add("downloaded/sample.gif");
        data.add(ob1);
        
        ob1 = new ArrayList<>();
        ob1.add("content.html");
        ob1.add(9);
        ob1.add("html");
        ob1.add("downloaded/sample.html");
        data.add(ob1);

        String json = "[";
        
        int sz = data.size();
        for(int i = 0; i < sz; i++)
        {
            if(i > 0) {
                json += ",";
            }
            List<Object> item = data.get(i);
            
            json += "{"
                + "\"id\":\"" + i + "\","    
                + "\"file\":\"" + item.get(0) + "\","
                + "\"size\":\"" + item.get(1) + "\","
                + "\"type\":\"" + item.get(2) + "\","
                + "\"src\":\"" + item.get(3) + "\""
                + "}";
        }
        
        json += "]";
        
        return json;
    }
    
    @RequestMapping(value = "/update", params = {"state"}, method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String updateStatus(@RequestParam(value = "state") boolean state)
    {
        System.out.println("state: " + state);
        return "{}";
    }
    
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String get(@RequestParam(value = "id") int id)
    {
        System.out.println("id: " + id);
        
        String types[] = {"text", "image", "html"};
        String texts[] = {"this is a text", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==", "<html><h1>What's up</h1></html>"};
        String json = "{"
                + "\"id\":\"" + id + "\","    
                + "\"type\":\"" + types[id] + "\","    
                + "\"content\":\"" + texts[id] + "\""
        + "}";
        
        return json;
    }
}
