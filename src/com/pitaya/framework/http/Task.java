package com.pitaya.framework.http;

import com.pitaya.framework.http.BaseTask.OnInvokeAterListener;
import com.pitaya.framework.http.BaseTask.OnInvokeBeforeListener;

import android.app.ProgressDialog;
import android.content.Context;

public class Task extends BaseTask {

	private OnTaskRequestListener onTaskRequestListener;
	
	public OnTaskRequestListener getOnTaskRequestListener() {
		return onTaskRequestListener;
	}

	public Task setOnTaskRequestListener(OnTaskRequestListener onTaskRequestListener) {
		this.onTaskRequestListener = onTaskRequestListener;
		return this;
	}
	
	
	public Task setOnInvokeBeforeListener(
			OnInvokeBeforeListener onInvokeBeforeListener) {
		super.setOnInvokeBeforeListener(onInvokeBeforeListener);
		return this;
	}
	
	public Task setOnInvokeAfterListener(OnInvokeAterListener onInvokeAfterListener) {
		super.setOnInvokeAfterListener(onInvokeAfterListener);
		return this;
	}
	

	public Task(Context context) {
		super(context);
	}
	
	

	@Override
	public Result request() throws Exception {
		if(onTaskRequestListener != null) {
			return onTaskRequestListener.onRequest();
		}
		return null;
	}
	
	
	public interface OnTaskRequestListener {
		public Result onRequest() throws Exception;
	}



}
