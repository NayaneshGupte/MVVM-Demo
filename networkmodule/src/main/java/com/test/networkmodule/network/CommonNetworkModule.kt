package com.test.networkmodule.network

import android.annotation.SuppressLint
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Module to provide okhttp and Moshi across this NetworkSDK for different APIs
 */
@Module
internal object CommonNetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providesOkHttp(): OkHttpClient {

        val builder = OkHttpClient.Builder()

        builder.sslSocketFactory(getSSLSocketFactory()!!, getX509TrustManager())
        builder.addInterceptor(SSLHandshakeInterceptor())
        builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

        return builder.build()
    }


    private fun getSSLSocketFactory(): SSLSocketFactory? {

        val sc: SSLContext?
        var sslSocketFactory: SSLSocketFactory? = null
        try {
            sc = SSLContext.getInstance(TlsVersion.TLS_1_2.javaName)
            if (sc != null) {
                sc.init(null, getAllTrustManagers(), SecureRandom())
                sslSocketFactory = Tls12SocketFactory(sc.socketFactory)
            } else {
                Timber.e("SSLContext is null")
            }
        } catch (e: NoSuchAlgorithmException) {
            Timber.e(e)
        } catch (e: KeyManagementException) {
            Timber.e(e)
        }

        return sslSocketFactory
    }


    private fun getAllTrustManagers(): Array<TrustManager> {
        return arrayOf(getX509TrustManager())
    }


    private fun getX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {
            }
        }
    }
}
