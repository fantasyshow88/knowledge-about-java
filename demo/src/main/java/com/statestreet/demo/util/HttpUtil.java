package com.statestreet.demo.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpUtil {
    public static final ContentType TEXT_PLAIN = ContentType.create("text/plain", StandardCharsets.UTF_8);

    /**
     * HttpClient 连接池
     */
    private static PoolingHttpClientConnectionManager cm = null;

    static {
        // 初始化连接池，可用于请求HTTP/HTTPS（信任所有证书）
        cm = new PoolingHttpClientConnectionManager(getRegistry());
        // 整个连接池最大连接数
        cm.setMaxTotal(200);
        // 每路由最大连接数，默认值是2
        cm.setDefaultMaxPerRoute(5);
    }

    /**
     * 发送 HTTP GET请求
     * <p>不带请求参数和请求头</p>
     *
     * @param url 地址
     * @return
     * @throws Exception
     */
    public static String httpGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);

        return doHttp(httpGet);
    }
    //curl -k -H "X-ApiKeys: accessKey=1c6f09dd4f40a7bf1daab2b4110493387ebf85231041e39d397d17972397a087; secretKey=e4b1d7bb4c6fab6e667a2b63ee3508bf0bbdca1b104e073de90f8647fbdca87e" https://192.168.3.53:8834/scans
    public static void main(String[] args) throws Exception {
/*        Map<String, Object> header = new HashMap();
        header.put("X-ApiKeys", "accessKey=1c6f09dd4f40a7bf1daab2b4110493387ebf85231041e39d397d17972397a087; secretKey=e4b1d7bb4c6fab6e667a2b63ee3508bf0bbdca1b104e073de90f8647fbdca87e");
        String result = httpGetWithHeader("https://192.168.3.53:8834/scans", header);
        System.out.println(result);*/

        String online = "f-k-de-ie.guahao.cn";
        InetAddress address = InetAddress.getByName(online);
        System.out.println(address.getHostAddress());
    }

    public static String httpGetWithHeader(String url, Map<String, Object> headers) throws Exception {
        // 转换请求参数

        // 装载请求地址和参数
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        HttpGet httpGet = new HttpGet(ub.build());
        // 设置请求头
        for (Map.Entry<String, Object> param : headers.entrySet())
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));

        return doHttp(httpGet);
    }
    /**
     * 发送 HTTP GET请求
     * <p>带请求参数，不带请求头</p>
     *
     * @param url    地址
     * @param params 参数
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static String httpGet(String url, Map<String, Object> params) throws Exception {
        // 转换请求参数
        List<NameValuePair> pairs = covertParams2NVPS(params);

        // 装载请求地址和参数
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());

        return doHttp(httpGet);
    }

    /**
     * 发送 HTTP GET请求
     * <p>带请求参数和请求头</p>
     *
     * @param url     地址
     * @param headers 请求头
     * @param params  参数
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static String httpGet(String url, Map<String, Object> headers, Map<String, Object> params) throws Exception {
        // 转换请求参数
        List<NameValuePair> pairs = covertParams2NVPS(params);

        // 装载请求地址和参数
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        // 设置请求头
        for (Map.Entry<String, Object> param : headers.entrySet())
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));

        return doHttp(httpGet);
    }

    /**
     * 发送 HTTP POST请求
     * <p>不带请求参数和请求头</p>
     *
     * @param url 地址
     * @return
     * @throws Exception
     */
    public static String httpPost(String url) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        return doHttp(httpPost);
    }

    /**
     * 发送 HTTP POST请求
     * <p>带请求参数，不带请求头</p>
     *
     * @param url    地址
     * @param params 参数
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, Map<String, Object> params) throws Exception {
        // 转换请求参数
        List<NameValuePair> pairs = covertParams2NVPS(params);

        HttpPost httpPost = new HttpPost(url);
        // 设置请求参数
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8.name()));

        return doHttp(httpPost);
    }

    /**
     * 发送 HTTP POST请求
     * <p>带请求参数和请求头</p>
     *
     * @param url     地址
     * @param headers 请求头
     * @param params  参数
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, Map<String, Object> headers, Map<String, Object> params) throws Exception {
        // 转换请求参数
        List<NameValuePair> pairs = covertParams2NVPS(params);

        HttpPost httpPost = new HttpPost(url);
        // 设置请求参数
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8.name()));
        // 设置请求头
        for (Map.Entry<String, Object> param : headers.entrySet())
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));

        return doHttp(httpPost);
    }

    public static String httpPostWithHeader(String url, Map<String, Object> headers) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        for (Map.Entry<String, Object> param : headers.entrySet())
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));

        return doHttp(httpPost);
    }

    /**
     * 发送HTTP POST/FORM请求
     * <p>模拟表单文件上传，默认表单名称为“media”，无附加表单</p>
     *
     * @param url      请求地址
     * @param pathName 模拟表单文件全路径名
     * @return Http POST/FORM 请求结果
     * @throws Exception IO流异常
     */
    public static String httpPostForm(String url, String pathName) throws Exception {
        return httpPostForm(url, "media", pathName, null);
    }

    /**
     * 发送HTTP POST/FORM请求
     * <p>模拟表单文件上传，默认表单名称为“media”，无附加表单</p>
     *
     * @param url  请求地址
     * @param file 模拟表单文件
     * @return Http POST/FORM 请求结果
     * @throws Exception IO流异常
     */
    public static String httpPostForm(String url, File file) throws Exception {
        return httpPostForm(url, "media", file, null);
    }

    /**
     * 发送 HTTP POST/FORM请求
     * <p>模拟表单文件上传</p>
     *
     * @param url      请求地址
     * @param name     模拟表单名称
     * @param pathName 模拟表单文件路径
     * @param map      文件上传表单之外的附加表单，新增永久视频素材API需要使用该字段
     * @return Http POST/FORM 请求结果
     * @throws Exception IO流异常
     */
    public static String httpPostForm(String url, String name, String pathName, Map<String, Object> map) throws Exception {
        File file = new File(pathName);
        // 检查文件名是否合法，以及文件是否存在
        if (!file.isFile() || !file.exists())
            throw new Exception("HttpClient Post/Form模拟表单请求的文件名不合法或文件不存在");

        return httpPostForm(url, name, file, map);
    }


    /**
     * 发送 HTTP POST/FORM请求
     * <p>模拟表单文件上传</p>
     *
     * @param url  请求地址
     * @param name 模拟表单名称
     * @param file 模拟表单文件
     * @param map  文件上传表单之外的附加表单，新增永久视频素材API需要使用该字段
     * @return Http POST/FORM 请求结果
     * @throws Exception IO流异常
     */
    public static String httpPostForm(String url, String name, File file, Map<String, Object> map) throws Exception {
        // 初始化请求链接
        HttpPost httppost = new HttpPost(url);

        // 组装模拟表单
        FileBody body = new FileBody(file);
        // 组装HTTP表单请求参数
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().addPart(name, body);

        // 附加表单
        if (MapUtils.isNotEmpty(map)) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() == null)
                    continue;
                builder.addTextBody(entry.getKey(), entry.getValue().toString(), TEXT_PLAIN);
            }
        }

        // 构建HttpEntity
        HttpEntity entity = builder.build();
        // 设置请求参数
        httppost.setEntity(entity);

        return doHttp(httppost);
    }

    /**
     * 发送 HTTP POST请求，参数格式JSON
     * <p>请求参数是JSON格式，数据编码是UTF-8</p>
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String httpPostJson(String url, String param) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

        return doHttp(httpPost);
    }

    /**
     * 发送 HTTP POST请求，参数格式XML
     * <p>请求参数是XML格式，数据编码是UTF-8</p>
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String httpPostXml(String url, String param) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/xml; charset=UTF-8");
        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

        return doHttp(httpPost);
    }

    /**
     * 发送 HTTPS POST请求
     * <p>使用指定的证书文件及密码，不带请求头信息</p>
     *
     * @param url
     * @param param
     * @param path
     * @param password
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static String httpsPost(String url, String param, String path, String password) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

        return doHttps(httpPost, path, password);
    }

    /**
     * 发送 HTTPS POST请求
     * <p>使用指定的证书文件及密码，请求头为“application/xml;charset=UTF-8”</p>
     *
     * @param url
     * @param param
     * @param path
     * @param password
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static String httpsPostXml(String url, String param, String path, String password) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/xml; charset=UTF-8");
        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

        return doHttps(httpPost, path, password);
    }

    /**
     * 将Map键值对拼接成QueryString字符串，UTF-8编码
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String getQueryStr(Map<String, Object> params) throws Exception {
        return URLEncodedUtils.format(covertParams2NVPS(params), StandardCharsets.UTF_8.name());
    }

    /**
     * 将JavaBean属性拼接成QueryString字符串，UTF-8编码
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static String getQueryStr(Object bean) throws Exception {
        // 将JavaBean转换为Map
        Map<String, Object> map = PropertyUtils.describe(bean);

        // 移除Map中键为“class”和值为空的项
        map = Maps.filterEntries(map, new Predicate<Map.Entry<String, Object>>() {
            public boolean apply(Map.Entry<String, Object> input) {
                // 返回false表示排除该项
                return !(input.getKey().equals("class") || input.getValue() == null);
            }
        });

        return URLEncodedUtils.format(covertParams2NVPS(map), StandardCharsets.UTF_8.name());
    }

    /**
     * 将表单字符串转换为JavaBean
     *
     * @param queryStr 表单字符串
     * @param clazz    {@link Class}待转换的JavaBean
     * @return
     * @throws Exception
     */
    public static <T> T parseNVPS2Bean(String queryStr, Class<T> clazz) throws Exception {
        // 将“表单字符串”形式的字符串解析成NameValuePair集合
        List<NameValuePair> list = URLEncodedUtils.parse(queryStr, StandardCharsets.UTF_8);
        Map<String, String> rsMap = new HashMap<String, String>();

        // 将NameValuePair集合中的参数装载到Map中
        for (NameValuePair nvp : list)
            rsMap.put(nvp.getName(), nvp.getValue());

        // 实例化JavaBean对象
        T t = clazz.newInstance();
        // 将Map键值对装载到JavaBean中
        BeanUtils.populate(t, rsMap);

        return t;
    }

    /**
     * 转换请求参数，将Map键值对拼接成QueryString字符串
     *
     * @param params
     * @return
     */
    public static String covertParams2QueryStr(Map<String, Object> params) {
        List<NameValuePair> pairs = covertParams2NVPS(params);

        return URLEncodedUtils.format(pairs, StandardCharsets.UTF_8.name());
    }

    /**
     * 转换请求参数
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();

        for (Map.Entry<String, Object> param : params.entrySet())
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));

        return pairs;
    }

    /**
     * 发送 HTTP 请求
     *
     * @param request
     * @return
     * @throws Exception
     */
    private static String doHttp(HttpRequestBase request) throws Exception {
        // 通过连接池获取连接对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        return doRequest(httpClient, request);
    }

    /**
     * 发送 HTTPS 请求
     * <p>使用指定的证书文件及密码</p>
     *
     * @param request
     * @param path
     * @param password
     * @return
     * @throws Exception
     * @throws Exception
     */
    private static String doHttps(HttpRequestBase request, String path, String password) throws Exception {
        // 获取HTTPS SSL证书
        SSLConnectionSocketFactory csf = getSSLFactory(path, password);
        // 通过连接池获取连接对象
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

        return doRequest(httpClient, request);
    }

    /**
     * 获取HTTPS SSL连接工厂
     * <p>使用指定的证书文件及密码</p>
     *
     * @param path     证书全路径
     * @param password 证书密码
     * @return
     * @throws Exception
     * @throws Exception
     */
    private static SSLConnectionSocketFactory getSSLFactory(String path, String password) throws Exception {

        // 初始化证书，指定证书类型为“PKCS12”
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        // 读取指定路径的证书
        FileInputStream input = new FileInputStream(new File(path));

        try {
            // 装载读取到的证书，并指定证书密码
            keyStore.load(input, password.toCharArray());
        } finally {
            input.close();
        }

        // 获取HTTPS SSL证书连接上下文
        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();

        // 获取HTTPS连接工厂，指定TSL版本
        SSLConnectionSocketFactory sslCsf = new SSLConnectionSocketFactory(sslContext, new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        return sslCsf;
    }

    /**
     * 获取HTTPS SSL连接工厂
     * <p>跳过证书校验，即信任所有证书</p>
     *
     * @return
     * @throws Exception
     */
    private static SSLConnectionSocketFactory getSSLFactory() throws Exception {
        // 设置HTTPS SSL证书信息，跳过证书校验，即信任所有证书请求HTTPS
        SSLContextBuilder sslBuilder = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });

        // 获取HTTPS SSL证书连接上下文
        SSLContext sslContext = sslBuilder.build();

        // 获取HTTPS连接工厂
        SSLConnectionSocketFactory sslCsf = new SSLConnectionSocketFactory(sslContext, new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);

        return sslCsf;
    }

    /**
     * 获取 HTTPClient注册器
     *
     * @return
     * @throws Exception
     */
    private static Registry<ConnectionSocketFactory> getRegistry() {
        Registry<ConnectionSocketFactory> registry = null;

        try {
            registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", new PlainConnectionSocketFactory()).register("https", getSSLFactory()).build();
        } catch (Exception e) {
        }

        return registry;
    }

    /**
     * 处理Http/Https请求，并返回请求结果
     * <p>注：默认请求编码方式 UTF-8</p>
     *
     * @param httpClient
     * @param request
     * @return
     * @throws Exception
     */
    private static String doRequest(CloseableHttpClient httpClient, HttpRequestBase request) throws Exception {
        String result = null;
        CloseableHttpResponse response = null;

        try {
            // 获取请求结果
            response = httpClient.execute(request);
            System.out.println(response.getStatusLine());
            // 解析请求结果
            HttpEntity entity = response.getEntity();
            // 转换结果
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
            // 关闭IO流
            EntityUtils.consume(entity);
        } finally {
            if (null != response)
                response.close();
        }

        return result;
    }
}
