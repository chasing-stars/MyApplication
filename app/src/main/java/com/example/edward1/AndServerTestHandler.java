package com.example.edward1;
/**
 * Created by Edward on 2016/8/26 19:52.
 * 自动生成的模版
 */

import com.yanzhenjie.andserver.AndServerRequestHandler;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 *
 * @author Edward
 * @revised by
 * Ddddddddd

 * Ower PDD
 */
public class AndServerTestHandler implements AndServerRequestHandler {
    @Override
    public void handle(HttpRequest rq, HttpResponse rp, HttpContext ct) throws HttpException, IOException {
       // Log.d("fuck", "handle: 0---------");
        rp.setEntity(new StringEntity("请求成功。", "utf-8"));
        //HttpServFileUpload
    }
}
