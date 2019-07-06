package com.meida.shaokaoshop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.meida.shaokaoshop.R;

/**
 * 项目名称：ShaoKaoShop
 * 创建人：刘
 * 创建时间：2019-04-26 18:36
 * 功能描述：
 */
public class Settings {
    private static SharedPreferences sSharedPreferences;

    public static SharedPreferences getPreferences(Context context) {
        if (sSharedPreferences == null) {
            sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        }

        return sSharedPreferences;
    }
    public static String getPayPalIntentType(Context context) {
        return getPreferences(context).getString("paypal_intent_type", "Authorize");
    }

    public static String getPayPalDisplayName(Context context) {
        return getPreferences(context).getString("paypal_display_name", null);
    }

    public static String getPayPalLandingPageType(Context context) {
        return getPreferences(context).getString("paypal_landing_page_type", "None");
    }

    public static boolean isPayPalUseractionCommitEnabled(Context context) {
        return getPreferences(context).getBoolean("paypal_useraction_commit", false);
    }

    public static boolean isPayPalCreditOffered(Context context) {
        return getPreferences(context).getBoolean("paypal_credit_offered", false);
    }

    public static boolean isPayPalSignatureVerificationDisabled(Context context) {
        return getPreferences(context).getBoolean("paypal_disable_signature_verification", true);
    }

    public static boolean usePayPalAddressOverride(Context context) {
        return getPreferences(context).getBoolean("paypal_address_override", true);
    }
}
