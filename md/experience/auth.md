### facebook 第三方登录
1. 前端集成facebook sdk，授权登录获取accessToken
2. 后台通过facebook api校验token是否有效，如果有效，获取userId
3. facebook提供graph API，通过userId获取用户信息
4. 参考文档：
    - https://cloud.tencent.com/developer/article/1620409
    - https://developers.facebook.com/docs/graph-api/using-graph-api
5. 参考代码
```java
class FacebookAuthExample{
    // 校验token是否有效
    public static JSONObject fbAuth(String inputToken) {
            String urlString = String.format("https://graph.facebook.com/debug_token?access_token=%s|%s&input_token=%s",
                    fbAppId, fbAppSecret, inputToken);
            System.out.println(urlString);
            String s = HttpUtil.get(urlString);
            // 返回值格式示例如下,is_valid标识token是否可用，user_id是获取用户信息的唯一标识
            //        {
            //            "data": {
            //            "app_id": "2631546830481875",
            //                    "type": "USER",
            //                    "application": "App Name",
            //                    "data_access_expires_at": 1638064071,
            //                    "expires_at": 1635472071,
            //                    "is_valid": true,
            //                    "issued_at": 1630288071,
            //                    "metadata": {
            //                "auth_type": "rerequest",
            //                        "sso": "ios"
            //            },
            //            "scopes": [
            //            "user_friends",
            //                    "email",
            //                    "openid",
            //                    "public_profile"
            //      ],
            //            "user_id": "112940651096066"
            //        }
            //        }
            return FastJsonUtil.object(s);
        }
}
```



### google 第三方登录
1. 前端 集成google sdk，授权登录获取idToken
2. 后台集成google sdk，校验token并获取用户信息
3. 参考文档：
    - https://developers.google.com/identity/sign-in/ios/backend-auth
4. 参考代码
```java
class GoogleAuthExample{
    // 校验token是否有效
    public static JSONObject google(String idTokenString) throws Exception {
            try {
                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new ApacheHttpTransport(), new GsonFactory())
                        // Specify the googleClientId of the app that accesses the backend:
                        .setAudience(Collections.singletonList(googleClientId))
                        // Or, if multiple clients access the backend:
                        //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                        .build();
                // (Receive idTokenString by HTTPS POST)
                GoogleIdToken idToken = verifier.verify(idTokenString);
                if (idToken != null) {
                    Payload payload = idToken.getPayload();
                    // Print user identifier
                    String userId = payload.getSubject();
                    System.out.println("User ID: " + userId);
                    // Get profile information from payload
                    String email = payload.getEmail();
                    boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                    String name = (String) payload.get("name");
                    String pictureUrl = (String) payload.get("picture");
                    String locale = (String) payload.get("locale");
                    String familyName = (String) payload.get("family_name");
                    String givenName = (String) payload.get("given_name");
                    // todo 业务逻辑处理
                    // ...
                    return JSONObject.parseObject(JSONObject.toJSONString(payload));
    
                } else {
                    System.out.println("Invalid ID token.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}
```



### apple 第三方登录
1. 前端集成apple sdk，获取jwt token
2. 后台解密token，并获取apple公钥进行解密验证，如果验证通过，则token有效，用户信息可用
3. 参考文档：
    - https://developer.apple.com/documentation/sign_in_with_apple
    - https://developer.apple.com/documentation/sign_in_with_apple/generate_and_validate_tokens
    - https://www.jianshu.com/p/22812febd858?utm_campaign=hugo&utm_content=note&utm_medium=writer_share&utm_source=weibo
    - https://blog.csdn.net/wpf199402076118/article/details/99677412
4. 参考代码
```java
class AppleAuthExample{
        /**
         * 校验token是否有效，并解密apple个人信息
         *
         * @param identityToken APP获取的identityToken
         * @return 解密参数：失败返回null  sub就是用户id,用户昵称需要前端传过来
         */
        public static JSONObject appleAuth(String identityToken) {
            try {
                String[] identityTokens = identityToken.split("\\.");
                String str = new String(Base64Utils.decodeFromString(identityTokens[1]), "UTF-8");
                JSONObject data = JSONObject.parseObject(str);
                String aud = (String) data.get("aud");
                String sub = (String) data.get("sub");
                // data示例，sub是该用户在应用'aud'的唯一id
                // {"aud":"com.coininn.yykik","sub":"000388.d4a91b99d41c401a80c13cbf95ba89bd.0618","c_hash":"Lq1TEX5Rq-NpEqYi_2FYww","nonce_supported":true,"email_verified":"true","auth_time":1630305842,"iss":"https://appleid.apple.com","is_private_email":"true","exp":1630392242,"iat":1630305842,"email":"c4qdm2q2y2@privaterelay.appleid.com"}
                if (verify(identityToken, aud, sub)) {
                    // 校验通过才使用
                    return data;
                }
            } catch (Exception e) {
                log.info("verify(*) error ", e);
            }
            return null;
        }
    
        /**
         * 验证
         *
         * @param identityToken APP获取的identityToken
         * @param aud           您在您的Apple Developer帐户中的client_id
         * @param sub           用户的唯一标识符对应APP获取到的：user
         * @return true/false
         */
        private static boolean verify(String identityToken, String aud, String sub) {
            try {
                PublicKey publicKey = getPublicKey();
                if (publicKey == null) {
                    return false;
                }
                JwtParser jwtParser = Jwts.parser().setSigningKey(publicKey);
                jwtParser.requireIssuer("https://appleid.apple.com");
                jwtParser.requireAudience(aud);
                jwtParser.requireSubject(sub);
                Jws<Claims> claim = jwtParser.parseClaimsJws(identityToken);
                System.out.println(JSONObject.toJSONString(claim.getBody()));
                if (claim != null && claim.getBody().containsKey("auth_time")) {
                    return true;
                }
            } catch (Exception e) {
                log.info("verify(*,*,*) error ", e);
            }
            return false;
        }
    
        /**
         * 获取apple公钥
         *
         * @return
         */
        private static PublicKey getPublicKey() {
            try {
                String url = "https://appleid.apple.com/auth/keys";
                String s = HttpUtil.get(url);
                JSONObject data = FastJsonUtil.object(s);
                JSONArray jsonArray = data.getJSONArray("keys");
                String n = jsonArray.getJSONObject(0).getString("n");
                String e = jsonArray.getJSONObject(0).getString("e");
                BigInteger modulus = new BigInteger(1, Base64.decodeBase64(n));
                BigInteger publicExponent = new BigInteger(1, Base64.decodeBase64(e));
                RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                return kf.generatePublic(spec);
            } catch (Exception e) {
                log.info("getPublicKey error ", e);
            }
            return null;
        }
}
```