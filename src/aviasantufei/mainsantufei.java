package aviasantufei;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/")
public class mainsantufei {
    private static final String AVIA_MAIN = "https://test.santufei.com"; //443 default https || http default 80
//    private static final String AVIA_MAIN_https = "https://test.santufei.com";
    private static final String CITY_LIST = AVIA_MAIN + "/api/v1/location/cities/";
    private static final String COUNTRY_LIST = AVIA_MAIN + "/api/v1/location/countries/";
    private static final String TICKETS_SEARCH = AVIA_MAIN + "/api/v1/tickets/search/";
    private static final String TICKETS_PRICES = AVIA_MAIN + "/api/v1/tickets/prices/";
    private static final String TICKETS_BOOK = AVIA_MAIN + "/api/v1/tickets/book/";
    private static final String TICKETS_PURCHASE = AVIA_MAIN + "/api/v1/tickets/purchase/";
    private static final String TICKETS_PROFILE_BOOK = AVIA_MAIN + "/api/v1/tickets/profile/bookings/";
    private static final String TICKETS_BOOKING = AVIA_MAIN + "/api/v1/tickets/bookings/";
    private static final String TICKETS_GET_RULES = AVIA_MAIN + "/api/v1/tickets/get_rules/";
    private static final String TICKETS_REFUND = AVIA_MAIN + "/api/v1/tickets/refund/";
    private static final String TICKETS_REFUND_GET = AVIA_MAIN + "/api/v1/tickets/refund/";
    private static final String TICKETS_REFUND_BY_ID = AVIA_MAIN + "/api/v1/tickets/refund/";
    private static final String TICKETS_REFUND_BY_ID_CANCEL = AVIA_MAIN + "/api/v1/tickets/refund/";
    private static final String TICKETS_REFUND_BY_ID_CANCEL_CONFIRM = AVIA_MAIN + "/api/v1/tickets/refund/";
    private static final String TICKETS_GET_BRANDED_FARES = AVIA_MAIN + "/api/v1/tickets/get_branded_fares/";

    private static final String token = "39c6c2440fcd0d4ece703a91f63d7540fbe71f74";

    private static final String helloWorld = "Hello World";

