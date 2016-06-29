#include <jni.h>
#include "Killer.h"

JNIEXPORT void JNICALL Java_Killer_killMeNow  (JNIEnv *env, jobject obj) {
   int divider = 0;

   // This should cause a crash because of a divide by zero error.
   divider = 1 / divider;
   return;
}
