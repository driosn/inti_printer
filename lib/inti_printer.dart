// You have generated a new plugin project without
// specifying the `--platforms` flag. A plugin project supports no platforms is generated.
// To add platforms, run `flutter create -t plugin --platforms <platforms> .` under the same
// directory. You can also find a detailed instruction on how to add platforms in the `pubspec.yaml` at https://flutter.dev/docs/development/packages-and-plugins/developing-packages#plugin-platforms.

import 'dart:async';

import 'package:flutter/services.dart';

class IntiPrinter {
  static const MethodChannel _channel =const MethodChannel('inti_printer');

  Future<void> imprimir(String texto, String mac) async {
    try {
      final respuesta = await _channel.invokeMethod('imprimir_texto', [texto, mac]);
      if (respuesta == "OK") {
        return respuesta;
      }
    } on PlatformException {
      rethrow;
    }
  }
}
