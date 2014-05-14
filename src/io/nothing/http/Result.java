package io.nothing.http;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;


/**
 * @author Sanvi E-mail:sanvibyfish@gmail.com
 * @version 创建时间�?010-8-31 下午01:18:16
 */
public interface Result {

	public <T extends Result> List<T> transfer(JSONArray jsonArray);
	public <T extends Result> T transfer(JSONObject jsonObject);
}
