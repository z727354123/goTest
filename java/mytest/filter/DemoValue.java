package mytest.filter;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.RequestFilterValve;
import org.apache.juli.logging.Log;

import javax.servlet.ServletException;
import java.io.IOException;

public class DemoValue extends RequestFilterValve {
    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {

        System.out.println("-------------------另外で一条，华丽の分割线----------------------" + request.getRequestURI()
        );
        getNext().invoke(request, response);

    }

    @Override
    protected Log getLog() {
        System.out.println("-------------------华丽分割线----------------------");
        return null;
    }
}
