package com.pitaya.framework.widget;

import java.util.List;

import com.pitaya.framework.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * 快速选择框
 * @author sanvi
 *
 */
public class SideBar extends View{

	private List<String> keys;  
    private SectionIndexer sectionIndexter = null;  
    private ListView list;  
    private final int m_nItemHeight = 22;  
    private TextView overlay;
    private OnSideBarTouchEventListener onSideBarTouchEventListener;
    
    public OnSideBarTouchEventListener getOnSideBarTouchEventListener() {
		return onSideBarTouchEventListener;
	}
	public void setOnSideBarTouchEventListener(
			OnSideBarTouchEventListener onSideBarTouchEventListener) {
		this.onSideBarTouchEventListener = onSideBarTouchEventListener;
	}
	
	
	public SideBar(Context context) {  
        super(context);  
        init();  
    }  
    public SideBar(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        init();  
    }  
    private void init() {  
        setBackgroundColor(getResources().getColor(android.R.color.transparent));  
    }  
    public SideBar(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        init();  
    }  
    public void setListView(ListView _list,List<String> keys,TextView overlay) {  
        list = _list;  
        this.overlay = overlay;
        this.keys = keys;
        sectionIndexter = (SectionIndexer) _list.getAdapter();  
    }  
    public boolean onTouchEvent(MotionEvent event) {  
        super.onTouchEvent(event);  
        int i = (int) event.getY();  
        int idx = i / m_nItemHeight;  
        if (idx >= keys.size()) {  
            idx = keys.size() - 1;  
        } else if (idx < 0) {  
            idx = 0;  
        }  
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {  
        	setBackgroundColor(getResources().getColor(R.color.side_bar_bg));  
            if (sectionIndexter == null) {  
                sectionIndexter = (SectionIndexer) list.getAdapter();  
            }  
            int position = sectionIndexter.getPositionForSection(idx);  
            if (position == -1) {  
                return true;  
            }  
            if(onSideBarTouchEventListener != null) {
            	onSideBarTouchEventListener.onSideBarTouchEventListener(list,position);
            }
            
            list.setSelection(position);  
        }  else{
        	setBackgroundColor(getResources().getColor(android.R.color.transparent));  
        }
        return true;  
    }  
    
    public interface OnSideBarTouchEventListener {
    	public void onSideBarTouchEventListener(ListView list, int position);
    }
    protected void onDraw(Canvas canvas) {  
        Paint paint = new Paint();  
        paint.setColor(0xFFA6A9AA);  
        paint.setTextSize(20);  
        paint.setTextAlign(Paint.Align.CENTER);  
        float widthCenter = getMeasuredWidth() / 2;  
        for (int i = 0; i < keys.size(); i++) {  
            canvas.drawText(String.valueOf(keys.get(i)), widthCenter, m_nItemHeight + (i * m_nItemHeight), paint);  
        }  
        super.onDraw(canvas);  
    }  
	
	

}
