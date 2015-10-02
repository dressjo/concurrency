package util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ListIterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CIOUtil {

	public static String parseRequest(final Socket connection)
			throws IOException {

		BufferedInputStream buf = null;
		ByteArrayOutputStream out = null;;

		try {

			buf = new BufferedInputStream(connection.getInputStream());
			out = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = buf.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			connection.close();
		} catch (IOException e) {
			throw e;
		}
		return out.toString();
	}

	public static Document parseHtmlUrl(String url) {
		org.jsoup.nodes.Document doc;
		try {
			doc = org.jsoup.Jsoup.connect(url).get();
			System.out.println(doc.html());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return doc;
	}

	public static byte[] getBytesFromUrl(String requestedurl)
			throws IOException {

		URL url = null;
		try {
			url = new URL(requestedurl);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		InputStream in = null;

		BufferedInputStream buf = null;
		ByteArrayOutputStream out = null;

		try {
			
			in = url.openStream();
			buf = new BufferedInputStream(in);
			out = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = buf.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return out.toByteArray();

	}

	public static void main(String[] args) {
		String url = "http://www.impactcoatings.com";
		Document doc = CIOUtil.parseHtmlUrl(url);
		Elements els = doc.getElementsByTag("img");
		ListIterator<Element> it = els.listIterator();
		while(it.hasNext()) {
			Element e = it.next();
			String src = e.attr("src");
			System.out.println(url + src);
			try {
				byte[] bytes = CIOUtil.getBytesFromUrl(url + "/" + src);
				System.out.println("Number of bytes: " + bytes.length);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
	}

}
