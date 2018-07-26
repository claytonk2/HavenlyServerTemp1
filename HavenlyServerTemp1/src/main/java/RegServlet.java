

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;
import Results.RegisterResult;
import Services.RegisterService;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 boolean success = false;
	        if (req.getMethod().toLowerCase().equals("post")) {

	            // Get the HTTP request headers
	            Map reqHeaders = getHeadersInfo(req);

	            // Check to see if an "Authorization" header is present
	            String password = null;
	            if (reqHeaders.containsKey("password")) {
	                password = (String) reqHeaders.get("password");
	            }
	            String username = null;
	            if (reqHeaders.containsKey("username")) {
	                username = (String) reqHeaders.get("username");
	            }
	            String firstName = null;
	            if (reqHeaders.containsKey("firstname")) {
	                firstName = (String) reqHeaders.get("firstname");
	            }
	            String lastName = null;
	            if (reqHeaders.containsKey("lastname")) {
	                lastName = (String) reqHeaders.get("lastname");
	            }
	            System.out.println(username + " registered with " + password);

	            RegisterService service = new RegisterService();
	            RegisterResult result = service.register(new User(firstName, lastName, password, username));
	            String respData = Serializer.encode(result);


	            // Start sending the HTTP response to the client, starting with
	            // the status code and any defined headers.
	            resp.setStatus(HttpURLConnection.HTTP_OK);
	            resp.getWriter().write(respData);
	            resp.getWriter().flush();
	            resp.getWriter().close();

	            //resp.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

	            // Now that the status code and headers have been sent to the client,
	            // next we send the JSON Locations in the HTTP response body.

	            // Get the response body output stream.
	            //OutputStream respBody = exchange.getResponseBody();
	            // Write the JSON string to the output stream.
	            //Serializer.writeString(respData, respBody);
	            // Close the output stream.  This is how Java knows we are done
	            // sending Locations and the response is complete/
	            //respBody.close();

	            success = true;
	        }


	        if (!success) {
	            //exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
	            resp.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
	            String respData = "{\n" +
	                    "\"message\": \"Error in logging in\"\n" +
	                    "}\n";
	            //OutputStream respBody = exchange.getResponseBody();
	            // Write the JSON string to the output stream.
	            resp.getWriter().write(respData);
	            resp.getWriter().flush();
	            resp.getWriter().close();
	        }
	}

}