    @GET
    @Path("/cities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CityList(@QueryParam("page") String pages, InputStream incomingData) {

        System.out.println(helloWorld);

        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = CITY_LIST;
            if (pages != null) {
                url += "?pages=" + pages;
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }


    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CountryList(InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = COUNTRY_LIST;
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @GET
    @Path("/ticketsSearch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response SearchReis(@QueryParam("segmentA") String segmentA, @QueryParam("segmentB") String segmentB,
                               @QueryParam("ADT") String ADT, @QueryParam("CHD") String CHD,
                               @QueryParam("INF") String INF, @QueryParam("service_type") String service_type, InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_SEARCH;
            if (segmentA != null) {
                url += "?segment=" + segmentA;
            }
            if (segmentB != null) {
                url += "&segment=" + segmentB;
            }
            if (ADT != null) {
                url += "&ADT=" + ADT;
            }
            if (CHD != null) {
                url += "&CHD=" + CHD;
            }
            if (INF != null) {
                url += "&INF=" + INF;
            }
            if (service_type != null) {
                url += "&service_type=" + service_type;
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @GET
    @Path("/ticketsPrices")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsPrice(@QueryParam("segmentA") String segmentA, @QueryParam("segmentB") String segmentB,
                                 @QueryParam("ADT") String ADT, @QueryParam("CHD") String CHD,
                                 @QueryParam("INF") String INF, @QueryParam("service_type") String service_type, InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_PRICES;
            if (segmentA != null) {
                url += "?segment=" + segmentA;
            }
            if (segmentB != null) {
                url += "&segment=" + segmentB;
            }
            if (ADT != null) {
                url += "&ADT=" + ADT;
            }
            if (CHD != null) {
                url += "&CHD=" + CHD;
            }
            if (INF != null) {
                url += "&INF=" + INF;
            }
            if (service_type != null) {
                url += "&service_type=" + service_type;
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @POST
    @Path("/ticketsBook")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsBook(InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
            String line = null;
            String data = "";
            while ((line = in.readLine()) != null) {
                data += line;
            }
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_BOOK;
            System.out.println(url);
            HttpPost request = new HttpPost(url);
            StringEntity entityRequest = new StringEntity(data);
//			request.addHeader("Content-Type","application/json; charset=utf-8");
//			request.addHeader("Accept","application/json");
//			request.addHeader("access-token", token);
//			request.addHeader("Authorization","Token " + token);

            request.setEntity(entityRequest);
            request.addHeader("Content-Type", "application/json; charset=utf-8");

            if (isJSONValid(data) || data.equals("null")) {
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                responseCode = response.getStatusLine().getStatusCode() + "";
                System.out.println(responseCode);
                settokenresult = EntityUtils.toString(entity);
                client.close();
                response.close();
            } else {
                settokenresult = "{\"Message\":\"Json is not valid\"}";
            }
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @POST
    @Path("/ticketsPurchase")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsPurchase(InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
            String line = null;
            String data = "";
            while ((line = in.readLine()) != null) {
                data += line;
            }
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_PURCHASE;
            System.out.println(url);
            HttpPost request = new HttpPost(url);
            StringEntity entityRequest = new StringEntity(data);

            request.setEntity(entityRequest);
            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            if (isJSONValid(data) || data.equals("null")) {
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                responseCode = response.getStatusLine().getStatusCode() + "";
                settokenresult = EntityUtils.toString(entity);
                client.close();
                response.close();
            } else {
                settokenresult = "{\"Message\":\"Json is not valid\"}";
            }
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @POST
    @Path("/ticketsProfileBook")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsProfileBook(InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
            String line = null;
            String data = "";
            while ((line = in.readLine()) != null) {
                data += line;
            }
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_PROFILE_BOOK;
            System.out.println(url);
            HttpPost request = new HttpPost(url);
            StringEntity entityRequest = new StringEntity(data);

            request.setEntity(entityRequest);
            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            if (isJSONValid(data) || data.equals("null")) {
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                responseCode = response.getStatusLine().getStatusCode() + "";
                settokenresult = EntityUtils.toString(entity);
                client.close();
                response.close();
            } else {
                settokenresult = "{\"Message\":\"Json is not valid\"}";
            }
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @GET
        @Path("/ticketsProfileBookGet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsProfileBookGet(@QueryParam("reference_id") String referenceID, InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_PROFILE_BOOK;
            if (referenceID != null) {
                url += referenceID + "/";
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);
            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @GET
    @Path("/ticketsBooking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsBooking(@QueryParam("reference_id") String referenceID, InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_BOOKING;
            if (referenceID != null) {
                url += referenceID + "/";
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @GET
        @Path("/ticketsGetRules")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsGetRules(@QueryParam("uid") String uid, @QueryParam("sequence_number") String sequenceNumber,
                                    @QueryParam("combination") String combination, @QueryParam("gds") String gds,
                                    InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_GET_RULES;
            if (uid != null) {
                url += uid + "/";
            }
            if (sequenceNumber != null) {
                url += sequenceNumber + "/";
            }
            if (combination != null) {
                url += combination + "/";
            }
            if (gds != null) {
                url += gds + "/";
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @POST
            @Path("/ticketsRefund")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsRefund(InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
            String line = null;
            String data = "";
            while ((line = in.readLine()) != null) {
                data += line;
            }
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_REFUND;
            System.out.println(url);
            HttpPost request = new HttpPost(url);
            StringEntity entityRequest = new StringEntity(data);

            request.setEntity(entityRequest);
            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            if (isJSONValid(data) || data.equals("null")) {
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                responseCode = response.getStatusLine().getStatusCode() + "";
                settokenresult = EntityUtils.toString(entity);
                client.close();
                response.close();
            } else {
                settokenresult = "{\"Message\":\"Json is not valid\"}";
            }
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @GET
    @Path("/ticketsRefundGet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsRefundGet(InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_REFUND_GET;
            System.out.println(url);
            HttpGet request = new HttpGet(url);

            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @GET
    @Path("/ticketsRefundById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsRefundById(@QueryParam("refundId") String refundId, InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_REFUND_BY_ID;
            if (refundId != null) {
                url += refundId;
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);

            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();

        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }


    @PUT
    @Path("/ticketsRefundByIdCancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsRefundByIdCancel(@QueryParam("refundId") String refundId, InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_REFUND_BY_ID_CANCEL;
            if (refundId != null) {
                url += refundId + "/cancel/";
            }
            System.out.println(url);
            HttpPut request = new HttpPut(url);

            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseCode = response.getStatusLine().getStatusCode() + "";
            settokenresult = EntityUtils.toString(entity);
            client.close();
            response.close();

        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @POST
    @Path("/ticketsRefundByIdConfirm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response TicketsRefundByIdConfirm(@QueryParam("refundId") String refundId, InputStream incomingData) {
        String settokenresult = "";
        String responseCode = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
            String line = null;
            String data = "";
            while ((line = in.readLine()) != null) {
                data += line;
            }
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_REFUND_BY_ID_CANCEL_CONFIRM;
            if (refundId != null) {
                url += refundId + "/confirm/";
            }
            System.out.println(url);
            HttpPost request = new HttpPost(url);
            StringEntity entityRequest = new StringEntity(data);

            request.setEntity(entityRequest);
            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            if (isJSONValid(data) || data.equals("null")) {
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                responseCode = response.getStatusLine().getStatusCode() + "";
                settokenresult = EntityUtils.toString(entity);
                client.close();
                response.close();
            } else {
                settokenresult = "{\"Message\":\"Json is not valid\"}";
            }
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }
        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }

    @POST
    @Path("/getBrandedFares")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetBrandedFares(InputStream incomingData){
        String settokenresult = "";
        String responseCode = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
            String line = null;
            String data = "";
            while ((line = in.readLine()) != null){
                data+=line;
            }
            CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(buildSSLSocketFactory()).build();
            String url = TICKETS_GET_BRANDED_FARES;
            System.out.println(url);
            HttpPost request = new HttpPost(url);
            StringEntity entityRequest = new StringEntity(data);
            request.setEntity(entityRequest);
            request.addHeader("Content-Type", "application/json; charset=utf-8");
            request.addHeader("Accept", "application/json");
            request.addHeader("access-token", token);
            request.addHeader("Authorization", "Token " + token);
            if (isJSONValid(data) || data.equals("null")) {
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                responseCode = response.getStatusLine().getStatusCode() + "";
                settokenresult = EntityUtils.toString(entity);
                client.close();
                response.close();
            } else {
                settokenresult = "{\"Message\":\"Json is not valid\"}";
            }
        } catch (Exception e) {
            responseCode = "1";
            settokenresult = "{\"Message\":\"" + e.getMessage() + "\"}";
        }

        return Response.status(200).entity(settokenresult).header("ResponseCode", responseCode).build();
    }


    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                if (test.length() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private SSLSocketFactory buildSSLSocketFactory() {
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
