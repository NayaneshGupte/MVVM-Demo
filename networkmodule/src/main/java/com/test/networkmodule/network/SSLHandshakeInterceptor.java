package com.test.networkmodule.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.CipherSuite;
import okhttp3.Handshake;
import okhttp3.Response;
import okhttp3.TlsVersion;

/**
 * Prints TLS Version and Cipher Suite for SSL Calls through OkHttp3
 */
public class SSLHandshakeInterceptor implements okhttp3.Interceptor {

    private static final String TAG = "OkHttp3-SSLHandshake";

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Log.d(TAG, "host " + chain.request().url().host());


        final Response response = chain.proceed(chain.request());

        printTlsAndCipherSuiteInfo(response);
        return response;
    }

    private void printTlsAndCipherSuiteInfo(Response response) {
        if (response != null) {
            Handshake handshake = response.handshake();
            if (handshake != null) {
                final CipherSuite cipherSuite = handshake.cipherSuite();
                final TlsVersion tlsVersion = handshake.tlsVersion();

                Log.d(TAG, "TLS: " + tlsVersion + ", CipherSuite: " + cipherSuite);
            }
        }
    }
}