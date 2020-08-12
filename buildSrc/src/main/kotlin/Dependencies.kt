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
    val vJunit = "4.12"
    val vExtJunit = "1.1.1"
    val vExpressoCore = "3.2.0"

    val vGradleBuildTools = "4.0.1"
}

object Lib {
    val appcompat = "androidx.appcompat:appcompat:${Version.vAppcompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.vConstraintLayout}"

    val junit = "junit:junit:${Version.vJunit}"
    val extJunit = "androidx.test.ext:junit:${Version.vExtJunit}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Version.vExpressoCore}"

    val gradleBuildTools = "com.android.tools.build:gradle:${Version.vGradleBuildTools}"
}