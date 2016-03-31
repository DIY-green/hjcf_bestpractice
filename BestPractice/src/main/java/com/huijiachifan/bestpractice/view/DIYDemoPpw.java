package com.huijiachifan.bestpractice.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.huijiachifan.bestpractice.R;

import java.lang.ref.WeakReference;

public class DIYDemoPpw extends PopupWindow {

	private Activity activity;
	private View conentView;

	public DIYDemoPpw(final Activity context) {
		activity = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.ppw_diydemo, null);
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 2 + 50);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);
		backgroundAlpha(0.5f);
		//添加pop窗口关闭事件
		this.setOnDismissListener(new PoponDismissListener(this));
	}

	public void showPopupWindow(View parent, int yoff) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, parent.getLayoutParams().width / 2, yoff);
		} else {
			this.dismiss();
		}
	}

	/**
	 * 设置添加屏幕的背景透明度
	 * @param bgAlpha
	 */
	private void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = bgAlpha; //0.0-1.0
		activity.getWindow().setAttributes(lp);
	}

	private static class PoponDismissListener implements PopupWindow.OnDismissListener{

		private WeakReference<DIYDemoPpw> diyDemoPpwRef;

		public PoponDismissListener(DIYDemoPpw diyPopup) {
			this.diyDemoPpwRef = new WeakReference<DIYDemoPpw>(diyPopup);
		}

		@Override
		public void onDismiss() {
			DIYDemoPpw diyPopup = diyDemoPpwRef.get();
			if (diyPopup == null) return;
			diyPopup.backgroundAlpha(1f);
		}

	}

}
