package com.gsonkeno.interview;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.util.Base64Utils;

public class RSA {
    /**
     * RSA算法
     */
    private static final String RSA_ALGORITHM = "RSA";
    /**
     * RSA签名算法
     */
    private static final String RSA_SIGNATURE_ALGORITHM = "SHA1withRSA";
    /**
     * RSA公钥key
     */
    public static final String RSA_PUBLIC_KEY = "RSA_PUBLIC_KEY";
    /**
     * RSA私钥key
     */
    public static final String RSA_PRIVATE_KEY = "RSA_PRIVATE_KEY";

    /**
     * 生成RSA公私钥对：密钥格式PKCS8
     *
     * @param keysize 密钥长度：1024, 2048
     * @return RSA公、私钥对Map<String, String>
     * @throws Exception
     */
    public static Map<String, String> generateKeyPair(Integer keysize) throws Exception {
        // 生成RSA Key
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(keysize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // Key --> Base64
        String publicKeyBase64 = Base64Utils.encodeToUrlSafeString(publicKey.getEncoded());
        String privateKeyBase64 = Base64Utils.encodeToUrlSafeString(privateKey.getEncoded());

        Map<String, String> ret = new HashMap<String, String>();
        ret.put(RSA_PUBLIC_KEY, publicKeyBase64);
        ret.put(RSA_PRIVATE_KEY, privateKeyBase64);
        return ret;
    }

    /**
     * RSA私钥签名：签名方式SHA1withRSA
     *
     * @param data             待签名byte[]
     * @param privateKeyBase64 私钥（Base64编码）
     * @return 签名byte[]
     * @throws Exception
     */
    public static byte[] sign(byte[] data, String privateKeyBase64) throws Exception {
        // Base64 --> Key
        byte[] bytes = Base64Utils.decodeFromUrlSafeString(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        // Sign
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * RSA公钥验签
     *
     * @param data            待签名字符串
     * @param publicKeyBase64 公钥（Base64编码）
     * @return 验签结果
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKeyBase64, String sign) throws Exception {
        // Base64 --> Key
        byte[] bytes = Base64Utils.decodeFromUrlSafeString(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        // verify
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(Base64Utils.decodeFromUrlSafeString(sign));
    }

    public static void main(String[] args) {
        try {
            // 0. 生成公、私钥对
            Map<String, String> keyPair = RSA.generateKeyPair(1024);
            System.out.println("私钥：" + keyPair.get(RSA_PRIVATE_KEY));
            System.out.println("公钥：" + keyPair.get(RSA_PUBLIC_KEY));

            // 将参数进行字典排序
            StringBuilder sb = new StringBuilder();
            Map<String, String> map = new TreeMap<String, String>();
            map.put("orderId", "10086");
            map.put("mobile", "18888888888");
            // 组成待签名字符串
            sb.delete(0, sb.length()); // 清空StringBuilder
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.toString().contains("="))
                    sb.append("&");
                sb.append(entry.getKey() + "=" + entry.getValue());
            }
            String signContent = sb.toString();
            System.out.println("待签名内容：" + signContent);

            // 1. 用私钥生成签名
            byte[] signBytes = RSA.sign(signContent.getBytes(), keyPair.get(RSA_PRIVATE_KEY));
            String sign = Base64Utils.encodeToUrlSafeString(signBytes);
            System.out.println("签名：" + sign);

            // 2. 用公钥进行验签
            boolean verify = RSA.verify(signContent.getBytes(), keyPair.get(RSA_PUBLIC_KEY), sign);
            System.out.println("验签结果：" + verify);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
