package com.meida.shaokaoshop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：ShaoKaoShop
 * 创建人：刘
 * 创建时间：2019-05-14 18:02
 * 功能描述：
 */
public class PayPalUtils {
    private static final String TAG = "PayPalHelper";
    //配置何种支付环境，一般沙盒，正式
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_PRODUCTION;

    // note that these credentials will differ between live & sandbox environments.
//你所注册的APP Id
    public  static String CONFIG_CLIENT_ID = "ARR0Nj96aui17h-QPKn1QqqV8ihg3h-G_kxjEFB1WZvreiqQI5Gb3bhpiqomXvndBZk_dPiEloOrNwJX";

    private static final int REQUEST_CODE_PROFILE_SHARING = 3;

    private  PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
    private static PayPalUtils payPalHelper;

    private PayPalUtils() {
    }

    public static PayPalUtils getInstance() {
        if (payPalHelper == null) {
            synchronized (PayPalUtils.class) {
                payPalHelper = new PayPalUtils();
            }
        }
        return payPalHelper;
    }

    /**
     * 启动PayPal服务
     *
     * @param context
     */
    public void startPayPalService(Context context) {
        Intent intent = new Intent(context, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        context.startService(intent);
    }

    /**
     * 停止PayPal服务  sdfsdfsdssaaass
     *
     * @param context
     */
    public static void stopPayPalService(Context context) {
        context.stopService(new Intent(context, PayPalService.class));
    }

    /**
     * 开始执行支付操作
     *
     * @param context
     */
    public void doPayPalPay(Context context, List<PayPalItem> productsInCart,String custom) {

        PayPalPayment thingToBuy = getStuffToBuy(PayPalPayment.PAYMENT_INTENT_SALE, productsInCart,custom);

        Intent intent = new Intent(context, PaymentActivity.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_PROFILE_SHARING);
    }

    /*
     * This method shows use of optional payment details and item list.
     *
     * 直接给PP创建支付的信息，支付对象实体信息
     */

    private PayPalPayment getStuffToBuy(String paymentIntent, List<PayPalItem> productsInCart,String custom) {
        //--- include an item list, payment amount details
        //具体的产品信息列表
        PayPalItem[] items = new PayPalItem[productsInCart.size()];
        items = productsInCart.toArray(items);

        BigDecimal subtotal = PayPalItem.getItemTotal(items);
        BigDecimal shipping = new BigDecimal("0.0");
        BigDecimal tax = new BigDecimal("0.0");
        PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(shipping, subtotal, tax);
        BigDecimal amount = subtotal.add(shipping).add(tax);
        PayPalPayment payment = new PayPalPayment(amount, "USD", "sample item", paymentIntent);
        payment.items(items).paymentDetails(paymentDetails);
        //--- set other optional fields like invoice_number, custom field, and soft_descriptor
        payment.custom(custom);
        return payment;
    }


}