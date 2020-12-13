package com.dewaara.caavo.Helper;

import com.dewaara.caavo.Remote.IMenuRequest;
import com.dewaara.caavo.Remote.RetrofitClient;

public class Common {

    public static IMenuRequest getMenuRequest()
    {
        return RetrofitClient.getClient("http://www.json-generator.com/").create(IMenuRequest.class);
    }
}
