package com.example.inti_printer;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** IntiPrinterPlugin */
public class IntiPrinterPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "inti_printer");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("imprimir_texto")) {
      ArrayList arguments = (ArrayList) call.arguments;
      String textoAImprimir = (String) arguments.get(0);
      String direccionMac = (String) arguments.get(1);

      try {
          Connection conexionBluetooth = new BluetoothConnectionInsecure(direccionMac);

          conexionBluetooth.open();
          String zplData = textoAImprimir;
          conexionBluetooth.write(zplData.getBytes());
          conexionBluetooth.close();

          Boolean conexionCerrada = false;
          while(conexionCerrada == false) {
              conexionCerrada = !conexionBluetooth.isConnected();
          }

          if (conexionCerrada) {
              result.success("OK");
          }
          Looper myLooper = Looper.myLooper();
          if (myLooper != null) {
              myLooper.quit();
          }
      } catch (ConnectionException connectionException) {
          connectionException.printStackTrace();
          result.error("1", "Fallo de conexión", connectionException.getMessage());
      }
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
