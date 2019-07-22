package aviasantufei;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by S.KENEL on 02.10.2018.
 */

public class App {
    public static void main(String[] args) {
        String  url = "https://mobile.enpf.kz/ISyncChannelHttpService2/ENPF_KAZPOST";
        String res = "<ns2:syncSendMessageRequest xmlns:ns2=\"http://bip.bee.kz/SyncChannel/v10/Types/Request\">\n" +
                "    <requestInfo>\n" +
                "        <messageId>1b93f45d-0a34-4ba0-b55f-85f11ff70226</messageId>\n" +
                "        <serviceId>getAllReference</serviceId>\n" +
                "        <messageDate>2018-10-03T09:53:03.328+06:00</messageDate>\n" +
                "    </requestInfo>\n" +
                "    <requestData>\n" +
                "        <data xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"></data>\n" +
                "    </requestData>\n" +
                "</ns2:syncSendMessageRequest>";

        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            HttpPost request=new HttpPost(url);
            request.addHeader("SOAPAction","");
            request.addHeader("Content-Type","text/xml;charset=UTF-8");
            request.addHeader("Accept-Encoding","gzip,deflate");
            request.setEntity(new StringEntity(res));
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity=response.getEntity();
            System.out.println("Response "+EntityUtils.toString(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static  SSLSocketFactory buildSSLSocketFactory() {
        TrustStrategy ts = new TrustStrategy() {

            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true; // heck yea!
            }
        };
        SSLSocketFactory sf = null;
        try {
			/* build socket factory with hostname verification turned off. */
            sf = new SSLSocketFactory(ts, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            //log.error("Failed to initialize SSL handling.", e);
        }
        return sf;
    }
}
