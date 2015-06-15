package cn.crane.framework.view.smartimage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;


public class SmartImageView extends ImageView {
	private static final int LOADING_THREADS = 4;
	private static ExecutorService threadPool = Executors
			.newFixedThreadPool(SmartImageView.LOADING_THREADS);

	private SmartImageTask currentTask;
	private boolean roundCorner = false;
	private float roundPx = 10;
	private float fDensity = 1.0f;

	public void setRoundCorner(boolean roundCorner) {
		this.roundCorner = roundCorner;
	}

	public SmartImageView(Context context) {
		super(context);
		fDensity = getResources().getDisplayMetrics().density;
		roundPx = 10 * fDensity;
	}

	public SmartImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		fDensity = getResources().getDisplayMetrics().density;
		roundPx = 10 * fDensity;
	}

	public SmartImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		fDensity = getResources().getDisplayMetrics().density;
		roundPx = 10 * fDensity;
	}

	// Helpers to set image by URL
	public void setImageUrl(String url) {
		this.setImage(new WebImage(url));
	}

	public void setImageUrl(String url,
			SmartImageTask.OnCompleteListener completeListener) {
		this.setImage(new WebImage(url), completeListener);
	}

	public void setImageUrl(String url, final Integer fallbackResource) {
		this.setImage(new WebImage(url), fallbackResource);
	}

	public void setImageUrl(String url, final Integer fallbackResource,
			SmartImageTask.OnCompleteListener completeListener) {
		this.setImage(new WebImage(url), fallbackResource, completeListener);
	}

	public void setImageUrl(String url, final Integer fallbackResource,
			final Integer loadingResource) {
		this.setImage(new WebImage(url), fallbackResource, loadingResource);
	}

	public void setImageUrl(String url, final Integer fallbackResource,
			final Integer loadingResource,
			SmartImageTask.OnCompleteListener completeListener) {
		if(!TextUtils.isEmpty(url))
		{
			url.replaceAll("//", "/");
		}
		this.setImage(new WebImage(url), fallbackResource, loadingResource,
				completeListener);
	}

	// Helpers to set image by contact address book id
	public void setImageContact(long contactId) {
		this.setImage(new ContactImage(contactId));
	}

	public void setImageContact(long contactId, final Integer fallbackResource) {
		this.setImage(new ContactImage(contactId), fallbackResource);
	}

	public void setImageContact(long contactId, final Integer fallbackResource,
			final Integer loadingResource) {
		this.setImage(new ContactImage(contactId), fallbackResource,
				fallbackResource);
	}

	// Set image using SmartImage object
	public void setImage(final SmartImage image) {
		this.setImage(image, null, null, null);
	}

	public void setImage(final SmartImage image,
			final SmartImageTask.OnCompleteListener completeListener) {
		this.setImage(image, null, null, completeListener);
	}

	public void setImage(final SmartImage image, final Integer fallbackResource) {
		this.setImage(image, fallbackResource, fallbackResource, null);
	}

	public void setImage(final SmartImage image,
			final Integer fallbackResource,
			SmartImageTask.OnCompleteListener completeListener) {
		this.setImage(image, fallbackResource, fallbackResource,
				completeListener);
	}

	public void setImage(final SmartImage image,
			final Integer fallbackResource, final Integer loadingResource) {
		this.setImage(image, fallbackResource, loadingResource, null);
	}

	public void setImage(final SmartImage image,
			final Integer fallbackResource, final Integer loadingResource,
			final SmartImageTask.OnCompleteListener completeListener) {
		// Set a loading resource
		if (loadingResource != null) {
			this.setImageResource(loadingResource);
		}

		// Cancel any existing tasks for this image view
		if (this.currentTask != null) {
			this.currentTask.cancel();
			this.currentTask = null;
		}

		// Set up the new task
		this.currentTask = new SmartImageTask(this.getContext(), image);
		this.currentTask
				.setOnCompleteHandler(new SmartImageTask.OnCompleteHandler() {
					@Override
					public void onComplete(Bitmap bitmap) {
						if (bitmap != null) {
							if (roundCorner) {
//								bitmap = ImageUtils.getRoundedCornerBitmap(
//										bitmap, roundPx);
							}
							SmartImageView.this.setImageBitmap(bitmap);
						} else {
							// Set fallback resource
							if (fallbackResource != null) {
								SmartImageView.this
										.setImageResource(fallbackResource);
							}
						}

						if (completeListener != null) {
							completeListener.onComplete();
						}
					}
				});

		// Run the task in a threadpool
		SmartImageView.threadPool.execute(this.currentTask);
	}

	public void setBackGroundUrl(String url) {
		this.setBackgroundImage(new WebImage(url), 0, 0, null);
	}

	public void setBackgroundImage(final SmartImage image,
			final Integer fallbackResource, final Integer loadingResource,
			final SmartImageTask.OnCompleteListener completeListener) {
		// Set a loading resource
		if (loadingResource != null) {
			this.setBackgroundResource(loadingResource);
		}

		// Cancel any existing tasks for this image view
		if (this.currentTask != null) {
			this.currentTask.cancel();
			this.currentTask = null;
		}

		// Set up the new task
		this.currentTask = new SmartImageTask(this.getContext(), image);
		this.currentTask
				.setOnCompleteHandler(new SmartImageTask.OnCompleteHandler() {
					@Override
					public void onComplete(Bitmap bitmap) {
						if (bitmap != null) {
							SmartImageView.this
									.setBackgroundDrawable(convertBitmap2Drawable(bitmap));
						} else {
							// Set fallback resource
							if (fallbackResource != null) {
								SmartImageView.this
										.setBackgroundResource(fallbackResource);
							}
						}

						if (completeListener != null) {
							completeListener.onComplete();
						}
					}
				});

		// Run the task in a threadpool
		SmartImageView.threadPool.execute(this.currentTask);
	}

	public static void cancelAllTasks() {
		SmartImageView.threadPool.shutdownNow();
		SmartImageView.threadPool = Executors
				.newFixedThreadPool(SmartImageView.LOADING_THREADS);
	}

	/**
	 * Bitmap to Drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public Drawable convertBitmap2Drawable(Bitmap bitmap) {
		if (roundCorner) {
//			bitmap = ImageUtils.getRoundedCornerBitmap(bitmap, roundPx);
		}
		return new BitmapDrawable(bitmap);
	}
}