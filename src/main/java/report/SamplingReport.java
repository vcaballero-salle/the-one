package report;

import core.*;

import java.util.List;

/**
 * Abstract class that makes it easier to implement sampling reports.
 *
 * @author teemuk
 */
public abstract class SamplingReport
  extends Report
  implements UpdateListener {

  //========================================================================//
  // Settings
  //========================================================================//
  /**
   * Interval in seconds between samples ({@value}).
   */
  public static final String SAMPLE_INTERVAL_SETTING = "sampleInterval";
  /**
   * Default value for sample interval ({@value} seconds).
   */
  public static final double DEFAULT_SAMPLE_INTERVAL = 60;
  //========================================================================//
  protected final double interval;
  //========================================================================//
  // Instance vars
  //========================================================================//
  private double lastRecord = Double.MIN_VALUE;
  //========================================================================//


  //========================================================================//
  // Report
  //========================================================================//
  protected SamplingReport() {
    super();

    // Set the sampling interval
    final Settings settings = super.getSettings();
    this.interval = settings.getDouble(SAMPLE_INTERVAL_SETTING,
      DEFAULT_SAMPLE_INTERVAL);
    if (this.interval <= 0) {
      throw new SettingsError("Setting '" + SAMPLE_INTERVAL_SETTING
        + "' must be positive. Found " + this.interval + ".");
    }
  }
  //========================================================================//

  //========================================================================//
  // Subclass API
  //========================================================================//
  protected abstract void sample(final List<DTNHost> hosts);
  //========================================================================//

  //========================================================================//
  // UpdateListener
  //========================================================================//
  @Override
  public void updated(final List<DTNHost> hosts) {
    if (SimClock.getTime() - lastRecord < interval) {
      return;
    }
    lastRecord = SimClock.getTime();

    this.sample(hosts);
  }
  //========================================================================//
}
