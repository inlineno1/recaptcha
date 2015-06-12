

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * Servlet implementation class ReCaptchaVerify
 */
@WebServlet("/ReCaptchaVerify")
public class ReCaptchaVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReCaptchaVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("start verifying ...");
		
		String secretKey = "6Le32AYTAAAAAFpUX0J8eXlcygMXu-rLb0nTrJ7H";
		String reCaptchaResponse = request.getParameter("response");
		
		System.out.println("reCAPTCHA response value : " + reCaptchaResponse);
		
		String verfiyURL = "https://www.google.com/recaptcha/api/siteverify";
		String checkURL = verfiyURL + "?secret=" + secretKey + "&response=" + reCaptchaResponse;
		
		System.out.println("check URL : " + checkURL);
				
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(verfiyURL);
 
		// add header
		post.setHeader("User-Agent", "Mozilla/5.0");
 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("secret", secretKey));
		urlParameters.add(new BasicNameValuePair("response", reCaptchaResponse));
 
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
 
		HttpResponse httpClientResponse = httpClient.execute(post);
		
		System.out.println("\nSending 'POST' request to URL : " + verfiyURL);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " +  httpClientResponse.getStatusLine().getStatusCode());
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(httpClientResponse.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		System.out.println(result.toString());
		
	}

}
