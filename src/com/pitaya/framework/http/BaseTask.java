package com.pitaya.framework.http;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.Toast;

import com.pitaya.framework.SupportException;
import com.pitaya.framework.utils.ActivityUtils;
import com.pitaya.framework.R;

/**
 * @author Sanvi E-mail:sanvibyfish@gmail.com
 * @version 创建时间2010-8-19 上午11:54:22
 */
public abstract class BaseTask extends AsyncTask<String, Void, TaskResult>  {
	protected Context context = null;
	
	private static String TAG = "BaseTask";
	private static boolean DEBUG = true;
	private static OnTaskInitListener onTaskInitListener;
	
	
	public static OnTaskInitListener getOnTaskInitListener() {
		return onTaskInitListener;
	}

	public static void setOnTaskInitListener(OnTaskInitListener onTaskInitListener) {
		BaseTask.onTaskInitListener = onTaskInitListener;
	}

	private  OnInvokeBeforeListener onInvokeBeforeListener;
	public OnInvokeBeforeListener getOnInvokeBeforeListener() {
		return onInvokeBeforeListener;
	}

	public BaseTask setOnInvokeBeforeListener(
			OnInvokeBeforeListener onInvokeBeforeListener) {
		this.onInvokeBeforeListener = onInvokeBeforeListener;
		return this;
	}

	
	private OnInvokeAterListener onInvokeAfterListener;

	private OnInvokeErrorListener onInvokeErrorListener;
	
	public OnInvokeErrorListener getOnInvokeErrorListener() {
		return onInvokeErrorListener;
	}

	public void setOnInvokeErrorListener(OnInvokeErrorListener onInvokeErrorListener) {
		this.onInvokeErrorListener = onInvokeErrorListener;
	}

	public interface OnInvokeErrorListener {
		public void onInvokeError(TaskResult result);
	}
	
	public OnInvokeAterListener getOnInvokeAfterListener() {
		return onInvokeAfterListener;
	}

	public BaseTask setOnInvokeAfterListener(OnInvokeAterListener onInvokeAfterListener) {
		this.onInvokeAfterListener = onInvokeAfterListener;
		return this;
	}

	private interface OnTaskInitListener {
		public void onTaskInitListener();
	}

	public BaseTask(Context context){
		if(onTaskInitListener != null) {
			onTaskInitListener.onTaskInitListener();
		}
	}
	
	

	public interface OnInvokeBeforeListener {
		public void onInvokeBefore();
	}
	
	public interface OnInvokeAterListener {
		public void onInvokeAter(TaskResult result);
	}
	
	
	@Override
	 protected void onPreExecute() {
		//执行客户写的代码
		if(onInvokeBeforeListener != null) {
			onInvokeBeforeListener.onInvokeBefore();
		}
	}

	@Override
	protected TaskResult doInBackground(String... strings) {
		TaskResult result = new TaskResult();
		try {

			result.successed = true;
			result.result = request();
		}catch (Exception e) {
			if(DEBUG)e.printStackTrace();
			result.successed = false;
			result.exception = e;
			return result;
		}
		return result;
	}
	
	/*
	 * 在doInBackground 执行完成后，onPostExecute 方法将被UI thread调用�?*
	 * 后台的计算结果将通过该方法传递到UI thread.
	 */
	@Override
	protected void onPostExecute(TaskResult result) {
		if(result.successed && onInvokeAfterListener != null){
			onInvokeAfterListener.onInvokeAter(result);
		}else if (onInvokeErrorListener != null ){
			onInvokeErrorListener.onInvokeError(result);
		}
	}
	
	/**
	 * 获取数据
	 */
	abstract public Result request() throws Exception;
	
	
}
