package com.reactnativesunmiprinting;

import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = SunmiPrintingModule.NAME)
public class SunmiPrintingModule extends ReactContextBaseJavaModule {

    public static final String NAME = "SunmiPrinting";
    public static final String SERVICE_NOT_FOUND = "SERVICE_NOT_FOUND";

    public SunmiPrintingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        SunmiPrintHelper.getInstance().initSunmiPrinterService(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void print(String text, Promise promise) {
        SunmiPrintHelper.getInstance().printText2(text);
        promise.resolve(true);
    }

    @ReactMethod
    public void clearBuffer(Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.clearBuffer();
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void printOriginalText(String text, Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.printOriginalText(text, null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setAlignment(int alignment, Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.setAlignment(alignment, null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setFontSize(float fontSize, Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.setFontSize(fontSize, null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void lineWrap(int linesToWrap, Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.lineWrap(linesToWrap, null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void printBitmap(String base64, int height, int width, Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            byte[] decoded = Base64.decode(base64, Base64.DEFAULT);
            final Bitmap bitMap = BitmapUtil.decodeBitmap(decoded, width, height);
            SunmiPrintHelper.getInstance().sunmiPrinterService.printBitmap(bitMap, null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
        promise.resolve(true);
    }

    @ReactMethod
    public void printQRCode(String data, int modulesize, int errorlevel, Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.printQr(data, modulesize, errorlevel, null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
        promise.resolve(true);
    }

    @ReactMethod
    public void setBold(boolean bold, Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            if (bold) {
                SunmiPrintHelper.getInstance().sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
            } else {
                SunmiPrintHelper.getInstance().sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
            }
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void underlineWithOneDotWidthOn(Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void underlineWithTwoDotWidthOn(Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.sendRAWData(ESCUtil.underlineWithTwoDotWidthOn(), null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void underlineOff(Promise promise) {
        if (SunmiPrintHelper.getInstance().sunmiPrinterService == null) {
            promise.reject(SERVICE_NOT_FOUND, "Printer Service is null");
            return;
        }

        try {
            SunmiPrintHelper.getInstance().sunmiPrinterService.sendRAWData(ESCUtil.underlineOff(), null);
            promise.resolve(true);
        } catch (RemoteException e) {
            promise.reject(e);
        }
    }
}
