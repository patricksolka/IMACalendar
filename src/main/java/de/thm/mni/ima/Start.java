package de.thm.mni.ima;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * The main class of the application.
 */
public class Start {
  /**
   * The port the server listens on.
   */
  private final static int PORT = 8080;
  private static Logger log = LogManager.getLogger(Start.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();

    startHttpServer(vertx)
      .flatMap(Start::initialize)
      .onSuccess(Start::printSuccessMessage)
      .onFailure(Start::printErrorMessage);
  }

  private static Future<HttpServer> startHttpServer(Vertx vertx) {
    return vertx.createHttpServer()
      .requestHandler(new Api(vertx).getRouter())
      .listen(PORT);
  }

  private static Future<HttpServer> initialize(HttpServer httpServer) {
    log.info("Initializing data storage ...");

    try {
      Bootstrap.initializeDataStorage();
    } catch (IOException e) {
      return Future.failedFuture(e);
    }

    log.info("Initialization completed.");

    return Future.succeededFuture(httpServer);
  }

  private static void printSuccessMessage(HttpServer httpServer) {
    log.info(MessageFormat.format(
      "Server started and listing on port {0,number,#}", PORT
    ));
  }

  private static void printErrorMessage(Throwable throwable) {
    log.error("Failed to start server", throwable);
  }
}
