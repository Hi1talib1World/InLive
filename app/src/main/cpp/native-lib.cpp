#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_denzo_in_1live_Activity_SplashActivity_casdAA(JNIEnv *env, jobject thiz) {
    jclass native_context = env->GetObjectClass(thiz);

    // context.getPackageManager()
    jmethodID methodID_func = env->GetMethodID(native_context, "getPackageManager", "()Landroid/content/pm/PackageManager;");
    jobject package_manager  = env->CallObjectMethod(thiz,methodID_func);
    jclass pm_clazz = env->GetObjectClass(package_manager);

    //packageManager.getPackageInfo()
    jmethodID methodId_pm = env->GetMethodID(pm_clazz,"getPackageInfo", "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");

    //context.getPackageName()
    jmethodID methodID_packagename = env->GetMethodID(native_context,"getPackageName","()Ljava/lang/String;");
    jstring name_str = static_cast<jstring>(env->CallObjectMethod(thiz,methodID_packagename));
    jobject package_info = env->CallObjectMethod(package_manager,methodId_pm,name_str,64);
    jclass pi_clazz = env->GetObjectClass(package_info);

    //packageInfo.signatures
    jfieldID fieldID_signatures = env->GetFieldID(pi_clazz,"signatures","[Landroid/content/pm/Signature;");
    jobject signatur = env->GetObjectField(package_info,fieldID_signatures);
    jobjectArray  signatures = reinterpret_cast<jobjectArray>(signatur);

    //signatures[0]
    jobject signature = env->GetObjectArrayElement(signatures,0);
    jclass s_clazz = env->GetObjectClass(signature);

    //signatures[0].toCharString()
    jmethodID methodId_ts = env->GetMethodID(s_clazz,"toCharsString","()Ljava/lang/String;");
    jobject ts = env->CallObjectMethod(signature,methodId_ts);
    jstring  final=reinterpret_cast<jstring>(ts);
    return final;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_denzo_in_1live_task_DecoderTask_mali(JNIEnv *env, jobject instance) {
    std::string ap = "_ZN10__caaabiv119__start_handles";
    return env->NewStringUTF(ap.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_denzo_in_1live_task_DecoderTask_toAo(JNIEnv *env, jobject instance) {
    std::string az = "O+poSr5nJhAy98ClpdgeZMTXklYHX0pS4ayn1UGJ42zRK/eKp+lt+QAwew2dUEPuF9kZwZjG1NWURnEfrZNDPsSv4fuDgvokWmIWx05mcYBKuG9Go7tkXbC4cg7cEZNAUUuoynx61W4w/cr4fyIOYg==";
    return env->NewStringUTF(az.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_denzo_in_1live_Model_Auth_ApiTokenModel_sec(JNIEnv *env, jobject thiz) {
    std::string ap = "PvmPdnGJkMK6d9qfQUBA720pidraV4/07MxN4fFHDSPWxHZsS2Z+t3FZnL+MmjRALDbfgSju9IenwXuy0rDKhg==";
    return env->NewStringUTF(ap.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_denzo_in_1live_Model_Auth_ApiTokenModel_salt(JNIEnv *env, jobject thiz) {
    std::string ap = "gV5Vnn8GahHiFHASXz+Ihh4SjwK1ecrU147TGDKL2nc=";
    return env->NewStringUTF(ap.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_denzo_in_1live_Model_Auth_ApiTokenModel_varSZION(JNIEnv *env, jobject thiz) {
    std::string ap = "ysUZRvNen3MX0Q/kPwwjkDaE/Rg2LUFIsbtL5JSdw68=";
    return env->NewStringUTF(ap.c_str());
}