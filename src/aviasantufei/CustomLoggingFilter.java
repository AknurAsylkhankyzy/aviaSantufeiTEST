package aviasantufei;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.ReaderWriter;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class CustomLoggingFilter extends LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
	private static Logger logger = Logger.getLogger(mainsantufei.class.getName());

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMethod : ").append(request.getMethod().toUpperCase())
                                .append("\nPath : ").append(request.getRequestUri().getPath())
                                .append("\nHeaders : ").append(request.getRequestHeaders());
        try {
            sb.append("\nEntity : ").append(getEntityBody(request));
        } catch (Exception e) {
            logger.log(Level.WARN, "\nEntity : ERROR in converting entity to JSON for logging. " + e.getMessage());
        }
        logger.log(Level.WARN, "\nHTTP REQUEST" + sb.toString());
        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        sb.append("\nHeaders : ").append(response.getHttpHeaders());
        sb.append("\nStatus : ").append(response.getStatus());
        try {
            sb.append("\nEntity : ").append(objectMapper.writeValueAsString(response.getEntity()));
        } catch (Exception e) {
            logger.log(Level.WARN, "\nEntity : ERROR in converting entity to JSON for logging. " + e.getMessage());
        }
        logger.log(Level.WARN, "\nHTTP RESPONSE" + sb.toString());
        return response;
    }

    private String getEntityBody(ContainerRequest requestContext) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityInputStream();
         
        final StringBuilder b = new StringBuilder();
        try
        {
            ReaderWriter.writeTo(in, out);
            byte[] requestEntity = out.toByteArray();
            if (requestEntity.length == 0)
            {
                b.append("\n");
            }
            else
            {
                b.append(new String(requestEntity)).append("\n");
            }
            requestContext.setEntityInputStream( new ByteArrayInputStream(requestEntity) );
 
        } catch (IOException ex) {
            //Handle logging error
        	return ex.getMessage();
        }
        return b.toString();
    }
}
