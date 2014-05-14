package io.nothing.http;

import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NothingRestClient {

	private static String baseURL = "";

	private static AsyncHttpClient client = new AsyncHttpClient();

	public NothingRestClient(String baseURL) {
		this.baseURL = baseURL;
	}

	public void get(String url, NothingResponse response,NothingParam... params
			) {
		client.get(url, stripNulls(params), jsonHttpResponseHandler(response));
	}

	public void post(String url, NothingResponse response,NothingParam... params) {
		client.post(url, stripNulls(params), jsonHttpResponseHandler(response));
	}

	private RequestParams stripNulls(NothingParam... nothingParams) {
		RequestParams params = new RequestParams();
		if (nothingParams != null) {
			for (int i = 0; i < nothingParams.length; i++) {
				NothingParam param = nothingParams[i];
				if (param.getValue() != null) {
					if (param instanceof NothingStringParam) {
						NothingStringParam nothingParam = (NothingStringParam) param;
						params.put(nothingParam.getName(), nothingParam.getValue());
					}
					
				}
			}
		}
		return params;
	}
	
	private <T extends Result> AsyncHttpResponseHandler jsonHttpResponseHandler(final NothingResponse<T> response){
		return new JsonHttpResponseHandler() {
			
			@Override
			public void onSuccess(JSONArray jsonArray) {
				try {
					System.out.println(jsonArray.toString());
					response.transfer(jsonArray);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			@Override
			public void onSuccess(JSONObject jsonObject) {
				try {
					System.out.println(jsonObject.toString());
					response.transfer(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFailure(int statusCode, Throwable e,
					JSONObject errorResponse) {
				response.onFailure(statusCode, e, errorResponse);
			}
		};
		
	}
}
