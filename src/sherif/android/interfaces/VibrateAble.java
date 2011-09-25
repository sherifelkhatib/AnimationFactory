package sherif.android.interfaces;

/**
 * Interface implemented by views that vibrate.
 * @author Sherif elKhatib
 *
 */
public interface VibrateAble {
	/**
	 * Called to cause the view to vibrate
	 */
	void startVibrating();
	/**
	 * Called to cause the view to stop vibrating
	 */
	void stopVibrating();
	/**
	 * @return <li>true if the view is vibrating</li><li>false otherwise</li>
	 */
	boolean isVibrating();
	
}
