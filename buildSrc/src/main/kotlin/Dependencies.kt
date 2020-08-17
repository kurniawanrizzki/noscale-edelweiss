/**
 * Definition
 * Created by kurniawanrizzki on 13/08/20.
 */
object Android {
    val compileSdkVersion = 30
    val buildToolsVersion = "30.0.0"
}

object Config {
    val applicationId = "com.noscale.edelweiss"
    val minSdkVersion = 21
    val targetSdkVersion = 30
    val versionCode = 100000
    val versionName = "1.0.0"
}

object Version {
    val vAppcompat = "1.2.0"
    val vConstraintLayout = "1.1.3"
    val vIntuit = "1.0.6"
    val vRecyclerview = "1.1.0"
    val vCardView = "1.0.0"
    val vMaterial = "1.2.0"
    val vRetrofit = "2.9.0"

    val vJunit = "4.12"
    val vExtJunit = "1.1.1"
    val vExpressoCore = "3.2.0"

    val vGradleBuildTools = "4.0.1"
}

object Lib {
    val appcompat = "androidx.appcompat:appcompat:${Version.vAppcompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.vConstraintLayout}"
    val sdp = "com.intuit.sdp:sdp-android:${Version.vIntuit}"
    val ssp = "com.intuit.ssp:ssp-android:${Version.vIntuit}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Version.vRecyclerview}"
    val cardview = "androidx.cardview:cardview:${Version.vCardView}"
    val material = "com.google.android.material:material:${Version.vMaterial}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.vRetrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Version.vRetrofit}"

    val junit = "junit:junit:${Version.vJunit}"
    val extJunit = "androidx.test.ext:junit:${Version.vExtJunit}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Version.vExpressoCore}"

    val gradleBuildTools = "com.android.tools.build:gradle:${Version.vGradleBuildTools}"
}