#import "IntiPrinterPlugin.h"
#if __has_include(<inti_printer/inti_printer-Swift.h>)
#import <inti_printer/inti_printer-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "inti_printer-Swift.h"
#endif

@implementation IntiPrinterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftIntiPrinterPlugin registerWithRegistrar:registrar];
}
@end
