package cn.crane.framework.utils;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.ActivityMannager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 单选对话框
 * 
 * @author Administrator
 * 
 */
public class DialogSelector {
	/**
	 * 单选对话框
	 * 
	 * @param context
	 * @param sTitle
	 * @param choices
	 * @param tv
	 */
	public static void showSelectDlg(Context context, String sTitle,
			final String[] choices, final TextView tv) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if (sTitle != null) {
			builder.setTitle(sTitle);
		}
		builder.setSingleChoiceItems(choices, 0,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (tv != null)
							tv.setText(choices[which]);
					}
				});
		builder.create().show();
	}

	/**
	 * 单选对话框
	 * 
	 * @param context
	 * @param sTitle
	 * @param choices
	 * @param tv
	 */
	public static void showSelectDlg(Context context, String sTitle,
			final String[] choices,
			final OnItemSelectListener onItemSelectListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if (!TextUtils.isEmpty(sTitle)) {
			builder.setTitle(sTitle);
		}
		builder.setSingleChoiceItems(choices, 0,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (onItemSelectListener != null) {
							onItemSelectListener.onItemSelected(which);
						}
					}
				});
		builder.create().show();
	}

	public static void showAlertDialog(final Context context, String msg,
			final OnItemSelectListener onItemSelectListener) {
		new AlertDialog.Builder(context).setMessage(msg)
				.setNegativeButton(R.string.txt_ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (onItemSelectListener != null) {
							onItemSelectListener.onItemSelected(0);
						}
					}
				}).show();
	}

	public static void showAlertDialog(final Context context, String msg,
			boolean cancelable, final OnItemSelectListener onItemSelectListener) {
		new AlertDialog.Builder(context).setMessage(msg)
				.setCancelable(cancelable)
				.setNegativeButton(R.string.txt_ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (onItemSelectListener != null) {
							onItemSelectListener.onItemSelected(0);
						}
					}
				}).show();
	}
	

	public static void showConfirmDialog(final Context context, String msg,
			final OnItemSelectListener onItemSelectListener) {
		new AlertDialog.Builder(context).setMessage(msg)
				.setPositiveButton(R.string.txt_cancel, null)
				.setNegativeButton(R.string.txt_ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (onItemSelectListener != null) {
							onItemSelectListener.onItemSelected(0);
						}
					}
				}).show();
	}
	public static void showConfirmDialog(final Context context, String title,String msg,
			final OnItemSelectListener onItemSelectListener) {
		new AlertDialog.Builder(context).setMessage(msg).setTitle(title)
		.setPositiveButton(R.string.txt_cancel, null)
		.setNegativeButton(R.string.txt_ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (onItemSelectListener != null) {
					onItemSelectListener.onItemSelected(0);
				}
			}
		}).show();
	}
	public static void showConfirmExitDialog(final Context context,String title, String msg,
			final OnItemSelectListener onItemSelectListener) {
		new AlertDialog.Builder(context).setMessage(msg).setTitle(title)
		.setCancelable(false)
		.setPositiveButton(R.string.txt_exit, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityMannager.getInstance().clearActivity();
			}
		})
		.setNegativeButton(R.string.txt_ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (onItemSelectListener != null) {
					onItemSelectListener.onItemSelected(0);
				}
			}
		}).show();
	}

//	public static void showConfirmDialog(final Context context, String msg,
//			final OnCommitListener onCommitListener) {
//		LinearLayout layout = new LinearLayout(context);
//		int padding = (int) (10 * App.fDensity);
//		layout.setPadding(padding , padding, padding, padding);
//	
//		final EditText et = new EditText(context);
//		et.setBackgroundResource(R.drawable.bg_edit_login);
//		et.setPadding(10, 10, 10, 10);
//		et.setSingleLine();
//		et.setGravity(Gravity.LEFT|Gravity.TOP);
//		layout.addView(et);
//		new AlertDialog.Builder(context).setView(layout)
////		.setTitle(R.string.appointment_complaint)
//				.setPositiveButton(R.string.txt_cancel, null)
//				.setNegativeButton(R.string.txt_ok, new OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						if (onCommitListener != null) {
//							onCommitListener.onCommit(et.getText().toString()
//									.trim());
//						}
//					}
//				}).show();
//	}

	public static void showPhoneCall(final Context context, String msg,
			final OnItemSelectListener onItemSelectListener) {
		new AlertDialog.Builder(context).setMessage(msg)
				.setNegativeButton(R.string.txt_cancel, null)
				.setPositiveButton(R.string.txt_ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (onItemSelectListener != null) {
							onItemSelectListener.onItemSelected(0);
						}
					}
				}).show();
	}

	public interface OnItemSelectListener {
		public void onItemSelected(int position);
	}

	public interface OnCommitListener {
		public void onCommit(String input);
	}

}
