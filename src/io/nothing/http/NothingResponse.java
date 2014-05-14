package io.nothing.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;


public abstract class NothingResponse<T extends Result> {

	private List<T> result = new ArrayList<T>();
	public void transfer(JSONArray jsonArray) throws Exception {
		onSuccess((List<T>)getActualClass().transfer(jsonArray));
	}

	private Type getType() {
		Type mySuperClass = this.getClass().getGenericSuperclass();
		return ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
	}
	
	private <T extends Result> T getActualClass() throws Exception{
		Result cls = null;
		try {
			String clsName = getType().toString().replace("class","").trim();
			cls = (io.nothing.http.Result) Class.forName(clsName).newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) cls;
		
	}


	public void transfer(JSONObject jsonObject) throws Exception {
		onSuccess((T) getActualClass().transfer(jsonObject));
	}

	public abstract void onSuccess(List<T> response);

	public abstract void onSuccess(T response);
	
	public abstract void onFailure(int statusCode, Throwable e,
			JSONObject errorResponse);

}
