/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.gallery3d.common;

import android.app.Activity;
import android.os.Build;
import android.provider.MediaStore.MediaColumns;
import android.view.View;

public class ApiHelper {
    public static interface VERSION_CODES {
        // These value are copied from Build.VERSION_CODES
        public static final int GINGERBREAD_MR1 = 10;
        public static final int HONEYCOMB = 11;
        public static final int HONEYCOMB_MR1 = 12;
        public static final int HONEYCOMB_MR2 = 13;
        public static final int ICE_CREAM_SANDWICH = 14;
        public static final int ICE_CREAM_SANDWICH_MR1 = 15;
        public static final int JELLY_BEAN = 16;
    }

    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE =
            hasField(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE");

    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_HIDE_NAVIGATION =
            hasField(View.class, "SYSTEM_UI_FLAG_HIDE_NAVIGATION");

    public static final boolean HAS_MEDIA_COLUMNS_WIDTH_AND_HEIGHT =
            hasField(MediaColumns.class, "WIDTH");

    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_REGION_DECODER =
            Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_FACTORY =
            Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_SET_BEAM_PUSH_URIS =
            Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_SET_DEFALT_BUFFER_SIZE = hasMethod(
            "android.graphics.SurfaceTexture", "setDefaultBufferSize",
            int.class, int.class);

    public static final boolean HAS_MTP =
            Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;

    public static final boolean HAS_AUTO_FOCUS_MOVE_CALLBACK =
            Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;

    public static final boolean HAS_ACTIVITY_INVALIDATE_OPTIONS_MENU =
            hasMethod(Activity.class, "invalidateOptionsMenu");

    public static final boolean HAS_REMOTE_VIEWS_SERVICE =
            Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;

    private static boolean hasField(Class<?> klass, String fieldName) {
        try {
            klass.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static boolean hasMethod(String className, String methodName,
            Class<?>... parameterTypes) {
        try {
            Class<?> klass = Class.forName(className);
            klass.getDeclaredMethod(methodName, parameterTypes);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean hasMethod(
            Class<?> klass, String methodName, Class<?> ... paramTypes) {
        try {
            klass.getDeclaredMethod(methodName, paramTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
