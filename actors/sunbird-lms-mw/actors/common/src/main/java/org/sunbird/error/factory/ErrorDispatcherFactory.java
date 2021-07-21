package org.sunbird.error.factory;

import org.sunbird.error.CsvError;
import org.sunbird.error.CsvErrorDispatcher;
import org.sunbird.error.IErrorDispatcher;
import org.sunbird.error.ListErrorDispatcher;
import org.sunbird.keys.JsonKey;
import org.sunbird.logging.LoggerUtil;
import org.sunbird.util.PropertiesCache;

/**
 * this is error dispatcher factory class which will judge type of error need to show on the basis
 * of error count.
 *
 * @author anmolgupta
 */
public class ErrorDispatcherFactory {

  private static LoggerUtil logger = new LoggerUtil(ErrorDispatcherFactory.class);

  /** this ERROR_VISUALIZATION_THRESHOLD will decide in which need to show errors */
  public static final int ERROR_VISUALIZATION_THRESHOLD = getErrorVisualizationThreshold();

  /**
   * this method will return the required error dispatcher class object
   *
   * @param error
   * @return IErrorDispatcher
   */
  public static IErrorDispatcher getErrorDispatcher(CsvError error) {
    if (error.getErrorsList().size() > ERROR_VISUALIZATION_THRESHOLD) {
      return CsvErrorDispatcher.getInstance(error);
    }
    return ListErrorDispatcher.getInstance(error);
  }

  /**
   * this method will return the ERROR_VISUALIZATION_THRESHOLD value
   *
   * @return int
   */
  private static int getErrorVisualizationThreshold() {
    String value =
        PropertiesCache.getInstance().readProperty(JsonKey.ERROR_VISUALIZATION_THRESHOLD);
    logger.info(
        "ErrorDispatcherFactory:getErrorVisualizationThreshold:threshold got ".concat(value + ""));
    return Integer.parseInt(value);
  }
}
