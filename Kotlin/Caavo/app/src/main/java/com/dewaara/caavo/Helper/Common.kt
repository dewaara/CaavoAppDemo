package com.dewaara.caavo.Helper

import com.dewaara.caavo.Remote.IMenuRequest
import com.dewaara.caavo.Remote.RetrofitClient

object Common {


    val menuRequest: IMenuRequest
        get() = RetrofitClient.getClient("http://www.json-generator.com/")!!.create(IMenuRequest::class.java)
}